package com.library.domain;

public class Book extends Item{
    private String isbn;
    private String author;
    private String genre;
    private static final String ISBN_CODE = " ";

    public Book(String title, ItemStatus status, String isbn, String author, String genre) {
        super(title, status);
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String getItemInfo() {
        return "";
    }
}
