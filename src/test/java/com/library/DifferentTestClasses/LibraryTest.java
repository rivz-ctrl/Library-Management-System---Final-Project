package com.library.DifferentTestClasses;

import com.library.domain.*;
import com.library.domain.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;

public class LibraryTest {
    private Library library;
    private Student student;
    private Teacher teacher;
    private Book book1;
    private Book book2;
    private Book book3;
    private DVD dvd;

    @BeforeEach
    void preSetting() throws InvalidInputException {

        Item.nextId = 1;

        User.nextId = 1;
        library = new Library();
        student = new Student("Bleeod", new ArrayList<>(), "S1001");
        teacher = new Teacher("Carti", new ArrayList<>(), "T2001");

        book1 = new Book("Rest In Bass", Item.ItemStatus.AVAILABLE, "9781254307141", "Che", "Music");
        book2 = new Book("Kill Your Idols", Item.ItemStatus.AVAILABLE, "9781254307143", "Bleeod", "Philosophy");
        book3 = new Book("Rest In Bass", Item.ItemStatus.AVAILABLE, "9781254307141", "Che", "Music"); // duplicate copy
        dvd   = new DVD("Waves", Item.ItemStatus.AVAILABLE, "Ryan Coogler", 240);

        library.addUser(student);
        library.addUser(teacher);
        library.addItem(book1);
        library.addItem(book2);
        library.addItem(book3);
        library.addItem(dvd);
    }

    @Test
    @DisplayName("adding null item throws InvalidInputException")
    public void testAddItemNUll() {
        assertThrows(InvalidInputException.class, () -> library.addItem(null));
    }

    @Test
    @DisplayName("adding null user throws InvalidInputException")
    public void testAddUserNull() {
        assertThrows(InvalidInputException.class, () -> library.addUser(null));
    }

    @Test
    @DisplayName("adding already existing user(same id) throws InvalidInputException")
    public void testAddUserDupe() {
        assertThrows(InvalidInputException.class, () -> library.addUser(student));
    }


}
