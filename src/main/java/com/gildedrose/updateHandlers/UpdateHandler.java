package com.gildedrose.updateHandlers;

import com.gildedrose.Item;
import com.gildedrose.updateHandlers.expiration.AlwaysGetsBetterExpirationUpdateHandler;
import com.gildedrose.updateHandlers.expiration.DefaultExpirationUpdateHandler;
import com.gildedrose.updateHandlers.expiration.ExpirationUpdateHandler;
import com.gildedrose.updateHandlers.expiration.QualityDropsToZeroExpirationUpdateHandler;

import java.util.List;

public abstract class UpdateHandler {
    private static final int NORMAL_ITEM_MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    private final List<ExpirationUpdateHandler> expirationUpdateHandlers;
    private final ExpirationUpdateHandler defaultExpirationUpdateHandler;

    protected UpdateHandler() {
        this.expirationUpdateHandlers = List.of(
                new AlwaysGetsBetterExpirationUpdateHandler(),
                new QualityDropsToZeroExpirationUpdateHandler());
        defaultExpirationUpdateHandler = new DefaultExpirationUpdateHandler();
    }

    public void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

    public void updateQuality(Item item) {
        performUpdate(item);
        handleExpired(item);
        item.quality = Math.min(item.quality, NORMAL_ITEM_MAX_QUALITY);
        item.quality = Math.max(item.quality, MIN_QUALITY);
    }

    private void handleExpired(Item item) {
        findAppropriateExpirationHandler(item).handleExpiration(item);
    }

    private ExpirationUpdateHandler findAppropriateExpirationHandler(Item item) {
        return expirationUpdateHandlers.stream()
                .filter(qualityUpdateHandler -> qualityUpdateHandler.matches(item))
                .findFirst()
                .orElse(defaultExpirationUpdateHandler);
    }

    protected abstract void performUpdate(Item item);

    public abstract boolean matches(Item item);

}
