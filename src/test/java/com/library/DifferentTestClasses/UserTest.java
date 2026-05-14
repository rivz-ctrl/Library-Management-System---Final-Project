package com.library.DifferentTestClasses;

import com.library.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class UserTest {

    private Student student;
    private Teacher teacher;
    private Admin admin;
    private Book book;
    private DVD dvd;

    @BeforeEach
    void preSetting() {
        User.nextId = 1;
        Item.nextId = 1;
        Library library = new Library();
        teacher = new Teacher("Carti", new ArrayList<>(), "T2001");
        student = new Student("Bleeod", new ArrayList<>(), "S1001");
        admin   = new Admin("Jeffery", new ArrayList<>(), "A3001", library);
        book    = new Book("Rest In Bass", Item.ItemStatus.AVAILABLE, "9781254307141", "Che", "Music");
        dvd     = new DVD("Waves", Item.ItemStatus.AVAILABLE, "Ryan Coogler", 240);
    }

    @Test
    @DisplayName("Student canBorrow Book => true")
    public void testStudent_canBorrowBook() {
        assertTrue(student.canBorrow(book));
    }
}
