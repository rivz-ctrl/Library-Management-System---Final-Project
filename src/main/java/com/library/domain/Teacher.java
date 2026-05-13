package com.library.domain;

public class Teacher extends User{
    private String teacherId;
    private static final int MAX_BOOKS = 10;



    public Teacher(String userName, List<Item> borrowedItems) {
        super(userName, borrowedItems);
    }

    @Override
    public int getBorrowCap() {
        return 0;
    }
}
