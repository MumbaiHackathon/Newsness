import wikipedia

def getsummary(about):
    text = wikipedia.summary(about, sentences=10)
    print text
    return text
