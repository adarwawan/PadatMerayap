import nltk
import csv
import string
from tqdm import *
import re

stop_words = "StopWords_Eng-Ind.txt"
lalin_csv = "../data/lalinbdg_tweets.csv"
preprocessed_data_csv = "../data/preprocessed_lalinbdg_tweets.csv"

# Mendapatkan set dari kata-kata stopword yang didefinisikan di file teks
def get_stopwords():
    sw = open(stop_words,encoding = 'utf-8', mode = 'r'); stop = sw.readlines(); sw.close()
    stop = [word.strip() for word in stop]; stop = set(stop)
    return stop

# Mendapatkan set dari simbol simbol tanda baca yang akan dibuang (diabaikan)
def get_punctuations():
    punctuations = set(string.punctuation).union(["''","...","``",".."])
    return punctuations



def preprocess(tweet):

    punctuations = get_punctuations()
    stop = get_stopwords()

    # Menggabungkan kata-kata yang akan dibuang dari dataset
    removed_words = punctuations.union(stop)

    #Convert to lower case
    tweet = ''.join(tweet).lower()
    #Convert www.* or https?://* to URL
    tweet = re.sub('((www\.[^\s]+)|(https?://[^\s]+))','URL',tweet)
    #Convert @username to AT_USER
    tweet = re.sub('@[^\s]+','AT_USER',tweet)
    #Remove additional white spaces
    tweet = re.sub('[\s]+', ' ', tweet)
    #Replace #word with word
    tweet = re.sub(r'#([^\s]+)', r'\1', tweet)

    tweet = nltk.word_tokenize(''.join(tweet))
    tweet = [word for word in tweet if word not in removed_words]

    tweet = ' '.join(tweet)

    return tweet

def preprocess_list(labelled_list):
    print("Processing. . .")
    preprocessed_data_list = []

    punctuations = get_punctuations()
    stop = get_stopwords()

    # Menggabungkan kata-kata yang akan dibuang dari dataset
    removed_words = punctuations.union(stop)

    # Tokenize list not spam dan spam, lalu membuang kata-kata yang telah didefinisikan akan dibuang
    # Dimasukkan ke list data yang akan menjadi output
    print("Melakukan pra-proses tweets")
    for (row,label) in tqdm(labelled_list):
        tweet = preprocess(row)
        t = tweet,label
        preprocessed_data_list.append(t)

    return preprocessed_data_list

if __name__ == "__main__":
    # OBSOLETE, jangan refer ke sini
    # Membuka csv file not spam, disimpan dalam list
    with open(lalin_csv, newline = '') as csvfile:
        reader = csv.reader(csvfile, quotechar = '"')
        lalin_list = list(reader)

    preprocessed_data_set = preprocess_list(lalin_list)
    # Menyimpan list data yang telah di pra-proses ke file csv
    print("Menyimpan list data yang telah di pra-proses ke file csv")

    print(len(preprocessed_data_set))

    with open(preprocessed_data_csv, 'w', newline = '') as f:
        writer = csv.writer(f)
        for tweet in tqdm(preprocessed_data_set):
            writer.writerow([tweet])
