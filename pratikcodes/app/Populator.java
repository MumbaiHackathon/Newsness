/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsness;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pratik1
 */
public class Populator extends Thread{
    public void run()
    {
        
    }
    void _populate() throws IOException, SQLException
    {
        Connection c = DBConnection.getInstance();
        String sql = "INSERT INTO article_db (idarticle_db,title,link,date,description) VALUES(DEFAULT,?,?,?,?);";
        PreparedStatement addArticle = c.prepareStatement(sql);
        ArrayList<article> list = news.getNews();
        System.out.println("Inserting "+list.size()+" Articles");
        for(article a : list)
        {
            addArticle.setString(1,a.getTitle());
            addArticle.setString(2,a.getLink());
            addArticle.setString(3, a.getDate());
            addArticle.setString(4, a.getDescription());
            addArticle.executeUpdate();
        }
        System.out.println("Inserting Finished!");
    }
    public static void main(String args[]) throws IOException, SQLException
    {
        Populator db_populator = new Populator();
        db_populator._populate();
    }
}
