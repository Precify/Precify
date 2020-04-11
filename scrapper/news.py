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

url_lst_all = ["https://economictimes.indiatimes.com/news/politics-and-nation/coronavirus-cases-in-india-live-news-latest-updates-april8/liveblog/75038326.cms", "https://www.pharmaceutical-technology.com/special-focus/covid-19/coronavirus-covid-19-outbreak-latest-information-news-and-updates/", "https://www.business-standard.com/article/current-affairs/coronavirus-live-updates-covid-19-cases-in-india-global-death-toll-state-wise-delhi-maharasthra-tablighi-nizamuddin-lockdown-extension-latest-news-120040800236_1.html", "https://timesofindia.indiatimes.com/india/coronavirus-india-cases-live-news-updates-lockdown-in-india-likely-to-be-extended/liveblog/75074630.cms"]

url_lst_statewise = ["https://timesofindia.indiatimes.com/india"]

url_lst_citywise = ["https://timesofindia.indiatimes.com/city"]

keywords = ["corona", "coronavirus", "lockdown", "covid-19", "covid19", "masks", "handwash", "mask", "infection", "staysafe", "quarantine", "stayathome", "pandemic", "shutdown"]

apikey = "c8c54678d99e43d9b11a09d74e8dfa51"

def extract_news(covid_links, filename) :
    #loc_ = loc.lower()
    title = []
    content = []
    for link in covid_links :
        try :
            article = Article(link)
            article.download()
            article.parse()
            article.nlp()
            title.append(article.title)
            content.append(article.summary)
            #print(article.title)
            #print(article.summary)
            #print("")
            #break
        except :
            continue
        
    data = dict()
    data['title'] = title
    data['content'] = content
    with open(filename, 'w') as fh:
        fh.write(json.dumps(data))
    return 
       

def allNews() :
    articles = []
    #response = requests.get(url_lst_all[0], allow_redirects = True)
    #soup = BeautifulSoup(response.text)
    r = requests.get(url_lst_all[0]) 
    soup = BeautifulSoup(r.content, 'html5lib') 
    #text1 = soup.findAll('h3', class_ = "")
    text2 = soup.findAll('div', class_ = "blogSysn")
    #text3 = soup.findAll('span')
    for text in text2 :
        if(len(text.text) > 50):
            articles.append(text.text)
            
    r = requests.get(url_lst_all[1]) 
    soup = BeautifulSoup(r.content, 'html5lib') 
    #text1 = soup.findAll('h3')
    text2 = soup.findAll('a')
    text3 = soup.findAll('li', class_ = "row")
    #text4 = soup.findAll('div', class_ = "large-9 columns")
    for txt in text3:
        regex = re.compile(r'[\n\r\t]')
        s = regex.sub(" ", txt.text)
        articles.append(s)
    for text in text2 :
        flag = 0
        blog = ""
        for ch in text:
            if(isinstance(ch, bs4.element.NavigableString)) :
                blog = blog + ch
        if(len(blog) > 25) :
            articles.append(blog)
        

    r = requests.get(url_lst_all[2]) 
    soup = BeautifulSoup(r.content, 'html5lib') 
    tags = soup.findAll('a')
    i = 0
    for tag in tags:
        try :
            if(tag['target'] == "_blank") :
                if(i < 3) :
                    i = i + 1
                    continue
                else :
                    if(len(tag.text) > 50) :
                        my_str = tag.text
                        _RE_COMBINE_WHITESPACE = re.compile(r"(?a:\s+)")
                        _RE_STRIP_WHITESPACE = re.compile(r"(?a:^\s+|\s+$)")

                        my_str = _RE_COMBINE_WHITESPACE.sub(" ", my_str)
                        my_str = _RE_STRIP_WHITESPACE.sub("", my_str)
                        articles.append(my_str)
        except:
            continue
            
    url = ('http://newsapi.org/v2/top-headlines?'
       'country=in&'
       'apiKey=c8c54678d99e43d9b11a09d74e8dfa51')
    response = requests.get(url)
    data = response.json()
    for article in data['articles'] :
        for word in keywords:
            try :
                if word in article['content'].lower():
                    articles.append['title']
            except :
                continue
    
    r = requests.get(url_lst_all[3]) 
    soup = BeautifulSoup(r.text, 'html5lib') 
    tags = soup.findAll('script')
    data = json.loads(tags[1].text, strict=False)
    for t in data['liveBlogUpdate'] :
        for word in keywords:
            if word in t['headline'].lower() :
                articles.append(t['headline'])
                break
    
    return articles



def citywise(loc) :
    prefix = "https://timesofindia.indiatimes.com"
    covid_citywise = []
    response = requests.get(url_lst_citywise[0], allow_redirects = True)
    soup = BeautifulSoup(response.text)
    one_a_tag = soup.findAll('a')
    #print(one_a_tag)
    no_cities = 67
    index = 0
    cities_lst = []
    for line in one_a_tag :
            if(index > no_cities) :
                break
            try:
                link = line['href']
                if(link[:6] == "/city/") :
                    cities_lst.append(link)
                    index = index + 1
            except:
                continue
    
    articles_citywise = []
    i = 0
    for url in cities_lst:
        articles_lst = []
        url_ = prefix + url
        #print(url_)
        words = url_.split("/")
        #print(words[-1])
        if(words[-1] != loc.lower()) :
            continue
        #articles_lst.append(words[-1])
        length_ = len(url_)
        length = len(url)
        #print(url_)
        r = requests.get(url_) 
        soup = BeautifulSoup(r.content, 'html5lib') 
        one_a_tag = soup.findAll('a')
        for link in one_a_tag :
            try :
                if(link['href'][-4:] == ".cms") :
                    if(link['href'][:length] == url):
                        temp = prefix + link['href']
                        articles_lst.append(temp)
                    elif(link['href'][:length_] == url_):
                        articles_lst.append(link['href'])
            except :
                continue
        #for ele in articles_lst :
            #print(ele)
        #break
        #articles_citywise.append(articles_lst)
        i = i + 1
        #covid_links = []
        #from newspaper import Article
        for link in articles_lst:
            #print(link)
            article = Article(link)
            article.download()
            article.parse()
            article.nlp()
            for word in keywords:
                if word in article.text.lower() :
                    covid_citywise.append(link)
                    #print(link)
                    break
            #covid_links.append(covid_citywise)
        break  
    return covid_citywise
            
    
def statewise(loc) :
    prefix = "https://timesofindia.indiatimes.com"
    #covid_links = []
    response = requests.get(url_lst_statewise[0], allow_redirects = True)
    soup = BeautifulSoup(response.text)
    data = soup.findAll('meta')
    links_lst =[]
    i = -1
    for one in data :
        try:
            if(one['itemprop'] == "url") :
                url = str(one['content']).split('/')
                if(url[-2] == "india") :
                    i = i + 1
                    if(i == 0) :
                        continue
                    else :
                        links_lst.append(str(one['content']))
        except:
            continue
    for link in links_lst :
        covid_statewise = []
        parser = link.split("/")
        if(parser[-1] != loc.lower()):
            continue
        #covid_statewise.append(parser[-1])
        r = requests.get(link) 
        soup = BeautifulSoup(r.content, 'html5lib') 
        tags = soup.findAll('script')
        tags_ = soup.findAll('a')
        lst = []
        for line in tags:
            try:
                if(line['type'] == "application/ld+json") :
                    lst.append(line)
            except :
                continue
        urls_list = []
        for tag in tags_ :
            try :
                if(tag['hid'] is not None) :
                    urls_list.append(tag['href'])
            except :
                continue
        for line in lst :
            data = json.loads(line.text, strict=False)
            i = 0
            for news in data['itemListElement'] :
                flag = 0
                for word in keywords :
                    if(word in news['name'].lower()) :
                            #url and name in news
                            covid_statewise.append(prefix + urls_list[i])
                            flag = 1
                            break
                if(flag == 0):
                    article = Article(prefix + urls_list[i])
                    article.download()
                    article.parse()
                    article.nlp()
                    for word in keywords:
                        if word in article.text.lower() :
                            covid_statewise.append(prefix + urls_list[i])
                            break
                i = i + 1

        #covid_links.append(covid_statewise)
        #print("done") 
        break

    return covid_statewise
     
def main() :
    if(sys.argv[1] == "all") :
        articles = allNews()
        #for article in articles:
            #print(article)
            #print("")
            #break
         #convert set of strings into json
        data = dict()
        data['articles'] = articles
        with open("all.json", 'w') as fh:
            fh.write(json.dumps(data))
    elif(sys.argv[1] == "statewise") :
        covid_links = statewise(sys.argv[2])
        extract_news(covid_links, str(sys.argv[2]) + ".json")
    elif(sys.argv[1] == "citywise") :
        covid_links = citywise(sys.argv[2])
        extract_news(covid_links, str(sys.argv[2]) + ".json")
    #elif(sys.argv[1] == "regionwise") :
        #covid_links = regionwise()
        #data = extract_news(covid_links, sys_argv[2])
    else :
        print("invalid argument")
        return
        

if (__name__ == "__main__") :
    main()