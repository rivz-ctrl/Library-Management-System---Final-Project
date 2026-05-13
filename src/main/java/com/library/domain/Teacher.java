package com.library.domain;

public class Teacher extends User{
    private String teacherId;
    private static final int MAX_ITEMS = 10;


    public Teacher(String userName, List<Item> borrowedItems, String teacherId) {
        super(userName, borrowedItems);
        this.teacherId = teacherId;
    }

    @Override
    public int getBorrowCap() {
        return MAX_ITEMS;
    }
}
