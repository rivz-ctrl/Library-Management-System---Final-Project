package com.library.DifferentTestClasses;

import com.library.domain.Book;
import com.library.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {
    @BeforeEach
    void preSetting() {
        Item.nextId = 1;
    }

    @Test
    @DisplayName("RIB by Che -> title: Rest In Bass, author: Che,genre: Music")
    public void testBookCreation() {
        Book book = new Book("Rest In Bass", Item.ItemStatus.AVAILABLE, "9781254307141", "Che", "Music");
        assertEquals("Rest In Bass", book.getTitle());
        assertEquals("Che", book.getAuthor());
        assertEquals("Music", book.getGenre());
        assertEquals("9781254307141", book.getIsbn());
        assertEquals(Item.ItemStatus.AVAILABLE, book.getStatus());
    }
}
