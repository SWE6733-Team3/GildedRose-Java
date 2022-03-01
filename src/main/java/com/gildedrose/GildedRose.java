package com.gildedrose;

class GildedRose {
    Item[] items;
    protected boolean isNormal;
    protected boolean isAging;
    protected boolean isLegendary;
    protected boolean isConjured;
    protected boolean discountEligible; // flag item for as being eligible for discount

    // No arg constructor (New Feature)
    public GildedRose() {
    }

    // Arg constructor
    public GildedRose(Item[] items) {
        this.items = items;
    }

    // Set value of items[] to passed array (New Feature)
    protected void setItems(Item[] items) {
        this.items = items;
    }

    // Processes all items and updates their quality using the appropriate method
    // for the given item type
    public void updateQuality() {
        for (Item item : items) {
            setItemFlags(item);

            if (this.isAging)
                updateAgingQuality(item);
            if (this.isConjured)
                updateConjuredQuality(item);
            if (this.isNormal)
                updateNormalQuality(item);

            applyQualityLimits(item);

            if (!this.isLegendary)
                decrementSellIn(item);
        }
    }

    // Set boolean flags appropriate for the passed item
    protected void setItemFlags(Item item) {
        this.isAging = item.name.contains("Aged Brie") || item.name.contains("concert");
        this.isLegendary = item.name.contains("Sulfuras");
        this.isConjured = item.name.contains("Conjured");
        this.isNormal = (!this.isAging && !this.isLegendary && !this.isConjured);
        // sets item as discount eligible if sellIn <= 5 or quality = 0 (New Feature)
        this.discountEligible = (item.sellIn <= 5 || item.quality == 0);
    }

    // Update the quality of an item which ages
    protected void updateAgingQuality(Item item) {
        if (item.sellIn > 10)
            item.quality = item.quality + 1;
        if (item.sellIn <= 10 && item.sellIn > 5)
            item.quality = item.quality + 2;
        if (item.sellIn <= 5 && item.sellIn > 0) {
            item.quality = item.quality + 3;
        }
        if (item.sellIn <= 0) {
            item.quality = 0;
        }
    }

    // Update the quality of a conjured item
    protected void updateConjuredQuality(Item item) {
        item.quality -= ((item.sellIn <= 0) ? 4 : 2);
    }

    // Update the quality of a normal item
    protected void updateNormalQuality(Item item) {
        item.quality -= ((item.sellIn <= 0) ? 2 : 1);
    }

    // Reduce sellIn of passed Item by 1
    protected void decrementSellIn(Item item) {
        item.sellIn -= 1;
    }

    // Apply quality limits (0 <= N <= 50, and if Legendary == 80)
    public void applyQualityLimits(Item item) {
        if (this.isLegendary)
            item.quality = 80;
        if (item.quality > 50 && !this.isLegendary)
            item.quality = 50;
        if (item.quality < 0) {
            item.quality = 0;
        }
    }

    // Returns lowercase string type name of passed item (New Feature)
    protected String getTypeString(Item item) {
        setItemFlags(item);
        if (this.isAging)
            return "aging";
        if (this.isConjured)
            return "conjured";
        if (this.isLegendary)
            return "legendary";
        return "normal";
    }

    // Concatenates formatted list of items and their properties (New Feature)
    protected String getItemsAsString() {
        StringBuilder itemString = new StringBuilder();
        itemString.append(String.format("%n %-50s %-10s %-10s %-10s", "Name", "Quality", "Sell In", "Item Type"));
        for (Item i : this.items) {
            itemString.append(String.format("%n %-50s %-10s %-10s %-10s",
                i.name, i.quality, i.sellIn, "(" + getTypeString(i) + ")"));
        }
        return itemString.toString();
    }
}
