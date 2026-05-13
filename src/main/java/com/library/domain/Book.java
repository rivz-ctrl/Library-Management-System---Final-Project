package com.library.domain;

public class Book extends Item{
    private String isbn;
    private String author;
    private String genre;
    private static final String ISBN_CODE = "^97[89]\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d$";

    public Book(String title, ItemStatus status, String isbn, String author, String genre) {
        super(title, status);
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

    /**
     * checks if isbn code is valid
     * @return whether its valid
     */
    public boolean checkISBN() {
        if (isbn == null) return false;
        return isbn.matches(ISBN_CODE);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String getItemInfo() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", itemId='" + itemId + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public String toString() {
        return getItemInfo();
    }
}
