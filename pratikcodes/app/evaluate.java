/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsness;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author Pratik1
 */

public class evaluate {
    public static final int MIN_YEAR = 1901;
    public static boolean isLeapYear(int year) {
        if ((year % 4 != 0) || (((year % 100) == 0) && (year % 400 != 0)))
            return false;
        return true;
    }
     protected static Date EPOCH = new Date(1, 1, MIN_YEAR);
     private static final int monthDays[] = {0, 31, 28, 31, 30, 31, 30,
    31, 31, 30, 31, 30, 31};
     public static int getDaysFromEpoch(Date d) {
        int i;
        int days = 0;
        for(i = EPOCH.getYear(); i < d.getYear(); i++)
            days += isLeapYear(i) ? 366 : 365;
        for(i = EPOCH.getMonth(); i < d.getMonth(); i++)
            days += monthDays[i];
             
        days += d.getDay() - EPOCH.getDay();
        return days;
    }
      public static int getDaysBetweenDates(Date d1,Date d2) {
        int diff = Math.abs(evaluate.getDaysFromEpoch(d1)
        - evaluate.getDaysFromEpoch(d2)) - 1;
        return (diff < 0) ? 0 : diff;
    }
    public static void main(String args[]) throws SQLException, ParseException
    {
        Connection c = DBConnection.getInstance();
        int article_id = 5;
        int points = 0,max_pts = 0,id = 0;
        
        String article = "Select * from articles where aid = '"+article_id+"';";
        String article_db = "Select * from article_db;";
        
        String delim = " ";
        
        ResultSet article_set = null;
        ResultSet db_set = null;
        String article_text= null;
        String compare_text = null;
        String article_word = null;
        String article_date= null;
        Statement s1 = c.createStatement();
        
        article_set = s1.executeQuery(article);
        article_set.next();
        article_text = article_set.getString("text");
        article_date = article_set.getString("sdate");
        db_set = s1.executeQuery(article_db);
        
        
        while(db_set.next())
        {
            compare_text = db_set.getString("description");
            StringTokenizer st = new StringTokenizer(article_text,delim); 
            while(st.hasMoreTokens())
            {
                article_word = st.nextToken();
                if(  article_word.length()>3 && compare_text.contains(article_word))
                {
                    points++;
                }
                /*
                points += points>5 && points<10? 5:0;
                points += points>10 && points<20? 10:0;
                points += points>20 && points<30? 20:0;
                points += points>30 && points<40? 20:0;
                */
            }
            if(max_pts < points)
            {
                max_pts = points;
                id = db_set.getInt("idarticle_db");
            }
            points = 0;
        }
        
        String get_rel_date = "SELECT date FROM article_db where idarticle_db='"+ id+"'";
        ResultSet date_set = s1.executeQuery(get_rel_date);
        date_set.next();
        String szDate = date_set.getString("date");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date rel_date = formatter.parse(szDate);
        Date a_date = formatter.parse(article_date);
        /*
        long diff = Math.abs(a_date.getTime() - rel_date.getTime());
        long diffDays = diff / (24 * 60 * 60 * 1000);
        */
        int diffDays = evaluate.getDaysBetweenDates(a_date, rel_date);
        System.out.println("difference from most relevant article in days: "+diffDays);
        if(diffDays>=0 && diffDays <2) 
        {
            max_pts+= 1;
        }
        else if(diffDays>=2 && diffDays <=5) 
        {
            max_pts+= 10;
        }
        else if(diffDays>=6 && diffDays <=10) 
        {
            max_pts+= 20;
        }
        else if(diffDays >=10) 
        {
            max_pts+= 50;
        }
        int newsness = 100 - max_pts;
        System.out.println("Matches with " + id+" And has newsness "+ newsness);
        String setValue = "UPDATE articles SET newsness = '"+newsness+"',most_rel='"+id+"' WHERE aid = '"+article_id+"';";
        s1.executeUpdate(setValue);
        System.out.println("Values updated!");
    }
}
