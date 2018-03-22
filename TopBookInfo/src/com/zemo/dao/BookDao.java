package com.zemo.dao;

import com.zemo.entity.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Liam
 * @version 1.0 2018/3/21
 */
public class BookDao {
    public static final String dburl = "jdbc:mysql://localhost:3307/douban_book?useSSL=true&autoReconnect=true";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "root";
    public  Connection conn = null;
    public  PreparedStatement pst = null;

    /**
     * 保存图书信息到数据库
     *
     * @param book
     */
    public  void saveBook(Book book) {
        try {
            // 指定连接类型
            Class.forName(name);
            // 获取连接
            conn = DriverManager.getConnection(dburl, user, password);
            //将'替换成\\'避免插入数据库出错
            if (book.getAuthor().contains("\'")) {
                book.setAuthor(book.getAuthor().replace("\'", "\\'"));
            }
            if (book.getName().contains("\'")) {
                book.setName(book.getName().replace("\'", "\\'"));
            }
            if (book.getPublishers().contains("\'")) {
                book.setPublishers(book.getPublishers().replace("\'", "\\'"));
            }
            String sql = "insert into book values ('" + book.getName() + "', '" + book.getRating() + "', '"
                    + book.getRatingPeople() + "', '" + book.getAuthor() + "', '" + book.getPublishers() + "', '" + book.getPulicationDate() + "', '" + book.getPrice() + "')";
            // 准备执行语句
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存url到数据库
     *
     * @param url
     */
    public  void saveUrl(String url) {
        try {
            // 指定连接类型
            Class.forName(name);
            // 获取连接
            conn = DriverManager.getConnection(dburl, user, password);
            //将'替换成\\'避免插入数据库出错
            if (url.contains("\'")) {
                url = url.replace("\'", "\\'");
            }
            String sql = "insert into url value ('" + url + "')";
            // 准备执行语句
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
