package com.library.domain;

import java.util.List;

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

    public String getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
