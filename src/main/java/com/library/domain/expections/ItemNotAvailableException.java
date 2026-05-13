package com.library.domain.expections;

public class ItemNotAvailableException extends Exception {
    public ItemNotAvailableException(String msg) {
        super(msg);
    }
}
