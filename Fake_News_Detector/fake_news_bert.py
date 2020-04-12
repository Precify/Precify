import pandas as pd 
import numpy as np 
import torch.nn as nn
from pytorch_pretrained_bert import BertTokenizer, BertModel
import torch
from torchnlp.datasets import imdb_dataset
from keras.preprocessing.sequence import pad_sequences
from sklearn.metrics import classification_report
from collections import Counter 
import http.client
import json

import random
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split

def get_data(link_conn) :
    try :
        conn = http.client.HTTPSConnection(link_conn)
    except :
        conn = http.client.HTTPSConnection('localhost:5000')
    headers = {'Content-type': 'application/json'}
    conn.request('GET', '/getAllUntestedPosts', None, headers)
    response = conn.getresponse()
    return response.read().decode()

class BertBinaryClassifier(nn.Module):
    def __init__(self, dropout=0.1):
        super(BertBinaryClassifier, self).__init__()

        self.bert = BertModel.from_pretrained('bert-base-uncased')

        self.dropout = nn.Dropout(dropout)
        self.linear = nn.Linear(768, 1)
        self.sigmoid = nn.Sigmoid()
    
    def forward(self, tokens, masks=None):
        _, pooled_output = self.bert(tokens, attention_mask=masks, output_all_encoded_layers=False)
        dropout_output = self.dropout(pooled_output)
        linear_output = self.linear(dropout_output)
        proba = self.sigmoid(linear_output)
        return proba
    
def training(batch_size, epoch_size, filename) :
    '''very unethical way of loading and traing the data in same function'''
    pd.set_option('display.max_columns', None)
    train_data, test_data = imdb_dataset(train=True, test=True)
    df = pd.read_csv("./data/fake.csv")
    df = df[['text', 'type']]
    #print(len(df))

    #print(Counter(df['type'].values))

    df = df[df['type'].isin(['fake', 'satire'])]
    df.dropna(inplace = True)
    df_fake = df[df['type'] == 'fake'] 
    df_statire = df[df['type'] == 'satire'] 
    df_statire = df_statire.sample(n=len(df_fake))
    df = df_statire.append(df_fake)
    df = df.sample(frac=1, random_state = 24).reset_index(drop=True)

    #print(Counter(df['type'].values))

    train_data = df.head(19)
    test_data = df.tail(19)

    #print(train_data)
    train_data = [{'text': text, 'type': type_data } for text in list(train_data['text']) for type_data in list(train_data['type'])]
    test_data = [{'text': text, 'type': type_data } for text in list(test_data['text']) for type_data in list(test_data['type'])]

    train_texts, train_labels = list(zip(*map(lambda d: (d['text'], d['type']), train_data)))
    test_texts, test_labels = list(zip(*map(lambda d: (d['text'], d['type']), test_data)))


    tokenizer = BertTokenizer.from_pretrained('bert-base-uncased', do_lower_case=True)
    train_tokens = list(map(lambda t: ['[CLS]'] + tokenizer.tokenize(t)[:511], train_texts))
    test_tokens = list(map(lambda t: ['[CLS]'] + tokenizer.tokenize(t)[:511], test_texts))

    train_tokens_ids = list(map(tokenizer.convert_tokens_to_ids, train_tokens))
    test_tokens_ids = list(map(tokenizer.convert_tokens_to_ids, test_tokens))



    train_tokens_ids = pad_sequences(train_tokens_ids, maxlen=512, truncating="post", padding="post", dtype="int")
    test_tokens_ids = pad_sequences(test_tokens_ids, maxlen=512, truncating="post", padding="post", dtype="int")


    train_y = np.array(train_labels) == 'fake'
    test_y = np.array(test_labels) == 'fake'
    
    BATCH_SIZE = batch_size
    EPOCHS = epoch_size


    train_masks = [[float(i > 0) for i in ii] for ii in train_tokens_ids]
    test_masks = [[float(i > 0) for i in ii] for ii in test_tokens_ids]
    train_masks_tensor = torch.tensor(train_masks)
    test_masks_tensor = torch.tensor(test_masks)

    train_tokens_tensor = torch.tensor(train_tokens_ids)
    train_y_tensor = torch.tensor(train_y.reshape(-1, 1)).float()
    test_tokens_tensor = torch.tensor(test_tokens_ids)
    test_y_tensor = torch.tensor(test_y.reshape(-1, 1)).float()
    train_dataset =  torch.utils.data.TensorDataset(train_tokens_tensor, train_masks_tensor, train_y_tensor)
    train_sampler =  torch.utils.data.RandomSampler(train_dataset)
    train_dataloader =  torch.utils.data.DataLoader(train_dataset, sampler=train_sampler, batch_size=BATCH_SIZE)
    test_dataset =  torch.utils.data.TensorDataset(test_tokens_tensor, test_masks_tensor, test_y_tensor)
    test_sampler =  torch.utils.data.SequentialSampler(test_dataset)
    test_dataloader =  torch.utils.data.DataLoader(test_dataset, sampler=test_sampler, batch_size=BATCH_SIZE)
    
    bert_clf = BertBinaryClassifier()
    optimizer = torch.optim.Adam(bert_clf.parameters(), lr=3e-6)
    
    for epoch_num in range(EPOCHS):
        bert_clf.train()
        train_loss = 0
        for step_num, batch_data in enumerate(train_dataloader):
            token_ids, masks, labels = tuple(t for t in batch_data)
            probas = bert_clf(token_ids, masks)
            loss_func = nn.BCELoss()
            batch_loss = loss_func(probas, labels)
            train_loss += batch_loss.item()
            bert_clf.zero_grad()
            batch_loss.backward()
            optimizer.step()
            print('Epoch: ', epoch_num + 1)
            print("\r" + "{0}/{1} loss: {2} ".format(step_num, len(train_data) / BATCH_SIZE, train_loss / (step_num + 1)))

    torch.save(bert_clf, filename)
    return

def testing(df, filename) :
    
    #input
    #device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
    l = len(df)
    lst = []
    if(l%2 == 1):
        for i in range(l) :
            if(i%2 == 1) :
                lst.append('fake')
            else:
                lst.append('satire')
    else :
        for i in range(l) :
            if(i%2 == 0) :
                lst.append('fake')
            else:
                lst.append('satire')
    df['type'] = lst
    test_data = df
    test_data1 = [{'text': text, 'type': ""} for text in list(test_data['text']) ]

    i = 0
    for type_data in list(test_data['type']) :
        test_data1[i]['type'] = type_data
        i = i + 1
        
    test_data = test_data1
    test_texts, test_labels = list(zip(*map(lambda d: (d['text'], d['type']), test_data)))
    tokenizer = BertTokenizer.from_pretrained('bert-base-uncased', do_lower_case=True)
    test_tokens = list(map(lambda t: ['[CLS]'] + tokenizer.tokenize(t)[:511], test_texts))
    test_tokens_ids = list(map(tokenizer.convert_tokens_to_ids, test_tokens))
    test_tokens_ids = pad_sequences(test_tokens_ids, maxlen=512, truncating="post", padding="post", dtype="int")
    test_y = np.array(test_labels) == 'fake'
    test_y = torch.from_numpy(np.array(test_y, dtype=np.uint8))
    
    BATCH_SIZE = 1
    
    test_masks = [[float(i > 0) for i in ii] for ii in test_tokens_ids]
    test_masks_tensor = torch.tensor(test_masks)
    test_tokens_tensor = torch.tensor(test_tokens_ids)
    test_y_tensor = torch.tensor(test_y.reshape(-1, 1)).float()
    test_dataset =  torch.utils.data.TensorDataset(test_tokens_tensor, test_masks_tensor, test_y_tensor)
    test_sampler =  torch.utils.data.SequentialSampler(test_dataset)
    test_dataloader =  torch.utils.data.DataLoader(test_dataset, sampler=test_sampler, batch_size=BATCH_SIZE)

    bert_clf = BertBinaryClassifier()
    optimizer = torch.optim.Adam(bert_clf.parameters(), lr=3e-6)
    bert_clf = torch.load(filename)
    bert_clf.eval()
    bert_predicted = []
    all_logits = []
    lst = []
    with torch.no_grad():
        for step_num, batch_data in enumerate(test_dataloader):

            token_ids, masks, labels = tuple(t for t in batch_data)

            logits = bert_clf(token_ids, masks)
            lst.append(logits)
            loss_func = nn.BCELoss()
            loss = loss_func(logits, labels)
            numpy_logits = logits.cpu().detach().numpy()

            bert_predicted += list(numpy_logits[:, 0])
            all_logits += list(numpy_logits[:, 0])

    return lst

def fake_post(article, score, link_conn) :
    try :
        conn = http.client.HTTPSConnection(link_conn)
    except :
        conn = http.client.HTTPSConnection('localhost:5000')
    headers = {'Content-type': 'application/json'}
    data = article
    data['fakeness'] = str(score)
    json_data = json.dumps(data)
    conn.request('POST', '/fakePost', json_data, headers)
    response = conn.getresponse()
    print(response.read().decode())
    return
  
def true_post(article, score, link_conn) :
    try :
        conn = http.client.HTTPSConnection(link_conn)
    except :
        conn = http.client.HTTPSConnection('localhost:5000')
    headers = {'Content-type': 'application/json'}
    data = article
    data['fakeness'] = str(score)
    json_data = json.dumps(data)
    conn.request('POST', '/testedPost', json_data, headers)
    response = conn.getresponse()
    print(response.read().decode())
    return


def using_bert(lst_bert, filename, link_conn) :
    lst = []
    for t in lst_bert :
        lst.append(t['content'])
    df = pd.DataFrame(lst, columns=["text"])
    scores = testing(df, filename)
    #print(scores)
    i = 0
    for article in lst_bert :
        if(scores[i] > 0.5 ) :
            fake_post(article, scores[i], link_conn)
        else :
            true_post(article, scores[i], link_conn)
        i = i + 1
    return
            
'''def malicious_url_detector(lst_url, link_conn) :
    lst = []
    for t in lst_url :
        lst.append(t['url'])
    #df = pd.DataFrame(lst, columns=["url"])
    data = pd.read_csv("./data/url.csv")
    y = data["label"]
    url_list = data["url"]
    # Using Tokenizer
    vectorizer = TfidfVectorizer()
    # Store vectors into X variable as Our XFeatures
    X = vectorizer.fit_transform(url_list)
    # Split into training and testing dataset 80:20 ratio
    #X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
    # Model Building using logistic regression
    logit = LogisticRegression()
    logit.fit(X, y)
    
    test_data = vectorizer.fit_transform(lst)
    scores = logit.predict(test_data)
    i = 0
    for article in lst_bert :
        if(scores[i] > 0.5 ) :
            fake_post(article, scores[i], link_conn)
        else :
            true_post(article, scores[i], link_conn)
        i = i + 1
    return'''
       

def main() :
    data = get_data(sys.argv[1])
    data = json.loads(data)
    lst_bert = []
    lst_url = []
    '''for article in data:
        try:
            if(len(article['url'] != 0) :
               lst_url.append(article)
            else :
               lst_bert.append(article)
       except:
               lst_bert.append(article)
    if(len(lst_url) != 0) :
        malicious_url_detector(lst_url, sys.argv[1])
    if(len(lst_bert) != 0):
        using_bert(lst_bert, sys.argv[2], sys.argv[1])'''
    for article in data:
        lst_bert.append(article)
    using_bert(lst_bert, sys.argv[2], sys_argv[1])
    
if __name__ == "__main__" :
    main()
