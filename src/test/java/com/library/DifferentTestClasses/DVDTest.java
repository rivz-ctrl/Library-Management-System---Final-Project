package com.library.DifferentTestClasses;

import com.library.domain.DVD;
import com.library.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    @DisplayName("getItemInfo contains title and director")
    public void testGetItemInfo() {
        DVD dvd = new DVD("Waves", Item.ItemStatus.AVAILABLE, "Ryan Coogler", 240);
        assertTrue(dvd.getItemInfo().contains("Waves"));
        assertTrue(dvd.getItemInfo().contains("Ryan Coogler"));
    }

    @Test
    @DisplayName("status from available to borrowed")
    public void testSettingStatus() {
        DVD dvd = new DVD("Waves", Item.ItemStatus.AVAILABLE, "Ryan Coogler", 240);
        dvd.setStatus(Item.ItemStatus.BORROWED);
        assertEquals(Item.ItemStatus.BORROWED, dvd.getStatus());
    }
}
