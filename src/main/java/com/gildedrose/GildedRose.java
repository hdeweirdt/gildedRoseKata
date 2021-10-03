package com.gildedrose;

class GildedRose {
    private static final int NORMAL_ITEM_MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void nextDay() {
        for (Item item : items) {
            updateSellIn(item);
            
            updateQuality(item);

            handleExpired(item);
        }
    }

    private void updateQuality(Item item) {
        if (getsBetterWithAge(item)) {
            increaseItemQuality(item);
        } else {
            decreaseQuality(item);
        }
    }

    private boolean getsBetterWithAge(Item item) {
        return item.name.equals("Aged Brie") || item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private void increaseItemQuality(Item item) {
        item.quality += 1;
        handleBackStagePasses(item);
        item.quality = Math.min(item.quality, NORMAL_ITEM_MAX_QUALITY);
    }

    private void handleBackStagePasses(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn < 11) {
                item.quality += 1;
            }
            if (item.sellIn < 6) {
                item.quality += 1;
            }
        }
    }

    private void decreaseQuality(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality = Math.max(item.quality - 1, MIN_QUALITY);
        }
    }

    private void updateSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void handleExpired(Item item) {
        if (pastExpirationDate(item)) {
            if(decreasesInValueOverTime(item)) {
                decreaseQuality(item);
            } else {
               if(item.name.equals("Aged Brie"))  {
                   increaseItemQuality(item);
               } else if(item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                   item.quality= 0;
               }
            }
        }
    }

    private boolean decreasesInValueOverTime(Item item) {
        return !item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean pastExpirationDate(Item item) {
        return item.sellIn < 0;
    }
}
