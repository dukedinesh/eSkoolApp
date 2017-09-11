package com.eduschool.eduschoolapp.BookDetailsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 28/08/2017.
 */

public class BookListBean1 {
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

