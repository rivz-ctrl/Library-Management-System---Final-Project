package com.library.domain;

import com.library.domain.exceptions.BorrowCapException;
import com.library.domain.exceptions.InvalidInputException;
import com.library.domain.exceptions.ItemNotAvailableException;
import com.library.util.CSVhandler;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * searches for items by title recursively
     * @param title title to be searched
     * @return list of unique items matching the search
     */
    public List<Item> recursiveSearchByTitle(String title){
        List<Item> results = new ArrayList<>();
        recursiveSearchByTitleHelper(title.toLowerCase(), items, 0, results);
        return results;
    }

    private void recursiveSearchByTitleHelper(String title, List<Item> items, int index, List<Item> results){
        if(index >= items.size()) return;
        Item current = items.get(index);
        if(current.getTitle().toLowerCase().contains(title)){
            boolean addedAlr = false;
            for (Item r : results) {
                if (r.getTitle().equalsIgnoreCase(current.getTitle())){
                    addedAlr = true;
                    break;
                }
            }
            if (!addedAlr) results.add(current);
        }
        recursiveSearchByTitleHelper(title, items, index + 1, results);
    }

    /**
     * seaches for items using stream
     * @param title title searched for
     * @return list of unique items matching search
     */
    public List<Item> streamSearchbyTitle(String title){
        return items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(java.util.stream.Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.stream()
                                .filter(item -> list.stream()
                                        .noneMatch(other -> other != item && other.getTitle().equalsIgnoreCase(item.getTitle()) && list.indexOf(other) < list.indexOf(item)))
                                .collect(Collectors.toList())));
    }

    /**
     * searches for a book author
     * @param author author searched
     * @return list of unique books matching author search
     */
    public List<Item> searchByAuthor(String author) {
        return items.stream()
                .filter(item -> item instanceof Book)
                .map(item -> (Book) item)
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(java.util.stream.Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.stream()
                                .filter(book -> list.stream()
                                        .noneMatch(other -> other != book && other.getTitle().equalsIgnoreCase(book.getTitle()) && list.indexOf(other) < list.indexOf(book)))
                                .collect(Collectors.toList())
                ));
    }

    /**
     * loads items and users in CSV files to init library
     * @param itemsFilePath path to items CSV
     * @param usersFilePath path to users CSV
     */
    public void loadFromTheCSV(String itemsFilePath, String usersFilePath){
        CSVhandler.loadItems(itemsFilePath, this);
        CSVhandler.loadUsers(usersFilePath, this);
    }

    /**
     * sorts items in library using the chosen method
     * @param method what to sort by
     */
    public void sortItems(Comparator<Item> method) {
        items.sort(method);
    }

    /**
     * sorts users of the library using the chosen method
     * @param method what to sort by
     */
    public void sortUsers(Comparator<User> method) {

        List<User> userList = new ArrayList<>(users.values());

        userList.sort(method);
        users.clear();

        for (User user : userList) {
            users.put(user.getUserId(), user);
        }
    }

    public List<Item> getItems() {
        return items;
    }



    public Map<String, User> getUsers() {
        return users;
    }
}
