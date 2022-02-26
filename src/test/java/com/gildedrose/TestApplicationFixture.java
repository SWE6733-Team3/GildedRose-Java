package com.gildedrose;

public class TestApplicationFixture {
    public static void main(String[] args) {
        System.out.println("Test Name Here");

        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("+3 Strength Gloves", 5, 10),
                new Item("Aged Brie", 2, 0),
                new Item("Aged Brie", 11, 10),
                new Item("Aged Brie", 6, 20),
                new Item("Aged Brie", 1, 30),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Elixir of the Mongoose", 1, 5),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 48),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 47),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 39),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 29),
                new Item("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);

        int days = 3;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            // System.out.printf("%-50s %-10s %-10s %-10s %-10s %-10s\n",
            // "name", "sellIn", "quality","ages","legendary","conjured");
            System.out.println("Name, SellIn, Quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }

}
