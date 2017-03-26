# coding: utf-8

def submittedarticlesofauthor(author):
    import mysql.connector
    cnx = mysql.connector.connect(host='192.168.137.1',port='3306',database='newsness',user='root',password='pratik')
    print cnx
    curA = cnx.cursor(buffered=True)
    curB = cnx.cursor(buffered=True)
    query = (" SELECT * FROM articles WHERE status = 'submitted' AND author = \'"+str(author)+"\'")
    curA.execute(query)
    article_arr = []
    for (aid,author,title,sdate,description,text,status, newsness, most_rel) in curA:
        print aid,author,title,sdate,description,text,status, newsness, most_rel
        a = {"aid":aid,"author":author,"title":title,"sdate":sdate,"description":description,"text":text,"status":status, "newsness":newsness, "most_rel":most_rel}
        article_arr.append(a)
    return article_arr

def allarticlesofauthor(author):
    import mysql.connector
    cnx = mysql.connector.connect(host='192.168.137.1',port='3306',database='newsness',user='root',password='pratik')
    print cnx
    curA = cnx.cursor(buffered=True)
    curB = cnx.cursor(buffered=True)
    query = (" SELECT * FROM articles WHERE author = \'"+str(author)+"\'")
    curA.execute(query)
    article_arr = []
    for (aid,author,title,sdate,description,text,status, newsness, most_rel) in curA:
        print aid,author,title,sdate,description,text,status, newsness, most_rel
        a = {"aid":aid,"author":author,"title":title,"sdate":sdate,"description":description,"text":text,"status":status, "newsness":newsness, "most_rel":most_rel}
        article_arr.append(a)
    return article_arr

def publishedarticlesofauthor(author):
    import mysql.connector
    cnx = mysql.connector.connect(host='192.168.137.1',port='3306',database='newsness',user='root',password='pratik')
    print cnx
    curA = cnx.cursor(buffered=True)
    curB = cnx.cursor(buffered=True)
    query = (" SELECT * FROM articles WHERE status = 'approved' AND author = \'"+str(author)+"\'")
    curA.execute(query)
    article_arr = []
    for (aid,author,title,sdate,description,text,status, newsness, most_rel) in curA:
        print aid,author,title,sdate,description,text,status, newsness, most_rel
        a = {"aid":aid,"author":author,"title":title,"sdate":sdate,"description":description,"text":text,"status":status, "newsness":newsness, "most_rel":most_rel}
        article_arr.append(a)
    return article_arr

def rejectedarticlesofauthor(author):
    import mysql.connector
    cnx = mysql.connector.connect(host='192.168.137.1',port='3306',database='newsness',user='root',password='pratik')
    print cnx
    curA = cnx.cursor(buffered=True)
    curB = cnx.cursor(buffered=True)
    query = (" SELECT * FROM articles WHERE status = 'rejected' AND author = \'"+str(author)+"\'")
    curA.execute(query)
    article_arr = []
    for (aid,author,title,sdate,description,text,status, newsness, most_rel) in curA:
        print aid,author,title,sdate,description,text,status, newsness, most_rel
        a = {"aid":aid,"author":author,"title":title,"sdate":sdate,"description":description,"text":text,"status":status, "newsness":newsness, "most_rel":most_rel}
        article_arr.append(a)
    return article_arr

def draftarticlesofauthor(author):
    import mysql.connector
    cnx = mysql.connector.connect(host='192.168.137.1',port='3306',database='newsness',user='root',password='pratik')
    print cnx
    curA = cnx.cursor(buffered=True)
    curB = cnx.cursor(buffered=True)
    query = (" SELECT * FROM articles WHERE status = 'draft' AND author = \'"+str(author)+"\'")
    curA.execute(query)
    article_arr = []
    for (aid,author,title,sdate,description,text,status, newsness, most_rel) in curA:
        print aid,author,title,sdate,description,text,status, newsness, most_rel
        a = {"aid":aid,"author":author,"title":title,"sdate":sdate,"description":description,"text":text,"status":status, "newsness":newsness, "most_rel":most_rel}
        article_arr.append(a)
    return article_arr

def allsubmittedarticles():
    import mysql.connector
    cnx = mysql.connector.connect(host='192.168.137.1',port='3306',database='newsness',user='root',password='pratik')
    print cnx
    curA = cnx.cursor(buffered=True)
    curB = cnx.cursor(buffered=True)
    query = (" SELECT * FROM articles WHERE status = 'submitted'")
    curA.execute(query)
    article_arr = []
    for (aid,author,title,sdate,description,text,status, newsness, most_rel) in curA:
        print aid,author,title,sdate,description,text,status, newsness, most_rel
        a = {"aid":aid,"author":author,"title":title,"sdate":sdate,"description":description,"text":text,"status":status, "newsness":newsness, "most_rel":most_rel}
        article_arr.append(a)
    return article_arr

def articlebyid(aid):
    import mysql.connector
    cnx = mysql.connector.connect(host='192.168.137.1',port='3306',database='newsness',user='root',password='pratik')
    print cnx
    curA = cnx.cursor(buffered=True)
    curB = cnx.cursor(buffered=True)
    query = (" SELECT * FROM articles WHERE aid = \'"+str(aid)+"\'")
    curA.execute(query)
    article_arr = []
    for (aid,author,title,sdate,description,text,status,newsness,most_rel) in curA:
        print aid,author,title,sdate,description,text,status, newsness, most_rel
        a = {"aid":aid,"author":author,"title":title,"sdate":sdate,"description":description,"text":text,"status":status, "newsness":newsness, "most_rel":most_rel}
        article_arr.append(a)
    return article_arr
