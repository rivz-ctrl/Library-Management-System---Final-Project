package com.library.domain;

public class Magazine extends Item{
    private String publisher;
    private int issueNumber;

    public Magazine(String title, ItemStatus status, String publisher, int issueNumber) {
        super(title, status);
        this.publisher = publisher;
        this.issueNumber = issueNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public String getItemInfo() {
        return "Magazine{" +
                "publisher='" + publisher + '\'' +
                ", issueNumber=" + issueNumber +
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
