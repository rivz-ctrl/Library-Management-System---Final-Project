package com.library.domain;

public class Teacher extends User{
    public Teacher(String userName, List<Item> borrowedItems) {
        super(userName, borrowedItems);
    }

    @Override
    public int getBorrowCap() {
        return 0;
    }
}
