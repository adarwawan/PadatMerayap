#!/usr/bin/env python
# encoding: utf-8

import tweepy #https://github.com/tweepy/tweepy
import csv

#Twitter API credentials
consumer_key = 'eNrLklArTZfWCGr9nP9n1t9KC' # Rahasia, Diubah dulu
consumer_secret = 'EACGK3rL7zAs0WW47mA4gxbcHJdGoD9YPGUFpQKVz01Nhgz61K' # Rahasia, Diubah dulu

maxTweets = 250;


def get_all_tweets(query1,query2):
	#Twitter only allows access to a users most recent 3240 tweets with this method

	#authorize twitter, initialize tweepy
    auth = tweepy.AppAuthHandler(consumer_key,consumer_secret)
    api = tweepy.API(auth, wait_on_rate_limit=True,wait_on_rate_limit_notify=True)

	#initialize a list to hold all the tweepy Tweets
    alltweets = []

	#make initial request for most recent tweets (200 is the maximum allowed count)
    new_tweets = api.search(q = query1,count=100)

	#save most recent tweets
    alltweets.extend(new_tweets)

	#save the id of the oldest tweet less one
    oldest = alltweets[-1].id - 1

    tweetCount = 100
	#keep grabbing tweets until there are no tweets left to grab
    while len(new_tweets) > 0 and tweetCount < maxTweets:

		#all subsiquent requests use the max_id param to prevent duplicates
        new_tweets = api.search(q = query1,count=50,max_id=oldest)

		#save most recent tweets
        alltweets.extend(new_tweets)

		#update the id of the oldest tweet less one
        oldest = alltweets[-1].id - 1

        print("...%s tweets downloaded so far" % (len(alltweets)))

        tweetCount+=len(new_tweets)


    #make initial request for most recent tweets (200 is the maximum allowed count)
    new_tweets = api.search(q = query2,count=100)

	#save most recent tweets
    alltweets.extend(new_tweets)

	#save the id of the oldest tweet less one
    oldest = alltweets[-1].id - 1

    tweetCount = 100
	#keep grabbing tweets until there are no tweets left to grab
    while len(new_tweets) > 0 and tweetCount < maxTweets:

		#all subsiquent requests use the max_id param to prevent duplicates
        new_tweets = api.search(q = query2,count=50,max_id=oldest)

		#save most recent tweets
        alltweets.extend(new_tweets)

		#update the id of the oldest tweet less one
        oldest = alltweets[-1].id - 1

        print("...%s tweets downloaded so far" % (len(alltweets)))

        tweetCount+=len(new_tweets)

	#transform the tweepy tweets into a 2D array that will populate the csv
    outtweets = [[tweet.text] for tweet in alltweets]

	#write the csv
    with open('../data/lalinbdg_tweets.csv', 'w') as f:
        writer = csv.writer(f)
        writer.writerows(outtweets)
    pass


if __name__ == '__main__':
	#pass in the username of the account you want to download
    get_all_tweets("#lalinbdg","#sikonbdg")
