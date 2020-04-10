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

import warnings
warnings.filterwarnings("ignore")

url = "https://www.mohfw.gov.in/"

keywords = ["corona", "coronavirus", "lockdown", "covid-19", "covid19", "masks", "handwash", "mask", "infection", "staysafe", "quarantine", "stayathome", "pandemic"]

response = requests.get(url, allow_redirects = True)
soup = BeautifulSoup(response.text)
tags = soup.findAll('a')
i = 0
title = []
url = []
for tag in tags:
    try :
        if(tag['target'] == "_BLANK") :
            if(i < 3) :
                i = i + 1
                continue
            else :
                title.append(tag.text)
                url.append(tag['href'])
                #print(tag['href'])
                #print("")
                #print(tag.text)
                #print("")
    except:
        
        continue
filename = "non-covid.json"
data = dict()
data["title"] = title
data["url"] = url
with open(filename, 'w') as fh:
    fh.write(json.dumps(data))

    