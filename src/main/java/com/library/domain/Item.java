package com.library.domain;

public abstract class Item {

    protected String itemId;
    protected String title;
    protected ItemStatus status;

    int nextId = 1;

    public Item(String title, ItemStatus status) {
        this.itemId = String.format("%04d", nextId++);
        this.title = title;
        this.status = status;
    }
}
