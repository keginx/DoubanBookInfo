package com.zemo.main;

import com.zemo.dao.BookDao;
import com.zemo.entity.Book;
import com.zemo.util.BookTask;
import com.zemo.util.PageUtil;
import com.zemo.util.ThreadPool;

import java.net.URLEncoder;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Liam
 * @version 1.0 2018/3/20
 */
public class main {
    public static void main(String[] args) {
        String[] tagArr = {"互联网", "编程", "算法"};
        ThreadPoolExecutor executor = ThreadPool.getInstance();
        try {
            for (String tagName : tagArr) {
                BookTask task = new BookTask();
                tagName = URLEncoder.encode(tagName, "utf-8");
                task.setTagName(tagName);
                executor.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*测试Book的作者只有一位时需要重新处理*/
//        CopyOnWriteArrayList<String> urls = new CopyOnWriteArrayList<>();
//        urls.add("https://book.douban.com/subject/1919072/");
//        PageUtil.getBook(urls);

        /*测试Book属性中包含单引号*/
//        Book book = new Book("深入理解计算机系统（原书第2版）", "9.7", "2007", "（美）Randal E.Bryant\n" +
//                "David O'Hallaron", "机械工业出版社", "2011-1-1", "99.00元");
//        BookDao.saveBook(book);


        String str = " GBP 31.49";

    }
}
