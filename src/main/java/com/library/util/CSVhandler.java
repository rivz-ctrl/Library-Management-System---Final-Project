package com.library.util;

import com.library.domain.*;
import com.library.domain.exceptions.InvalidInputException;

import java.io.*;
import java.util.ArrayList;

public class CSVhandler {
    private static final int ITEM_ISSUE_NUM = 0;
    private static final int ITEM_TYPE      = 1;
    private static final int ITEM_TITLE     = 2;
    private static final int ITEM_STATUS    = 3;
    private static final int ITEM_DURATION  = 4;
    private static final int ITEM_DIRECTOR  = 5;
    private static final int ITEM_ISBN      = 6;
    private static final int ITEM_PUBLISHER = 7;
    private static final int ITEM_AUTHOR    = 8;
    private static final int ITEM_GENRE     = 9;

    private static final int USER_TYPE         = 0;
    private static final int USER_NAME         = 1;
    private static final int USER_SPECIFIC_ID  = 2;

    private static User createUser(String[] f, Library library) throws InvalidInputException {
        if (f.length < 3) throw new InvalidInputException("Not enough columns in user row.");

        String type       = f[USER_TYPE];
        String userName   = f[USER_NAME];
        String specificId = f[USER_SPECIFIC_ID];

        switch (type) {
            case "Student": return new Student(userName, new ArrayList<>(), specificId);
            case "Teacher": return new Teacher(userName, new ArrayList<>(), specificId);
            case "Admin":   return new Admin(userName, new ArrayList<>(), specificId, library);
            default: throw new InvalidInputException("unknown user type:" + type);
        }
    }

    private static Item.ItemStatus createStatus(String status) {
        switch (status.toUpperCase()) {
            case "BORROWED": return Item.ItemStatus.BORROWED;
            case "MISSING":  return Item.ItemStatus.MISSING;
            default:         return Item.ItemStatus.AVAILABLE;
        }
    }

    public static void loadItems(String filePath, Library library) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; }
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] fields = line.split(",", -1);
                for (int i = 0; i < fields.length; i++) fields[i] = fields[i].trim();

                try {
                    Item item = createItem(fields);
                    if (item != null) library.addItem(item);
                } catch (InvalidInputException e) {
                    System.err.println("invalid item row, skipping said row: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("couldnt load items from CSV: " + e.getMessage());
        }
    }


    public static void loadUsers(String filePath, Library library) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; }
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] fields = line.split(",", -1);
                for (int i = 0; i < fields.length; i++) fields[i] = fields[i].trim();

                try {
                    User user = createUser(fields, library);
                    if (user != null) library.addUser(user);
                } catch (InvalidInputException e) {
                    System.err.println("skipping the invalid user row: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("couldnt load users from CSV: " + e.getMessage());
        }
    }



    private static Item createItem(String[] f) throws InvalidInputException {
        if (f.length < 10) throw new InvalidInputException("item row doesnt have enoguh columns");

        String type   = f[ITEM_TYPE];
        String title  = f[ITEM_TITLE];
        Item.ItemStatus status = createStatus(f[ITEM_STATUS]);

        switch (type) {
            case "Book": {
                return new Book(title, status, f[ITEM_ISBN], f[ITEM_AUTHOR], f[ITEM_GENRE]);
            }
            case "DVD": {
                int duration = f[ITEM_DURATION].isEmpty() ? 0 : Integer.parseInt(f[ITEM_DURATION]);
                return new DVD(title, status, f[ITEM_DIRECTOR], duration);
            }
            case "Magazine": {
                int issueNumber = f[ITEM_ISSUE_NUM].isEmpty() ? 0 : Integer.parseInt(f[ITEM_ISSUE_NUM]);
                return new Magazine(title, status, f[ITEM_PUBLISHER], issueNumber);
            }
            default:
                throw new InvalidInputException("unknown item type:" + type);
        }
    }



    private static String itemToCSV(Item item) {
        if (item instanceof Book) {
            Book b = (Book) item;
            return ",,Book," + b.getTitle() + "," + b.getStatus()
                    + ",,," + b.getIsbn() + ",," + b.getAuthor() + "," + b.getGenre();
        } else if (item instanceof DVD) {
            DVD d = (DVD) item;
            return ",,DVD," + d.getTitle() + "," + d.getStatus()
                    + "," + d.getDuration() + "," + d.getDirector() + ",,,";
        } else if (item instanceof Magazine) {
            Magazine m = (Magazine) item;
            return m.getIssueNumber() + ",Magazine," + m.getTitle() + "," + m.getStatus()
                    + ",,,," + m.getPublisher() + ",,";
        }
        return "";
    }

    private static String userToCSV(User user) {
        if (user instanceof Student) {
            return "Student," + user.getUserName() + "," + ((Student) user).getStudentId();
        } else if (user instanceof Teacher) {
            return "Teacher," + user.getUserName() + "," + ((Teacher) user).getTeacherId();
        } else if (user instanceof Admin) {
            return "Admin," + user.getUserName() + "," + ((Admin) user).getAdminId();
        }
        return "";
    }

    public static void backupItems(String filePath, Library library) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("issueNumber,type,title,status,duration,director,isbn,publisher,author,genre");
            writer.newLine();

            for (Item item : library.getItems()) {
                writer.write(itemToCSV(item));
                writer.newLine();
            }
            System.out.println("Items backed up to: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to backup items: " + e.getMessage());
        }
    }


    public static void backupUsers(String filePath, Library library) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("type,userName,specificId");
            writer.newLine();

            for (User user : library.getUsers().values()) {
                writer.write(userToCSV(user));
                writer.newLine();
            }
            System.out.println("Users backed up to: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to backup users: " + e.getMessage());
        }
    }

}

