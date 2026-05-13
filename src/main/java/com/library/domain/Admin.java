package com.library.domain;

public class Admin extends User{
    public Admin(String userName, List<Item> borrowedItems) {
        super(userName, borrowedItems);
    }

    @Override
    public int getBorrowCap() {
        return 0;
    }
}
