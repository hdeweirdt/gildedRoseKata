package com.gildedrose.updateHandlers.expiration;

import com.gildedrose.Item;

public abstract class ExpirationUpdateHandler {

    public void handleExpiration(Item item) {
        if (pastExpirationDate(item)) {
            this.handleExpirationInternal(item);
        }
    }

    public abstract boolean matches(Item item);

    protected abstract void handleExpirationInternal(Item item);

    private boolean pastExpirationDate(Item item) {
        return item.sellIn < 0;
    }
}
