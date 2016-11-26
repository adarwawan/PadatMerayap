# I.S : ada file CSV twitter yang sudah dilabeli
# F.S :
    # ada pickle (model) dari classifier
    # ada pickle dari word features (untuk membangun bag-of-words)

import preprocess
import extraction
import classify_tweet
import csv
import nltk
import pickle
from tqdm import *
from nltk.classify.scikitlearn import SklearnClassifier
from sklearn.naive_bayes import MultinomialNB,BernoulliNB
from sklearn.svm import SVC, LinearSVC, NuSVC

labelled_data_csv = "../data/labelled_data.csv"
labelled_preprocessed_data_csv = "../data/labelled_preprocessed_data.csv"

# Membuka labelled data yang belum di praproses
with open(labelled_data_csv, newline = '') as csvfile:
    reader = csv.reader(csvfile, quotechar = '"')
    labelled_list = list(reader)

# Melakukan praproses
labelled_preprocessed_list = preprocess.preprocess_list(labelled_list)
labelled_preprocessed_set = set(labelled_preprocessed_list) # Menghapus duplicate tweets

# Menyimpan list data yang telah di pra-proses ke file csv
with open(labelled_preprocessed_data_csv, 'w', newline = '') as f:
    writer = csv.writer(f)
    for tweet in labelled_preprocessed_set:
        writer.writerow(tweet)


word_list = [''.join(tweet[0]).split() for tweet in labelled_preprocessed_set]

# Membuat himpunan bag-of-words
word_features = set()

for tweet in word_list:
    word_features = word_features.union(set(tweet))

f = open('model/word_features.pickle', 'wb')
pickle.dump(word_features, f)
f.close()

def document_features(document):
    document_words = set(''.join(document).split())
    features = {}
    for word in word_features:
        features['contains({})'.format(word)] = (word in document_words)
    return features

featuresets = [(document_features(d), c) for (d,c) in labelled_preprocessed_set]
train_set = featuresets

# 10 Fold CV
num_folds = 10
subset_size = int(len(train_set)/num_folds)
sum_NB_accuracy = 0;
sum_SV_accuracy = 0;
for i in range(num_folds):
    testing_this_round = train_set[i*subset_size:][:subset_size]
    training_this_round = train_set[:i*subset_size] + train_set[(i+1)*subset_size:]

    SVC_classifier = SklearnClassifier(LinearSVC())
    SVC_classifier.train(training_this_round)
    SV_accuracy = nltk.classify.accuracy(SVC_classifier, testing_this_round)
    sum_SV_accuracy += SV_accuracy

    NB_classifier = SklearnClassifier(MultinomialNB())
    NB_classifier.train(training_this_round)
    NB_accuracy = nltk.classify.accuracy(NB_classifier, testing_this_round)
    sum_NB_accuracy += NB_accuracy


# find mean accuracy over all rounds
sum_SV_accuracy /= num_folds
sum_NB_accuracy /= num_folds
print ("Total SV accuracy: ", sum_SV_accuracy)
print ("Total NB accuracy: ", sum_NB_accuracy)


f = open('model/my_classifier.pickle', 'wb')
pickle.dump(SVC_classifier, f)
f.close()
