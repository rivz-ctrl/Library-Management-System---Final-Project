package com.library.domain;

import com.library.domain.exceptions.BorrowCapException;
import com.library.domain.exceptions.InvalidInputException;
import com.library.domain.exceptions.ItemNotAvailableException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private List<Item> items;
    private Map<String, User> users;

    public Library(){
        this.items = new ArrayList<>();
        this.users = new HashMap<>();
    }

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
    public void borrowItem(String userId, String itemId) throws InvalidInputException, ItemNotAvailableException, BorrowCapException {
        User user = users.get(userId);
        if(user == null) throw new InvalidInputException("user doesnt exist:" + userId);

        Item item = findItemById(itemId);
        if(item == null) throw new InvalidInputException(("item doesnt exist:" + itemId));

        if(item.getStatus() != Item.ItemStatus.AVAILABLE){
            throw new ItemNotAvailableException(item.getTitle() + "isnt available" + "currently:" + item.getStatus());
        }

        if (!user.canBorrow(item)){
            throw new BorrowCapException(user.getUserName() + "you cant borrow this item, check borrow limit and item type");
        }

        item.setStatus(Item.ItemStatus.BORROWED);
        user.getBorrowedItems().add(item);
    }

    /**
     * allows user to return item
     * @param userId user trying to return
     * @param itemId to be returned
     */
    public void returnItem(String userId, String itemId) throws InvalidInputException{
        User user = users.get(userId);
        if(user == null) throw new InvalidInputException(("user doesnt exist" + userId));
        Item item = findItemById(itemId);
        if(item == null) throw new InvalidInputException("item doesnt exist" + itemId);

        boolean removed = user.getBorrowedItems().remove(item);
        if(!removed) {
            throw new InvalidInputException(item.getTitle() + "wasnt borrowed by" + user.getUserName());
        }
        item.setStatus(Item.ItemStatus.AVAILABLE);
    }

    /**
     * sorting item in library by ID
     * @param itemId id searched for
     * @return the searched item or null
     */
    private Item findItemById(String itemId) {
        for (Item item : items){
            if(item.getItemId().equals(itemId)) return item;
        }
        return null;
    }


    public List<Item> getItems() {
        return items;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
