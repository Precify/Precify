# fake_news_tf.py

fake detection alogorithm using RNN with LSTM cells using tensorflow

**Note** : Make sure you have tensorflow == 2.0
Run this command :
`wget --no-check-certificate https://storage.googleapis.com/laurencemoroney-blog.appspot.com/glove.6B.100d.txt -O /tmp/glove.6B.100d.txt`

`python fake_news_tf.py`

For testing, dump all the tweets in tweet.csv.
Output is all fake news.
If want to test on other csv file, change this line `df2 = pd.read_csv(<filename-with-path>)`


# fake_news_bert.py

fake detection algorithm using BERT (pytorch implementation)

**Meta Note** : You need to run this on GPU . You are running on colab, run fake_news_bert.ipynb notebook itself and also all the data files are available to you at your working directory. To make things easy, just mount your drive in colab and change your working directory and copy data folder into that directory. Also make sure you change the runtime to GPU on colab.

**Note** : Run this commands before proceeding further :
1. `pip install pytorch-pretrained-bert`
2. `pip install pytorch-nlp`
3. `pip install gdown==3.6.0`
4. Run this to download checkpoint file both
`fid = "1mHW0zXK6gzJs0nQUle_yrWqiAHKOORUI"`
`!gdown https://drive.google.com/uc?id={fid}`

Step 4 will return path, just copy this and pass it as argument

For ones having GPU, run this command:
`python fake_news_detector.py <ngrok-link> <path-to-file>`

For running on colab, just put the ngrok-link and put filename obtained in step4.


