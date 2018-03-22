# DoubanBookInfo
爬取豆瓣图书关于"互联网，编程，算法"方面的书籍将评价人数超过1000的保存到数据库

Book
------
### 书籍信息: 书名,评分,评价人数,作者,出版社,出版日期,价格
PageUtil
--------
### 解析页面数据的工具类
#### getUrls(String tag): 获取tag标签对应的所有url
#### getBook(CopyOnWriteArrayList<String> urls): 获取urls对应的详情页书籍信息
BookDao
-------
### 数据库相关操作
#### saveBook(Book book):  保存书籍信息到数据库
#### saveUrl((String url)) 保存获取到的url到数据库
Main
------
### 主函数
