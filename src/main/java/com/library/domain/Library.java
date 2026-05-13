package com.library.domain;

import com.library.domain.exceptions.InvalidInputException;

import java.util.List;
import java.util.Map;

public class Library {
    private List<Item> items;
    private Map<String, User> users;

    public Library(List<Item> items, Map<String, User> users) {
        this.items = items;
        this.users = users;
    }

    /**
     * adds item to library
     * @param item item to be added
     * @throws InvalidInputException if item == null
     */
    public void addItem(Item item) throws InvalidInputException{
        if (item == null) throw new InvalidInputException("Item cannot be null");
        items.add(item);
    }

}
