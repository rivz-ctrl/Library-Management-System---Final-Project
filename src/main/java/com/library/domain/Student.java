package com.library.domain;

public class Student extends User{
    private String studentId;
    private static final int MAX_BOOKS = 5;

    public Student(String userName, List<Item> borrowedItems, String studentId) {
        super(userName, borrowedItems);
        this.studentId = studentId;
    }

    @Override
    public int getBorrowCap() {
        return 0;
    }
}
