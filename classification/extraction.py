import nltk
import csv
import pickle

def extract_features(document):

    f = open('model/word_features.pickle', 'rb')
    word_features = pickle.load(f)
    f.close()

    document_words = set(''.join(document).split())
    features = {}
    for word in word_features:
        features['contains({})'.format(word)] = (word in document_words)
    return features
