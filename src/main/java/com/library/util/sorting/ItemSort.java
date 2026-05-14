package com.library.util.sorting;

import com.library.domain.Item;

import java.util.Comparator;

public class ItemSort {

    /**
     * sorts items ascendingly alphabetically by title
     * @return sorting
     */
    public static Comparator<Item> byTitleAscend() {
        return Comparator.comparing(item -> item.getTitle().toLowerCase());
    }

    /**
     * sorts items descendingly alphabetically by title
     * @return sorting
     */
    public static Comparator<Item> byTitleDescend() {
        return Comparator.comparing((Item item) -> item.getTitle().toLowerCase()).reversed();
    }

    /**
     * sorts items by their availability (available>borrowed>missing)
     * @return sorting
     */
    public static Comparator<Item> byStatus(){
        return Comparator.comparing(Item::getStatus);
    }





}
