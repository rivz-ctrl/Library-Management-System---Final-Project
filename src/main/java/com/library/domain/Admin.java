package com.library.domain;

import java.util.List;

public class Admin extends User{
    private String adminId;

    public Admin(String userName, List<Item> borrowedItems, String adminId) {
        super(userName, borrowedItems);
        this.adminId = adminId;
    }

    @Override
    public boolean canBorrow(Item item) {
        return false;
    }

    @Override
    public int getBorrowCap() {
        return 0;
    }

    public String getAdminId() {
        return adminId;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
