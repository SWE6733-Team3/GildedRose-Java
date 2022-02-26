package com.gildedrose;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    // Added a SetItems(Item[]) to make test case writing easier
    // to quickly set the array, you can use the syntax:
    // SetItems(new Item[]{itemA, itemB, itemC});
    // OR
    // SetItems(new Item[]{new Item("name", sellIn, quality), new Item("name",
    // sellIn, quality)});

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void setItems() {

    }

    @Test
    void updateQuality() {
    }

    @Test
    void setItemFlags() {
        // create a set of test items and instantiate GildedRose class
        Item testItemC = new Item("Conjured Test Item", 0, 10);
        Item testItemL = new Item("Sulfuras Test Item", 10, 10);
        Item testItemA = new Item("Aged Brie Test", 10, 0);
        Item testItemA2 = new Item("test concert tickets", 10, 10);
        Item testItemN = new Item("Normal Test Item", 10, 10);
        GildedRose testGildedRose = new GildedRose();

        // assert defaults are false
        assertFalse(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertFalse(testGildedRose.isPastSellIn);
        assertFalse(testGildedRose.isUnsellable);

        // test conjured item
        testGildedRose.setItemFlags(testItemC);
        assertFalse(testGildedRose.isAging);
        assertTrue(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertTrue(testGildedRose.isPastSellIn);
        assertFalse(testGildedRose.isUnsellable);

        // test legendary item
        testGildedRose.setItemFlags(testItemL);
        assertFalse(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertTrue(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertFalse(testGildedRose.isPastSellIn);
        assertFalse(testGildedRose.isUnsellable);

        // test aging item - "Aged Brie"
        testGildedRose.setItemFlags(testItemA);
        assertTrue(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertFalse(testGildedRose.isPastSellIn);
        assertTrue(testGildedRose.isUnsellable);

        // test aging item - "concert"
        testGildedRose.setItemFlags(testItemA2);
        assertTrue(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertFalse(testGildedRose.isPastSellIn);
        assertFalse(testGildedRose.isUnsellable);

        // test normal item
        testGildedRose.setItemFlags(testItemN);
        assertFalse(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertTrue(testGildedRose.isNormal);
        assertFalse(testGildedRose.isPastSellIn);
        assertFalse(testGildedRose.isUnsellable);

    }

    @Test
    void updateAgingQuality() {
    }

    @Test
    void updateConjuredQuality() {
    }

    @Test
    void updateNormalQuality() {
    }

    @Test
    void decrementSellIn() {
    }

    @Test
    void applyQualityLimits() {
    }
}
