package com.library.DifferentTestClasses;

import com.library.domain.DVD;
import com.library.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class DVDTest {

    @BeforeEach
    void preSetting() {
        Item.nextId = 1;
    }

    @Test
    @DisplayName("Waves,Ryan Coogler,240")
    public void testDVDCreation() {
        DVD dvd = new DVD("Waves", Item.ItemStatus.AVAILABLE, "Ryan Coogler", 240);
        assertEquals("Waves", dvd.getTitle());
        assertEquals("Ryan Coogler", dvd.getDirector());
        assertEquals(240, dvd.getDuration());
        assertEquals(Item.ItemStatus.AVAILABLE, dvd.getStatus());
    }
}
