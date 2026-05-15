package com.library.DifferentTestClasses;

import com.library.domain.Item;
import com.library.domain.Magazine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class MagazineTest {
    @BeforeEach
    void setUp() {
        Item.nextId = 1;
    }

    @Test
    @DisplayName("Fader, issue 555")
    public void testCreatingMag() {
        Magazine magazine = new Magazine("Fader", Item.ItemStatus.AVAILABLE, "KTO", 555);
        assertEquals("National Geographic", magazine.getTitle());
        assertEquals("KTO", magazine.getPublisher());
        assertEquals(555, magazine.getIssueNumber());
        assertEquals(Item.ItemStatus.AVAILABLE, magazine.getStatus());
    }

    @Test
    @DisplayName("status = missing")
    public void testSettingStatus() {
        Magazine magazine = new Magazine("Fader", Item.ItemStatus.AVAILABLE, "KTO", 555);
        magazine.setStatus(Item.ItemStatus.MISSING);
        assertEquals(Item.ItemStatus.MISSING, magazine.getStatus());
    }
}
