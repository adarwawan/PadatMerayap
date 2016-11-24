import nltk
import csv
import string
from tqdm import *
import re


preprocessed_data_csv = "../data/preprocessed_lalinbdg_tweets.csv"
labelled_data_csv = "../data/labelled_data.csv"

# Membuka csv file not spam, disimpan dalam list
with open(preprocessed_data_csv, newline = '') as csvfile:
    reader = csv.reader(csvfile, quotechar = '"')
    lalin_list = list(reader)

labelled_list = list()

for tweet in lalin_list:
    print(tweet)
    # label = input("Input label : ").lower()
    # if label == 'n':
    #     tweet.append("nl")
    # else:
    #     tweet.append("l")
    tweet.append("lalin")
    labelled_list.append(tweet)


with open(labelled_data_csv, 'w', newline = '') as f:
    writer = csv.writer(f)
    for tweet in tqdm(labelled_list):
        print(tweet)
        writer.writerow(tweet)
