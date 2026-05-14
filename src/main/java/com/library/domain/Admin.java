package com.library.domain;

import com.library.interfaces.Reportable;
import com.library.util.CSVhandler;
import org.w3c.dom.css.CSSValue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Admin extends User implements Reportable {
    private String adminId;
    private Library library;

    public Admin(String userName, List<Item> borrowedItems, String adminId, Library library) {
        super(userName, borrowedItems);
        this.adminId = adminId;
        this.library = library;
    }

    /**
     * creates a report with all the items according to their status
     * @return the report as a string
     */
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
        String report = makeReport();

        try (FileWriter writer = new FileWriter(filePath)) {

            writer.write(report);
            System.out.println("report exported in:" + filePath);

        } catch (IOException e){

            System.err.println("report export failed:" + e.getMessage());
        }
    }

    /**
     * backup current library to the CSV files
     * @param itemsFilePath path to write items CSV
     * @param usersFilePath path to write users CSV
     */
    public void backupToCSV(String itemsFilePath, String usersFilePath){
        CSVhandler.backupItems(itemsFilePath, library);
        CSVhandler.backupUsers(usersFilePath, library);
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
