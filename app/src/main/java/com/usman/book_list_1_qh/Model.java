package com.usman.book_list_1_qh;


public class Model {

    String bookid,bookname,bookauthore;

    public Model(String bookid, String bookname, String bookauthore) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.bookauthore = bookauthore;
    }

    public String getBookid() {
        return bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public String getBookauthore() {
        return bookauthore;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setBookauthore(String bookauthore) {
        this.bookauthore = bookauthore;
    }
}

