package com.library.domain;

import com.library.interfaces.Reportable;

import java.util.List;

public class Admin extends User implements Reportable {
    private String adminId;
    private Library library;

    public Admin(String userName, List<Item> borrowedItems, String adminId, Library library) {
        super(userName, borrowedItems);
        this.adminId = adminId;
        this.library = library;
    }

    @Override
    public String makeReport() {

        List<Item> items = library.getItems();
        StringBuilder sb = new StringBuilder();

        sb.append("____LIBRARY REPORT____\n\n");
        sb.append("*AVAILABLE*\n");

        for(Item item : items) {
            if (item.getStatus() == Item.ItemStatus.AVAILABLE) sb.append(item.getItemInfo()).append("\n");
        }

        sb.append("\n*BORROWED*\n");
        for (Item item : items) {
            if(item.getStatus() == Item.ItemStatus.BORROWED) sb.append(item.getItemInfo())
                    .append("\n");
        }


        sb.append("\n*MISSING*\n");
        for(Item item : items) {
            if(item.getStatus() == Item.ItemStatus.MISSING) sb.append(item.getItemInfo()).append("\n");
        }

        return sb.toString();

    }

    @Override
    public void exportReport(String filePath) {

    }

    public Library getLibrary() {
        return library;
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
