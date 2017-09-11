package com.eduschool.eduschoolapp.BorrowedBookPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 8/28/2017.
 */

public class BookList {

    @SerializedName("book_id")
    @Expose
    private String bookId;
    @SerializedName("book_no_id")
    @Expose
    private String bookNoId;
    @SerializedName("book_no")
    @Expose
    private String bookNo;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("publish_date")
    @Expose
    private String publishDate;
    @SerializedName("book_available_id")
    @Expose
    private String bookAvailableId;
    @SerializedName("book_available")
    @Expose
    private String bookAvailable;
    @SerializedName("borrow_fromdate")
    @Expose
    private String borrowFromdate;
    @SerializedName("borrow_todate")
    @Expose
    private String borrowTodate;
    @SerializedName("borrowed_by")
    @Expose
    private String borrowedBy;
    @SerializedName("book_category")
    @Expose
    private List<BookCategory> bookCategory = null;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookNoId() {
        return bookNoId;
    }

    public void setBookNoId(String bookNoId) {
        this.bookNoId = bookNoId;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getBookAvailableId() {
        return bookAvailableId;
    }

    public void setBookAvailableId(String bookAvailableId) {
        this.bookAvailableId = bookAvailableId;
    }

    public String getBookAvailable() {
        return bookAvailable;
    }

    public void setBookAvailable(String bookAvailable) {
        this.bookAvailable = bookAvailable;
    }

    public String getBorrowFromdate() {
        return borrowFromdate;
    }

    public void setBorrowFromdate(String borrowFromdate) {
        this.borrowFromdate = borrowFromdate;
    }

    public String getBorrowTodate() {
        return borrowTodate;
    }

    public void setBorrowTodate(String borrowTodate) {
        this.borrowTodate = borrowTodate;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public List<BookCategory> getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(List<BookCategory> bookCategory) {
        this.bookCategory = bookCategory;
    }

}