package com.library.domain;

import java.util.ArrayList;

public abstract class User {

    protected String userId;
    protected String userName;
    protected List<Item> borrowedItems;

    int nextId = 1;

    public User(String userId, String userName, List<Item> borrowedItems) {
        this.userId = String.format("%04d", nextId++);
        this.userName = userName;
        this.borrowedItems = new ArrayList<>();
    }

    public abstract int getBorrowCap();


}
