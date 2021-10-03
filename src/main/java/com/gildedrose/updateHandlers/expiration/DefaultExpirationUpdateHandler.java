package com.gildedrose.updateHandlers.expiration;

import com.gildedrose.Item;

public class DefaultExpirationUpdateHandler extends ExpirationUpdateHandler {

    @Override
    protected void handleExpirationInternal(Item item) {
        item.quality -= 1;
    }

    @Override
    public boolean matches(Item item) {
        return true;
    }

}
