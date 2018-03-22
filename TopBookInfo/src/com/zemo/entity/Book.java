package com.zemo.entity;

/**
 * @author Liam
 * @version 1.0 2018/3/20
 */
public class Book {
    /**
     * 书名
     */
    private String name;
    /**
     * 评分
     */
    private String rating;
    /**
     * 评价人数
     */
    private String ratingPeople;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版社
     */
    private String publishers;
    /**
     * 出版日期
     */
    private String pulicationDate;
    /**
     * 价格
     */
    private String price;

    public Book(String name, String rating, String ratingPeople, String author, String publishers, String pulicationDate, String price) {
        this.name = name;
        this.rating = rating;
        this.ratingPeople = ratingPeople;
        this.author = author;
        this.publishers = publishers;
        this.pulicationDate = pulicationDate;
        this.price = price;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", ratingPeople='" + ratingPeople + '\'' +
                ", author='" + author + '\'' +
                ", publishers='" + publishers + '\'' +
                ", pulicationDate='" + pulicationDate + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatingPeople() {
        return ratingPeople;
    }

    public void setRatingPeople(String ratingPeople) {
        this.ratingPeople = ratingPeople;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishers() {
        return publishers;
    }

    public void setPublishers(String publishers) {
        this.publishers = publishers;
    }

    public String getPulicationDate() {
        return pulicationDate;
    }

    public void setPulicationDate(String pulicationDate) {
        this.pulicationDate = pulicationDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
