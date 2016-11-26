# Ini kode contoh untuk mengklasifikasikan tweet
# Misal dump tweets baru.

import tweepy #https://github.com/tweepy/tweepy
import classify_tweet
import csv

#Twitter API credentials
consumer_key = 'eNrLklArTZfWCGr9nP9n1t9KC' # Rahasia, Diubah dulu
consumer_secret = 'EACGK3rL7zAs0WW47mA4gxbcHJdGoD9YPGUFpQKVz01Nhgz61K' # Rahasia, Diubah dulu
query = '#lalinbdg'
maxTweets = 200;

#Twitter only allows access to a users most recent 3240 tweets with this method

#authorize twitter, initialize tweepy
auth = tweepy.AppAuthHandler(consumer_key,consumer_secret)
api = tweepy.API(auth, wait_on_rate_limit=True,wait_on_rate_limit_notify=True)

#initialize a list to hold all the tweepy Tweets
alltweets = []

#make initial request for most recent tweets (200 is the maximum allowed count)
new_tweets = api.search(q = query,count=100)

#save most recent tweets
alltweets.extend(new_tweets)

#save the id of the oldest tweet less one
oldest = alltweets[-1].id - 1

tweetCount = 100
#keep grabbing tweets until there are no tweets left to grab
while len(new_tweets) > 0 and tweetCount < maxTweets:

	#all subsiquent requests use the max_id param to prevent duplicates
    new_tweets = api.search(q = query,count=100,max_id=oldest)

	#save most recent tweets
    alltweets.extend(new_tweets)

	#update the id of the oldest tweet less one
    oldest = alltweets[-1].id - 1

    print("...%s tweets downloaded so far" % (len(alltweets)))

    tweetCount+=len(new_tweets)


#transform the tweepy tweets into a 2D array that will populate the csv
outtweets = [[tweet.text, tweet.created_at] for tweet in alltweets] # Pake date time, misalnya

# Kalau mau hanya teks..
#text_tweets = [tweet_text for (tweet_text, tweet_date) in outtweets]
text_tweets = outtweets

# Kalau mau hanya ngambil yang berlabel 'lalin', pake fungsi klasifikasi yang dibuat urang
lalin_tweets = []

for tweet in text_tweets:
    if classify_tweet.classify_tweet(tweet) == 'lalin':
        lalin_tweets.append(tweet)
    else:
        print(tweet)

# Gunakan tweet yang berlabel lalin untuk diekstrak informasinya (info extraction)
# Good luck!

# Ini tweet relevan disimpen
with open('../data/relevant_tweets.csv', 'w', newline = '') as f:
    writer = csv.writer(f)
    for tweet in lalin_tweets:
        if (len(tweet) == 1):
            writer.writerow([tweet])
        else:
            writer.writerow(tweet)
