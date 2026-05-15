package com.library.DifferentTestClasses;

import com.library.domain.*;
import com.library.domain.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

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
}
