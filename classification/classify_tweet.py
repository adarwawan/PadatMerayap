import pickle
import csv
import extraction
import preprocess

def classify_tweet(tweet):
    f = open('model/my_classifier.pickle', 'rb')
    classifier = pickle.load(f)
    f.close()

    preprocessed_tweet = preprocess.preprocess(tweet)

    extracted_tweet = extraction.extract_features(preprocessed_tweet)

    label = classifier.classify(extracted_tweet)

    return label

if __name__ == "__main__":
    lalin_data_csv = "../data/lalinbdg_tweets.csv"
    with open(lalin_data_csv, newline = '') as csvfile:
        reader = csv.reader(csvfile, quotechar = '"')
        raw_documents = list(reader)

    for tweet in raw_documents:
        print(tweet)
        print(classify_tweet(tweet))
