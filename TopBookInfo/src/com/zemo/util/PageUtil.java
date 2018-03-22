package com.zemo.util;

import com.zemo.dao.BookDao;
import com.zemo.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 页面解析工具
 *
 * @author Liam
 * @version 1.0 2018/3/21
 */
public class PageUtil {

    /**
     * 通过tag获取tag对应的所有url
     *
     * @param tag 标签名
     * @return 标签对应的所有图书详细页url
     */
    public static CopyOnWriteArrayList<String> getUrls(String tag) {
        CopyOnWriteArrayList<String> urls = new CopyOnWriteArrayList<>();
        int index = 0;
        BookDao bookDao = new BookDao();
        try {
            Map<String, String> cookies = new HashMap<>(16);
            //book.douban.com
            cookies.put("__utma", "81379588.1625906329.1478780180.1478780180.1478780180.1");
            cookies.put("__utmb", "81379588.1.10.1478780180");
            cookies.put("__utmc", "81379588");
            cookies.put("__utmz", "81379588.1478780180.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");
            cookies.put("_pk_id.100001.3ac3", "b8e7b1931da4acd1.1478780181.1.1478780181.1478780181.");
            cookies.put("_pk_ses.100001.3ac3", "*");
            //douban.com
            cookies.put("bid", "MvEsSVNL_Nc");
            //read.douban.com
            cookies.put("_ga", "GA1.3.117318709.1478747468");
            cookies.put("_pk_id.100001.a7dd", "ce6e6ea717cbd043.1478769904.1.1478769904.1478769904.");
            cookies.put("_pk_ref.100001.a7dd", "%5B%22%22%2C%22%22%2C1478769904%2C%22https%3A%2F%2Fbook.douban.com%2"
                    + "Fsubject_search%3Fsearch_text%3D%25E6%258E%25A8%25E8%258D%2590%25E7%25B3%25BB%25E7%25BB%259F%25"
                    + "E5%25AE%259E%25E8%25B7%25B5%26cat%3D1001%22%5D");
            //www.douban.com
            cookies.put("_pk_id.100001.8cb4", "237bb6b49215ebbc.1478749116.2.1478774039.1478749120.");
            cookies.put("_pk_ref.100001.8cb4", "%5B%22%22%2C%22%22%2C1478773525%2C%22https%3A%2F%2Fwww.baidu."
                    + "com%2Flink%3Furl%3DlQ4OMngm1b6fAWeomMO7xq6PNbBlxyhdnHqz9mIYN9-ycRbjZvFb1NQyQ7hqzvI46-WThP"
                    + "6A_Qo7oTQNP-98pa%26wd%3D%26eqid%3Da24e155f0000e9610000000258244a0c%22%5D");

            while (true) {
                // 获取cookies

                /*System.setProperty("https.proxySet", "true");
                System.getProperties().put("https.proxyHost", "59.44.16.6");
                System.getProperties().put("https.proxyPort", "80");*/
                Document doc = Jsoup.connect("https://book.douban.com/tag/" + tag + "?start=" + index + "&type=T")
                        .header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)").cookies(cookies)
                        .timeout(3000).get();
                Elements newsHeadlines = doc.select("ul").select("h2").select("a");
                System.out.println("本页：  " + newsHeadlines.size());
                for (Element e : newsHeadlines) {
                    System.out.println(e.attr("href"));
                    urls.add(e.attr("href"));
                    bookDao.saveUrl(e.attr("href"));
                }
                index += newsHeadlines.size();
                System.out.println("共抓取url个数：" + index);
                if (newsHeadlines.size() == 0) {
                    System.out.println("end");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }

    /**
     * 通过url将对应页面的图书信息存到数据库
     *
     * @param urls
     */
    public static void getBook(CopyOnWriteArrayList<String> urls) {
        BookDao bookDao = new BookDao();
        Map<String, String> cookies = new HashMap<>(16);
        //book.douban.com
        cookies.put("__utma", "81379588.1625906329.1478780180.1478780180.1478780180.1");
        cookies.put("__utmb", "81379588.1.10.1478780180");
        cookies.put("__utmc", "81379588");
        cookies.put("__utmz", "81379588.1478780180.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");
        cookies.put("_pk_id.100001.3ac3", "b8e7b1931da4acd1.1478780181.1.1478780181.1478780181.");
        cookies.put("_pk_ses.100001.3ac3", "*");
        //douban.com
        cookies.put("bid", "MvEsSVNL_Nc");
        //read.douban.com
        cookies.put("_ga", "GA1.3.117318709.1478747468");
        cookies.put("_pk_id.100001.a7dd", "ce6e6ea717cbd043.1478769904.1.1478769904.1478769904.");
        cookies.put("_pk_ref.100001.a7dd", "%5B%22%22%2C%22%22%2C1478769904%2C%22https%3A%2F%2Fbook.douban.com%2"
                + "Fsubject_search%3Fsearch_text%3D%25E6%258E%25A8%25E8%258D%2590%25E7%25B3%25BB%25E7%25BB%259F%25"
                + "E5%25AE%259E%25E8%25B7%25B5%26cat%3D1001%22%5D");
        //www.douban.com
        cookies.put("_pk_id.100001.8cb4", "237bb6b49215ebbc.1478749116.2.1478774039.1478749120.");
        cookies.put("_pk_ref.100001.8cb4", "%5B%22%22%2C%22%22%2C1478773525%2C%22https%3A%2F%2Fwww.baidu."
                + "com%2Flink%3Furl%3DlQ4OMngm1b6fAWeomMO7xq6PNbBlxyhdnHqz9mIYN9-ycRbjZvFb1NQyQ7hqzvI46-WThP"
                + "6A_Qo7oTQNP-98pa%26wd%3D%26eqid%3Da24e155f0000e9610000000258244a0c%22%5D");

        int count = 0;
        //初始化Book
        Book book = new Book();
        // 如果不设置，只要代理IP和代理端口正确,此项不设置也可以
        for (String url : urls) {
            try {
                Document doc = Jsoup.connect(url)
                        .header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)").cookies(cookies)
                        .timeout(30000).get();
                Elements titleElement = doc.getElementById("mainpic").select("a");
                Elements scoreElement = doc.select("strong");
                Elements ratingSum = doc.getElementsByClass("rating_sum").select("a").select("span");
                Elements authorElement = doc.getElementById("info").select("span").first().select("a");
                Element pressElement = doc.getElementById("info");

                // 书名
                String title = titleElement.attr("title");
                book.setName(title);
                // 评分
                String score = scoreElement.html();
                book.setRating(score);
                // 评价人数
                String rating_sum = ratingSum.html();
                book.setRatingPeople(rating_sum);
                // 作者
                String author = authorElement.html();
                //获取到的元素为空说明只有一位作者需要重新获取元素
                if (author.equals("")) {
                    authorElement = doc.getElementById("info").select("a");
                    author = authorElement.text();
                }
                book.setAuthor(author);
                // 出版社
                String press = pressElement.text();
                if (press.indexOf("出版社:") > -1) {
                    press = pressElement.text().split("出版社:")[1].split(" ")[1];
                } else {
                    press = "";
                }
                book.setPublishers(press);
                // 出版日期
                String date = pressElement.text();
                if (date.indexOf("出版年:") > -1) {
                    date = pressElement.text().split("出版年:")[1].split(" ")[1];
                } else {
                    date = "";
                }
                book.setPulicationDate(date);
                // 价格
                String price = pressElement.text();
                if (price.indexOf("定价:") > -1) {
                    price = pressElement.text().split("定价:")[1].split(" ")[1];
                    //其他非人民币价格
                    if (price.equals("CNY") || price.equals("USD") || price.equals("GBP")) {
                        price += pressElement.text().split("定价:")[1].split(" ")[2];
                    }
                } else {
                    price = "";
                }
                book.setPrice(price);
                // 评价人数大于1000插入数据到数据库
                if (!rating_sum.equals("") && Integer.parseInt(rating_sum) >= 1000) {
                    System.out.println(++count + " " + book);
                    bookDao.saveBook(book);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("getBook-> end");
    }

}
