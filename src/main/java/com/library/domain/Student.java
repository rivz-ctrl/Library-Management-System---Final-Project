package com.library.domain;

public class Student extends User{

    public Student(String userName, List<Item> borrowedItems) {
        super(userName, borrowedItems);
    }

    @Override
    public int getBorrowCap() {
        return 0;
    }
}
