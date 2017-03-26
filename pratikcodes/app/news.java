/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsness;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Pratik1
 */
public class news {
    static final HashMap<String,String> url = new HashMap<String,String>();  
    static Connection html_doc_conn;
    static Document doc;
    
    news()
    {
        url.put("Hindu:Cricket", "http://www.thehindu.com/sport/cricket/?service=rss");
        url.put("IndiaToday:Nation", "http://indiatoday.intoday.in/rss/article.jsp?sid=36");
        url.put("IndianExpress:Pune", "http://syndication.indianexpress.com/rss/706/pune.xml");
        url.put("IndianExpress:World", "http://syndication.indianexpress.com/rss/695/world.xml");
        url.put("Hindu:News", "http://www.thehindu.com/news/?service=rss");
        url.put("TOI:headLines", "http://newsrack.in/crawled.feeds/toi.rss.xml");
        url.put("TOI:Sports", "http://timesofindia.indiatimes.com/rssfeeds/671208.cms");
    }
    static ArrayList<article> getNews() throws IOException
    {
        url.put("Hindu:Cricket", "http://www.thehindu.com/sport/cricket/?service=rss");
        

//url.put("IndiaToday:Nation", "http://indiatoday.intoday.in/rss/article.jsp?sid=36");        
//url.put("TOI:headLines", "http://newsrack.in/crawled.feeds/toi.rss.xml");
        
        // populated
        //url.put("TOI:Sports", "http://timesofindia.indiatimes.com/rssfeeds/671208.cms");
        //url.put("Hindu:News", "http://www.thehindu.com/news/?service=rss");//
        //url.put("IndianExpress:World", "http://syndication.indianexpress.com/rss/695/world.xml");
        url.put("IndianExpress:Pune", "http://syndication.indianexpress.com/rss/706/pune.xml");//
        int itr = 0;
        int count = 0;
        ArrayList<article> article_list = new ArrayList<article>();
        for(String rss_feed:url.values())
        {
            itr++;
            System.out.println("**Fetching from:"+rss_feed+"itr"+itr);
            html_doc_conn = Jsoup.connect(rss_feed);
            doc = html_doc_conn.get();
            Elements el_list = doc.getElementsByTag("item");
            for(Element e : el_list)
            {   
                /*
                String title = e.child(1).ownText();
                String link = e.child(2).ownText();
                String desc = e.child(3).ownText();
                String szDate = e.child(4).ownText();
                */
                String title = e.getElementsByTag("title").text();
                //String link = e.getElementsByTag("link").text();
                String link = e.getElementsByTag("guid").text();
                String desc = e.getElementsByTag("description").text();
                String szDate = e.getElementsByTag("pubDate").text();
                article_list.add(new article(title, link, desc, szDate));
                System.out.println("Article: "+title+"Link:"+link);
                count++;
            }
        }
        return article_list;
    }
}
