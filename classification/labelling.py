import nltk
import csv
import string
from tqdm import *
import re


unlabelled_data_csv = "../data/lalinbdg_tweets.csv"
labelled_data_csv = "../data/labelled_data.csv"

# Membuka csv file not spam, disimpan dalam list
with open(unlabelled_data_csv, newline = '') as csvfile:
    reader = csv.reader(csvfile, quotechar = '"')
    unlabelled_list = list(reader)

labelled_list = []

for tweet in unlabelled_list:
    # print(tweet)
    # label = input("Input label : ").lower()
    # if label == 'n':
    #     tweet.append("notlalin")
    # else:
    #     tweet.append("lalin")

    tweet.append("lalin")
    labelled_list.append(tweet)


with open(labelled_data_csv, 'w', newline = '') as f:
    writer = csv.writer(f)
    for tweet in tqdm(labelled_list):
        print(tweet)
        writer.writerow(tweet)
