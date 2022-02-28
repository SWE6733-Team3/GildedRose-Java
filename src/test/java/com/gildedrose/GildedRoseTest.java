package com.gildedrose;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    GildedRose testGildedRose;
    GildedRose testGildedRoseNoItems;

    Item testItemC1;
    Item testItemC2;
    Item testItemL;
    Item testItemN1;
    Item testItemN2;
    Item testItemA1;
    Item testItemA2;
    Item testItemA3;
    Item testItemA4;
    Item testItemA5;
    Item testItemQ1;
    Item testItemQ2;

    Item[] testItemsSet;
    Item[] testAgingSet;
    // Added a SetItems(Item[]) to make test case writing easier
    // to quickly set the array, you can use the syntax:
    // SetItems(new Item[]{itemA, itemB, itemC});
    // OR
    // SetItems(new Item[]{new Item("name", sellIn, quality), new Item("name",
    // sellIn, quality)});

    @BeforeEach
    void setUp() {
        //create a set of test items and instantiate GildedRose class
        testItemC1 = new Item("Conjured Test Item", 10, 10);
        testItemC2 = new Item("Conjured Test Item", 0, 10);
        testItemL = new Item("Sulfuras Test Item", 10, 10);
        testItemN1 = new Item("Normal Test Item", 10, 10);
        testItemN2 = new Item("Normal Test Item", 0, 10);
        testItemA1 = new Item("Aged Brie Test", 3, 10);
        testItemA2 = new Item("Aged Brie Test", 7, 10);
        testItemA3 = new Item("Aged Brie Test", 12, 10);
        testItemA4 = new Item("Aged Brie Test", 0, 10);
        testItemA5 = new Item("test concert tickets",7, 10);
        testItemQ1 = new Item("Too High Quality Test Item", 10, 100);
        testItemQ2 = new Item("Too Low Quality Test Item", 10, -100);

        testItemsSet = new Item[]{testItemC1,testItemL, testItemN1, testItemA1, testItemA5};
        testAgingSet = new Item[]{testItemA1, testItemA2, testItemA3, testItemA4};

        testGildedRoseNoItems = new GildedRose();
        testGildedRose = new GildedRose(testItemsSet);
    }

    @AfterEach
    void tearDown() {
        //create a set of test items and instantiate GildedRose class
        testItemC1 = new Item("Conjured Test Item", 10, 10);
        testItemC2 = new Item("Conjured Test Item", 0, 10);
        testItemL = new Item("Sulfuras Test Item", 10, 10);
        testItemN1 = new Item("Normal Test Item", 10, 10);
        testItemN2 = new Item("Normal Test Item", 0, 10);
        testItemA1 = new Item("Aged Brie Test", 3, 10);
        testItemA2 = new Item("Aged Brie Test", 7, 10);
        testItemA3 = new Item("Aged Brie Test", 12, 10);
        testItemA4 = new Item("Aged Brie Test", 0, 10);
        testItemA5 = new Item("test concert tickets",7, 10);
        testItemQ1 = new Item("Too High Quality Test Item", 10, 100);
        testItemQ2 = new Item("Too Low Quality Test Item", 10, -100);

        testItemsSet = new Item[]{testItemC1,testItemL, testItemN1, testItemA1, testItemA5};
        testAgingSet = new Item[]{testItemA1, testItemA2, testItemA3, testItemA4};

        testGildedRoseNoItems = new GildedRose();
        testGildedRose = new GildedRose(testItemsSet);
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
        //ensuring the two arrays are correct before and after
        assertNull(testGildedRoseNoItems.items);

        //begin creating the set
        testGildedRoseNoItems.setItems(testItemsSet);

        //ensure that it now has items
        assertNotNull(testGildedRoseNoItems.items);
    }

    @Test
    void updateQuality() {
        //test that all quality change paths are called
        for (Item i : testGildedRose.items)
            assertEquals(10, i.quality);
        testGildedRose.updateQuality();
        assertEquals(8, testItemC1.quality);
        assertEquals(80, testItemL.quality);
        assertEquals(9, testItemN1.quality);
        assertEquals(13, testItemA1.quality);
        assertEquals(12, testItemA5.quality);
    }

    @Test
    void setItemFlags() {
        // assert defaults are false
        assertFalse(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertFalse(testGildedRose.discountEligible);


        // test conjured item
        testGildedRose.setItemFlags(testItemC2);
        assertFalse(testGildedRose.isAging);
        assertTrue(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertTrue(testGildedRose.discountEligible);


        // test legendary item
        testGildedRose.setItemFlags(testItemL);
        assertFalse(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertTrue(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertFalse(testGildedRose.discountEligible);


        // test aging item - "Aged Brie"
        testGildedRose.setItemFlags(testItemA1);
        assertTrue(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertTrue(testGildedRose.discountEligible);


        // test aging item - "concert"
        testGildedRose.setItemFlags(testItemA2);
        assertTrue(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertFalse(testGildedRose.discountEligible);


        // test normal item
        testGildedRose.setItemFlags(testItemN1);
        assertFalse(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertTrue(testGildedRose.isNormal);
        assertFalse(testGildedRose.discountEligible);

    }

    @Test
    void updateAgingQuality() {
        assertEquals(10, testAgingSet[0].quality);
        assertEquals(10, testAgingSet[1].quality);
        assertEquals(10, testAgingSet[2].quality);
        assertEquals(10, testAgingSet[3].quality);

        testGildedRose.updateAgingQuality(testAgingSet[0]);
        testGildedRose.updateAgingQuality(testAgingSet[1]);
        testGildedRose.updateAgingQuality(testAgingSet[2]);
        testGildedRose.updateAgingQuality(testAgingSet[3]);

        assertEquals(13, testAgingSet[0].quality);
        assertEquals(12, testAgingSet[1].quality);
        assertEquals(11, testAgingSet[2].quality);
        assertEquals(0, testAgingSet[3].quality);
    }

    @Test
    void updateConjuredQuality() {
        assertEquals(10, testItemC1.quality);
        assertEquals(10, testItemC2.quality);

        testGildedRose.updateConjuredQuality(testItemC1);
        testGildedRose.updateConjuredQuality(testItemC2);

        assertEquals(8, testItemC1.quality);
        assertEquals(6, testItemC2.quality);
    }

    @Test
    void updateNormalQuality() {
            assertEquals(10, testItemN1.quality);
            assertEquals(10, testItemN2.quality);
            testGildedRose.updateNormalQuality(testItemN1);
            testGildedRose.updateNormalQuality(testItemN2);
            assertEquals(9, testItemN1.quality);
            assertEquals(8, testItemN2.quality);
    }

    @Test
    void decrementSellIn() {
        //setting a evaluator to capture the old sell in date vs the new one
        int oldSellin = testItemA3.sellIn;

        //reference an item spun up to decrement
        testGildedRose.decrementSellIn(testItemA3);

        //ensure that the new sellin is the old sellin -1 which is the function being called
        assertEquals(testItemA3.sellIn, oldSellin-1);
    }

    @Test
    void applyQualityLimits() {
        testGildedRose.setItemFlags(testItemQ1);
        testGildedRose.applyQualityLimits(testItemQ1);
        testGildedRose.setItemFlags(testItemQ2);
        testGildedRose.applyQualityLimits(testItemQ2);
        testGildedRose.setItemFlags(testItemL);
        testGildedRose.applyQualityLimits(testItemL);

        assertEquals(50,testItemQ1.quality);
        assertEquals(0,testItemQ2.quality);
        assertEquals(80,testItemL.quality);
    }

    @Test
    void getTypeString() {
        String testString = testGildedRose.getTypeString(testItemL);
        assertEquals("legendary", testString);
        testString = testGildedRose.getTypeString(testItemC1);
        assertEquals("conjured", testString);
        testString = testGildedRose.getTypeString(testItemN1);
        assertEquals("normal", testString);
        testString = testGildedRose.getTypeString(testItemA1);
        assertEquals("aging", testString);
    }

    @Test
    void getItemsAsString() {
        String testString = testGildedRose.getItemsAsString();
        assertTrue(testString.contains("Conjured Test Item"));
        assertTrue(testString.contains("Sulfuras Test Item"));
        assertTrue(testString.contains("Normal Test Item"));
        assertTrue(testString.contains("Aged Brie Test"));
    }
}
