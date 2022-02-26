package com.gildedrose;

class GildedRose {
    Item[] items;
    protected boolean isPastSellIn;
    protected boolean isUnsellable;
    protected boolean isNormal;
    protected boolean isAging;
    protected boolean isLegendary;
    protected boolean isConjured;

    // no arg constructor
    public GildedRose() {
    }

    // arg constructor
    public GildedRose(Item[] items) {
        this.items = items;
    }

    // set items to passed array
    protected void setItems(Item[] items) {
        this.items = items;
    }

    // processes all items and updates their quality using the appropriate method
    // for the given item type
    public void updateQuality() {
        for (Item item : items) {
            setItemFlags(item);

            if (this.isAging)
                updateAgingQuality(item);
            if (this.isConjured)
                updateAgingQuality(item);
            if (this.isNormal)
                updateAgingQuality(item);

            applyQualityLimits(item);

            if (!this.isLegendary)
                decrementSellIn(item);
        }
    }

    // set flags appropriate for the passed item
    protected void setItemFlags(Item item) {
        this.isAging = item.name.contains("Aged Brie") || item.name.contains("concert");
        this.isLegendary = item.name.contains("Sulfuras");
        this.isConjured = item.name.contains("Conjured");
        this.isNormal = (!this.isAging && !this.isLegendary && !this.isConjured);
        this.isPastSellIn = item.sellIn <= 0; // corrected bug, was item.quality
        this.isUnsellable = item.quality <= 0;
    }

    // update the quality of an item which ages
    protected void updateAgingQuality(Item item) {
        if (item.sellIn > 10)
            item.quality = item.quality + 1;
        if (item.sellIn <= 10 && item.sellIn > 5)
            item.quality = item.quality + 2;
        if (item.sellIn <= 5 && item.sellIn > 0)
            item.quality = item.quality + 3;
        if (isPastSellIn)
            item.quality = 0;
    }

    // update the quality of a conjured item
    protected void updateConjuredQuality(Item item) {
        item.quality -= ((isPastSellIn) ? 4 : 2);
    }

    // update the quality of a normal item
    protected void updateNormalQuality(Item item) {
        item.quality -= ((isPastSellIn) ? 2 : 1);
    }

    // reduce sellIn by 1
    protected void decrementSellIn(Item item) {
        item.sellIn -= 1;
    }

    // apply hard quality limits
    public void applyQualityLimits(Item item) {
        if (this.isLegendary)
            item.quality = 80;
        if (item.quality > 50 && !this.isLegendary)
            item.quality = 50;
        if (item.quality < 0)
            item.quality = 0;
    }
}
