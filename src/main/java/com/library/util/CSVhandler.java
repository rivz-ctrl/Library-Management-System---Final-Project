package com.library.util;

import com.library.domain.Item;
import com.library.domain.Library;
import com.library.domain.User;
import com.library.domain.exceptions.InvalidInputException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class CSVhandler {
    private static final int ITEM_ISSUE_NUM = 0;
    private static final int ITEM_ID        = 1;
    private static final int ITEM_TYPE      = 2;
    private static final int ITEM_TITLE     = 3;
    private static final int ITEM_STATUS    = 4;
    private static final int ITEM_DURATION  = 5;
    private static final int ITEM_DIRECTOR  = 6;
    private static final int ITEM_ISBN      = 7;
    private static final int ITEM_PUBLISHER = 8;
    private static final int ITEM_AUTHOR    = 9;
    private static final int ITEM_GENRE     = 10;

    private static final int USER_TYPE         = 1;
    private static final int USER_NAME         = 2;
    private static final int USER_SPECIFIC_ID  = 3;
    private static final int USER_ID           = 4;

    private static User createUser(String[] f, Library library) throws InvalidInputException {

    }


    private static Item createItem(String[] f) throws InvalidInputException {

    }

    public static void loadItems(String filePath, Library library) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; } // skip header
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] fields = line.split(",", -1);
                for (int i = 0; i < fields.length; i++) fields[i] = fields[i].trim();

                try {
                    Item item = createItem(fields);
                    if (item != null) library.addItem(item);
                } catch (InvalidInputException e) {
                    System.err.println("invalid item row, skipping said row:" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("couldnt load items from CSV:" + e.getMessage());
        }
    }




}
