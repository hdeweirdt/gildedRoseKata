package com.gildedrose.updateHandlers.quality;

import com.gildedrose.Item;

public class BetterWithAgeQualityUpdateHandler extends QualityUpdateHandler {

    @Override
    public void updateQuality(Item item) {
        item.quality += 1;
    }

    @Override
    public boolean matches(Item item) {
        return item.name.equals("Aged Brie");
    }
}
