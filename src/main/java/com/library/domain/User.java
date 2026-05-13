package com.library.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    protected String userId;
    protected String userName;
    protected List<Item> borrowedItems;

    int nextId = 1;

    public User(String userName, List<Item> borrowedItems) {
        this.userId = String.format("%04d", nextId++);
        this.userName = userName;
        this.borrowedItems = new ArrayList<>();
    }

    /**
     * checks if user allowed to borrow
     * @param item item to be borrowed
     * @return whether item can be borrowed
     */
    public abstract boolean canBorrow(Item item);

    public abstract int getBorrowCap();

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public List<Item> getBorrowedItems() {
        return borrowedItems;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", borrowedItems=" + borrowedItems +
                '}';
    }
}
