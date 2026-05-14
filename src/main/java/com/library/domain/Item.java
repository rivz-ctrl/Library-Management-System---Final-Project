package com.library.domain;

public abstract class Item {

    protected String itemId;
    protected String title;
    protected ItemStatus status;

    static int nextId = 1;

    public Item(String title, ItemStatus status) {
        this.itemId = String.format("%04d", nextId++);
        this.title = title;
        this.status = ItemStatus.AVAILABLE;
    }

    public enum ItemStatus{
        AVAILABLE,
        BORROWED,
        MISSING
    }

    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public abstract String getItemInfo();

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
}
