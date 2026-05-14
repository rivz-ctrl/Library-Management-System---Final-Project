package com.library.util.sorting;

import com.library.domain.User;

import java.util.Comparator;

public class UserSort {

    /**
     * sorts user by name alphabetically ascendingly
     * @return sorting
     */
    public static Comparator<User> byNameAsc() {
        return Comparator.comparing(user -> user.getUserName().toLowerCase());
    }

    /**
     * sorts user by name alphabetically descendingly
     * @return sorting
     */
    public static Comparator<User> byNameDesc() {
        return Comparator.comparing((User user) -> user.getUserName().toLowerCase()).reversed();
    }
}
