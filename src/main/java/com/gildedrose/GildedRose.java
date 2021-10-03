package com.gildedrose;

import com.gildedrose.updateHandlers.LegendaryItemUpdateHandler;
import com.gildedrose.updateHandlers.UpdateHandler;
import com.gildedrose.updateHandlers.quality.BackStagePassQualityUpdateHandler;
import com.gildedrose.updateHandlers.quality.BetterWithAgeQualityUpdateHandler;
import com.gildedrose.updateHandlers.quality.DefaultQualityUpdateHandler;
import com.gildedrose.updateHandlers.quality.QualityUpdateHandler;

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
                new LegendaryItemUpdateHandler());
        defaultQualityUpdateHandler = new DefaultQualityUpdateHandler();

    }

    public void nextDay() {
        for (Item item : items) {
            updateSellIn(item);

            updateQuality(item);
        }
    }

    private void updateQuality(Item item) {
        findAppropriateUpdateHandler(item).update(item);
    }

    private UpdateHandler findAppropriateUpdateHandler(Item item) {
        return qualityUpdateHandlers.stream()
                .filter(qualityUpdateHandler -> qualityUpdateHandler.matches(item))
                .findFirst()
                .orElse(defaultQualityUpdateHandler);
    }

    private void updateSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }
    }


}
