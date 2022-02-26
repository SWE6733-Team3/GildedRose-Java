package com.gildedrose;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    //Added a SetItems(Item[]) to make test case writing easier
    //to quickly set the array, you can use the syntax:
    //SetItems(new Item[]{itemA, itemB, itemC});
    // OR
    //SetItems(new Item[]{new Item("name", sellIn, quality), new Item("name", sellIn, quality)});

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
        app.UpdateQuality();
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
        //create a set of test items and instantiate GildedRose class
        Item testItemC = new Item("Conjured Test Item", 0, 10);
        Item testItemL = new Item("Sulfuras Test Item", 10, 10);
        Item testItemA = new Item("Aged Brie Test", 10, 0);
        Item testItemA2 = new Item("test concert tickets",10, 10);
        Item testItemN = new Item("Normal Test Item", 10, 10);
        GildedRose testGildedRose = new GildedRose();

        //assert defaults are false
        assertFalse(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertFalse(testGildedRose.isPastSellIn);
        assertFalse(testGildedRose.isUnsellable);

        //test conjured item
        testGildedRose.SetItemFlags(testItemC);
        assertFalse(testGildedRose.isAging);
        assertTrue(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertTrue(testGildedRose.isPastSellIn);
        assertFalse(testGildedRose.isUnsellable);

        //test legendary item
        testGildedRose.SetItemFlags(testItemL);
        assertFalse(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertTrue(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertFalse(testGildedRose.isPastSellIn);
        assertFalse(testGildedRose.isUnsellable);

        //test aging item - "Aged Brie"
        testGildedRose.SetItemFlags(testItemA);
        assertTrue(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertFalse(testGildedRose.isPastSellIn);
        assertTrue(testGildedRose.isUnsellable);

        //test aging item - "concert"
        testGildedRose.SetItemFlags(testItemA2);
        assertTrue(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertFalse(testGildedRose.isNormal);
        assertFalse(testGildedRose.isPastSellIn);
        assertFalse(testGildedRose.isUnsellable);

        //test normal item
        testGildedRose.SetItemFlags(testItemN);
        assertFalse(testGildedRose.isAging);
        assertFalse(testGildedRose.isConjured);
        assertFalse(testGildedRose.isLegendary);
        assertTrue(testGildedRose.isNormal);
        assertFalse(testGildedRose.isPastSellIn);
        assertFalse(testGildedRose.isUnsellable);

    }

    @Test
    void updateAgingQuality() {
        //create a set of test items and instantiate GildedRose class
        Item testItemA = new Item("Test Item > 10", 11, 10);
        Item testItemB = new Item("Test Item > 5", 9, 10);
        Item testItemC = new Item("Test Item > 0)", 4, 10);
        Item testItemD = new Item("Test Item <=0", -1, 10);
        GildedRose testGildedRose = new GildedRose();
        int oldQuality;

        oldQuality = testItemA.quality;
        testGildedRose.SetItemFlags(testItemA);
        testGildedRose.UpdateAgingQuality(testItemA);
        assertTrue(testItemA.sellIn > 10 && (testItemA.quality == oldQuality+1));
        assertFalse((testItemD.sellIn <= 10 && testItemD.sellIn > 5) && (testItemA.quality == oldQuality+2));
        assertFalse((testItemD.sellIn <= 5 && testItemD.sellIn > 0) && (testItemA.quality == oldQuality+3));
        assertFalse(testGildedRose.isPastSellIn && testItemA.quality == 0);

        oldQuality = testItemB.quality;
        testGildedRose.SetItemFlags(testItemB);
        testGildedRose.UpdateAgingQuality(testItemB);
        assertFalse(testItemB.sellIn > 10 && (testItemB.quality == oldQuality+1));
        assertTrue((testItemB.sellIn <= 10 && testItemB.sellIn > 5) && (testItemB.quality == oldQuality+2));
        assertFalse((testItemB.sellIn <= 5 && testItemB.sellIn > 0) && (testItemB.quality == oldQuality+3));
        assertFalse(testGildedRose.isPastSellIn && testItemB.quality == 0);
        
        oldQuality = testItemC.quality;
        testGildedRose.SetItemFlags(testItemC);
        testGildedRose.UpdateAgingQuality(testItemC);
        assertFalse(testItemC.sellIn > 10 && (testItemC.quality == oldQuality + 1));
        assertFalse((testItemC.sellIn <= 10 && testItemC.sellIn > 5) && (testItemC.quality == oldQuality + 2));
        assertTrue((testItemC.sellIn <= 5 && testItemC.sellIn > 0) && (testItemC.quality == oldQuality + 3) );
        assertFalse(testGildedRose.isPastSellIn );

        oldQuality = testItemD.quality;
        testGildedRose.SetItemFlags(testItemD);
        testGildedRose.UpdateAgingQuality(testItemD);
        assertFalse(testItemD.sellIn > 10 && (testItemD.quality == oldQuality + 1));
        assertFalse((testItemD.sellIn <= 10 && testItemD.sellIn > 5) && (testItemD.quality == oldQuality + 2) );
        assertFalse((testItemD.sellIn <= 5 && testItemD.sellIn > 0) && (testItemD.quality == oldQuality + 3));
        assertTrue(testGildedRose.isPastSellIn && testItemD.quality == 0);        
    }

    @Test
    void updateConjuredQuality() {
    }

    @Test
    void updateNormalQuality() {
    }

    @Test
    void decrementSellIn() {
        Item testItemA = new Item("Test Item > 10", 11, 10);
        GildedRose testGildedRose = new GildedRose();
        int oldSellin = testItemA.sellIn;

        testGildedRose.DecrementSellIn(testItemA);
        assertTrue(testItemA.sellIn == (oldSellin - 1));
    }

    @Test
    void applyQualityLimits() {
    }
}
