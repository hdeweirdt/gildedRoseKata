package com.gildedrose.updateHandlers.expiration;

import com.gildedrose.Item;

public class AlwaysGetsBetterExpirationUpdateHandler extends ExpirationUpdateHandler {

    @Override
    public boolean matches(Item item) {
        return item.name.equals("Aged Brie");
    }

    @Override
    protected void handleExpirationInternal(Item item) {
        item.quality +=1;
    }
}
