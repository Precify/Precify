# fake_news_tf.py

fake detection alogorithm

**Note** : Make sure you have tensorflow == 2.0
Run this command :
`wget --no-check-certificate https://storage.googleapis.com/laurencemoroney-blog.appspot.com/glove.6B.100d.txt -O /tmp/glove.6B.100d.txt`

`python fake_news_tf.py`

For testing, dump all the tweets in tweet.csv.
Output is all fake news.
If want to test on other csv file, change this line `df2 = pd.read_csv(<filename-with-path>)'.