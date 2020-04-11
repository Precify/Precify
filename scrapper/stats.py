import requests
import urllib.request
import time
from bs4 import BeautifulSoup
import bs4

import requests
from urllib.parse import quote

from newspaper import Article

import nltk
from nltk.stem.porter import *

porterStemmer = PorterStemmer()

import pandas as pd

import json

import re

from selenium import webdriver

import sys

import lxml.html as lh

import warnings
warnings.filterwarnings("ignore")

url = "https://www.mohfw.gov.in/"

keywords = ["corona", "coronavirus", "lockdown", "covid-19", "covid19", "masks", "handwash", "mask", "infection", "staysafe", "quarantine", "stayathome", "pandemic"]

response = requests.get(url, allow_redirects = True)
doc = lh.fromstring(response.content)#Parse data that are stored between <tr>..</tr> of HTML
tr_elements = doc.xpath('//tr')
length = len(tr_elements[0])
tr_elements = doc.xpath('//tr')#Create empty list
col=[]
i=0#For each row, store each first element (header) and an empty list
for t in tr_elements[0]:
    i+=1
    name=t.text_content()
    #print ('%d:"%s"'%(i,name))
    col.append((name,[]))
#Since out first row is the header, data is stored on the second row onwards
for j in range(1,len(tr_elements)):
    #T is our j'th row
    T=tr_elements[j]
    
    #If row is not of size 10, the //tr data is not from our table 
    if len(T)!=length:
        break
    
    #i is the index of our column
    i=0
    
    #Iterate through each element of the row
    for t in T.iterchildren():
        data=t.text_content() 
        #Check if row is empty
        if i>0:
        #Convert any numerical value to integers
            try:
                data=int(data)
            except:
                pass
        #Append the data to the empty list of the i'th column
        col[i][1].append(data)
        #Increment i for the next column
        i+=1
Dict={title:column for (title,column) in col}
df=pd.DataFrame(Dict)
#print(df)
#convert df into json
data = df.to_json(orient='columns')
with open("stats.json", 'w') as fh:
        fh.write(json.dumps(data))
#df.to_csv("stats.csv")