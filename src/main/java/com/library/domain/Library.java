package com.library.domain;

import java.util.List;
import java.util.Map;

public class Library {
    private List<Item> items;
    private Map<String, User> users;

    public Library(List<Item> items, Map<String, User> users) {
        this.items = items;
        this.users = users;
    }


}
