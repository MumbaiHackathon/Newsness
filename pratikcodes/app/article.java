/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsness;

import newsness.DateUtil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
/**
 *
 * @author Pratik1
 */
public class article {
    String title,link,description;
    Date publish_date;
    String date;
    public article(String title, String link, String description, String publish_date) {
        this.title = title;
        this.link = link;
        this.description = description;
        //DateTimeFormatter f = DateTimeFormatter.RFC_1123_DATE_TIME;
        //DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        //String str = formatter.parse(publish_date);
        DateFormat src = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        DateFormat dest = new SimpleDateFormat("yyyy-MM-dd");
        try {
            
            
            this.publish_date = src.parse(publish_date);
            /*
            String year = String.valueOf(this.publish_date.getYear());
            String month = String.valueOf(this.publish_date.getMonth());
            String day = String.valueOf(this.publish_date.getDay());
            date = year + "-" + month + "-" + day;
              */
            date = dest.format(this.publish_date);

        } catch (Exception ex) {
            System.out.println("Something went wrong in parsing Date::"+ publish_date);
        }
    }
    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public String getDate() {
        return date;
    }
    
}
