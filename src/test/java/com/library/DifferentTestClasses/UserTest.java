package com.library.DifferentTestClasses;

import com.library.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
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

    @Test
    @DisplayName("Student canBorrow DVD => false")
    public void testStudent_canBorrowDVD() {
        assertFalse(student.canBorrow(book));
    }

    @Test
    @DisplayName("Student alr at 5 books canBorrow item => false")
    public void testStudent_canBorrowWhenAlreadyAtCap() {
        for (int i = 0; i < 5; i++) {
            student.getBorrowedItems().add(new Book("Book"+i,Item.ItemStatus.BORROWED, "9781254307141", "Author", "Genre"));
        }
        assertFalse(student.canBorrow(book));
    }


    @Test
    @DisplayName("Student canBorrow Book => true")
    public void testTeacher_canBorrowBook() {
        assertTrue(teacher.canBorrow(book));
    }

    @Test
    @DisplayName("Student canBorrow DVD => false")
    public void testTeacher_canBorrowDVD() {
        assertFalse(teacher.canBorrow(book));
    }

    @Test
    @DisplayName("Student alr at 10 books canBorrow item => false")
    public void testTeacher_canBorrowWhenAlreadyAtCap() {
        for (int i = 0; i < 10; i++) {
            teacher.getBorrowedItems().add(new Book("Book"+i,Item.ItemStatus.BORROWED, "9781254307141", "Author", "Genre"));
        }
        assertFalse(teacher.canBorrow(book));

    }

    @Test
    @DisplayName("Admin canBorrow Book =? false")
    public void testAdmin_canBorrow() {
        assertFalse(admin.canBorrow(book));
    }






}
