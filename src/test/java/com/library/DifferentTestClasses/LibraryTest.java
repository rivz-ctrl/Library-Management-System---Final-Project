package com.library.DifferentTestClasses;

import com.library.domain.*;
import com.library.domain.exceptions.BorrowCapException;
import com.library.domain.exceptions.InvalidInputException;
import com.library.domain.exceptions.ItemNotAvailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    @DisplayName("invalid userId throws InvalidInputException")
    public void testBorrowItemUserNotFound() {
        assertThrows(InvalidInputException.class, () -> library.borrowItem("9567", book1.getItemId()));
    }

    @Test
    @DisplayName("invalid itemId throws InvalidInputException")
    public void testBorrowNotFoundItem() {
        assertThrows(InvalidInputException.class, () -> library.borrowItem(student.getUserId(), "6767"));
    }

    @Test
    @DisplayName("item already borrowed throws ItemNotAvailableException")
    public void testBorrowItem_NotAvailable() {
        book1.setStatus(Item.ItemStatus.BORROWED);
        assertThrows(ItemNotAvailableException.class, () -> library.borrowItem(student.getUserId(), book1.getItemId()));
    }

    @Test
    @DisplayName("Student has hit cap of 5 books throws BorrowCapException")
    public void testBorrowItem_studentCapped() throws Exception {
        for (int i = 0; i < 5; i++) {
            Book extra = new Book("Extra " + i, Item.ItemStatus.AVAILABLE, "9781254307141", "X", "Y");
            library.addItem(extra);
            library.borrowItem(student.getUserId(), extra.getItemId());
        }
        assertThrows(BorrowCapException.class, () -> library.borrowItem(student.getUserId(), book1.getItemId()));
    }

    @Test
    @DisplayName("Student tries to borrow DVD throws BorrowCapException")
    public void testStudentBorrowDVD() {
        assertThrows(BorrowCapException.class, () -> library.borrowItem(student.getUserId(), dvd.getItemId()));
    }

    @Test
    @DisplayName("Teacher borrow DVD updates status")
    public void testTeacherBorrowDVD() throws Exception {
        library.borrowItem(teacher.getUserId(), dvd.getItemId());
        assertEquals(Item.ItemStatus.BORROWED, dvd.getStatus());
    }

    @Test
    @DisplayName("Bleeod returns Rest In Bass, status updates, not in borrowedItems")
    public void testReturnItem() throws Exception {
        library.borrowItem(student.getUserId(), book1.getItemId());
        library.returnItem(student.getUserId(), book1.getItemId());
        assertEquals(Item.ItemStatus.AVAILABLE, book1.getStatus());
        assertFalse(student.getBorrowedItems().contains(book1));
    }

    @Test
    @DisplayName("recursiveSearch still gives 1 unique result despite 2 copies existing")
    public void testRecursiveSearchWithDuplicates() {
        List<Item> results = library.recursiveSearchByTitle("rest");
        assertEquals(1, results.size());
        assertEquals("Rest In Bass", results.get(0).getTitle());
    }

    @Test
    @DisplayName("recursiveSearch for inexisting books gives []")
    public void testRecursiveSearchFakeBook() {
        assertTrue(library.recursiveSearchByTitle("gugugaga").isEmpty());
    }


    @Test
    @DisplayName("streamSearching rest still gives 1 unique result despite 2 copies existing")
    public void testStreamSearchWithDuplicates() {
        assertEquals(1, library.streamSearchbyTitle("rest").size());
    }

    @Test
    @DisplayName("streamSearch for inexisting books gives []")
    public void testStreamSearchFakeBook() {
        assertTrue(library.streamSearchbyTitle("jajajajaja").isEmpty());
    }

}
