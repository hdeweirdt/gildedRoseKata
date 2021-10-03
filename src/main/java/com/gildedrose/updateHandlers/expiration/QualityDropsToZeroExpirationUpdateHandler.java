package com.gildedrose.updateHandlers.expiration;

import com.gildedrose.Item;

public class QualityDropsToZeroExpirationUpdateHandler extends ExpirationUpdateHandler {
    @Override
    public boolean matches(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    @Override
    protected void handleExpirationInternal(Item item) {
        item.quality = 0;
    }
}
