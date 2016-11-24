import nltk
import csv

labelled_data = "../data/labelled_data.csv"

with open(labelled_data, newline = '') as csvfile:
    reader = csv.reader(csvfile, quotechar = '"')
    documents = list(reader)

word_list = [''.join(tweet[0]).split() for tweet in documents]

#Membuat himpunan bag-of-words
word_features = set()

for tweet in word_list:
    word_features = word_features.union(set(tweet))

def document_features(document):
    document_words = set(''.join(document).split())
    features = {}
    print(document_words)
    for word in word_features:
        features['contains({})'.format(word)] = (word in document_words)
    return features


featuresets = [(document_features(d), c) for (d,c) in documents]
train_set, test_set = featuresets, featuresets
classifier = nltk.NaiveBayesClassifier.train(train_set)
print(nltk.classify.accuracy(classifier, test_set))
classifier.show_most_informative_features(5)
