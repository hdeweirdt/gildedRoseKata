package com.gildedrose.updateHandlers.quality;

import com.gildedrose.Item;

public class BackStagePassQualityUpdateHandler extends QualityUpdateHandler {

    @Override
    public void performQualityUpdate(Item item) {
        item.quality+=1;
        if (item.sellIn < 11) {
            item.quality += 1;
        }
        if (item.sellIn < 6) {
            item.quality += 1;
        }
    }

    @Override
    public boolean matches(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }
}
