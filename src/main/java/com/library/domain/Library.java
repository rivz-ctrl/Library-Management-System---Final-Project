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

    /**
     * adds user to library
     * @param user user to be added
     * @throws InvalidInputException if user =  null
     */
    public void addUser(User user) throws InvalidInputException{
        if(user == null) throw new InvalidInputException("User cannot be = null");
        if (users.containsKey(user.getUserId())) {
            throw new InvalidInputException("Id already exists:" + user.getUserId());
        }
        users.put(user.getUserId(), user);
    }

    /**
     * allows user to borrow item
     * @param userId trying to borrow
     * @param itemId item to be borrowed
     */
    public void borrowItem(String userId, String itemId){

    }

    /**
     * allows user to return item
     * @param userId user trying to return
     * @param itemId to be returned
     */
    public void returnItem(String userId, String itemId){

    }

    /**
     * sorting item in library by ID
     * @param itemId id searched for
     * @return the searched item or null
     */
    private Item findItemById(String itemId) {

        return null;
    }


    public List<Item> getItems() {
        return items;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
