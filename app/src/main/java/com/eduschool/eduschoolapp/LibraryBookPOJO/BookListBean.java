package com.eduschool.eduschoolapp.LibraryBookPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/24/2017.
 */

public class BookListBean {

    @SerializedName("book_list")
    @Expose
    private List<BookList> bookList = null;

    public List<BookList> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookList> bookList) {
        this.bookList = bookList;
    }

}
