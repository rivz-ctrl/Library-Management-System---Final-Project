package com.library.domain;

public class Admin extends User{
    private String adminId;

    public Admin(String userName, List<Item> borrowedItems, String adminId) {
        super(userName, borrowedItems);
        this.adminId = adminId;
    }

    @Override
    public int getBorrowCap() {
        return 0;
    }
}
