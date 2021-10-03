package com.gildedrose;

import com.gildedrose.updateHandlers.LegendaryItemUpdateHandler;
import com.gildedrose.updateHandlers.UpdateHandler;
import com.gildedrose.updateHandlers.quality.*;

import java.util.List;

class GildedRose {

    private final List<UpdateHandler> qualityUpdateHandlers;
    private final QualityUpdateHandler defaultQualityUpdateHandler;

    final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
        this.qualityUpdateHandlers = List.of(
                new BackStagePassQualityUpdateHandler(),
                new BetterWithAgeQualityUpdateHandler(),
                new ConjuredQualityUpdateHandler(),
                new LegendaryItemUpdateHandler());
        defaultQualityUpdateHandler = new DefaultQualityUpdateHandler();

    }

    public void nextDay() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        findAppropriateUpdateHandler(item).updateSellIn(item);
        findAppropriateUpdateHandler(item).updateQuality(item);
    }

    private UpdateHandler findAppropriateUpdateHandler(Item item) {
        return qualityUpdateHandlers.stream()
                .filter(qualityUpdateHandler -> qualityUpdateHandler.matches(item))
                .findFirst()
                .orElse(defaultQualityUpdateHandler);
    }

}
