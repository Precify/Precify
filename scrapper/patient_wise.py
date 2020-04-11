# libraries

from datetime import datetime
import os
import glob
import requests 
import numpy as np
import pandas as pd
from bs4 import BeautifulSoup
# download data 
# =============

link = 'https://docs.google.com/spreadsheets/d/e/2PACX-1vSc_2y5N0I67wDU38DjDh35IZSIS30rQf7_NYZhtYYGU1jJYT6_kDx4YpF-qw0LSlGsBYP8pqM_a1Pd/pubhtml#'
req = requests.get(link)
soup = BeautifulSoup(req.content, "html.parser")
tbody = soup.find_all('tbody')[0]
body = tbody.find_all('tr')
head_row = [i.text for i in body[0].find_all('td')]
contents = []

for i in range(len(body)):
    contents.append([i.text for i in body[i].find_all('td')])
p_df = pd.DataFrame(contents[2:len(contents)], columns=head_row)
# selecting important columns only
# ================================

p_df = p_df.loc[:, :'Backup Notes']
p_df = p_df.replace(r'', np.nan, regex=True)
p_df.dropna(subset=['Detected State'], inplace=True)
# rename dateframe columns 
# ========================

p_df.columns = ['_'.join(col.lower().split()) for col in p_df.columns]
p_df.rename(columns = {'contracted_from_which_patient_(suspected)':'suspected_contacted_patient'}, inplace=True)
# creating patient id column from patient number
# ===============================================

p_df['p_id'] = p_df['patient_number'].apply(lambda x : 'P'+str(x))
p_df = p_df.loc[:, :'backup_notes']
p_df.to_csv('patients_data.csv', index=False)
data = p_df.to_json(orient="columns")

#data is json object conatining db