package com.library.domain;

public abstract class User {

    protected String userId;
    protected String userName;
    protected List<Item> borrowedItems;

    int nextId = 1;

    public User(String userId, String userName, List<Item> borrowedItems) {
        this.userId = String.format("%04d", nextId++);
        this.userName = userName;
        this.borrowedItems = borrowedItems;
    }
}
