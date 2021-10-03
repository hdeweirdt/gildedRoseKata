package com.gildedrose.updateHandlers.quality;

import com.gildedrose.Item;

public class ConjuredQualityUpdateHandler extends QualityUpdateHandler {
    public static final int TIMES_FASTER_THAN_NORMAL = 2;

    private final DefaultQualityUpdateHandler defaultQualityUpdateHandler;

    public ConjuredQualityUpdateHandler() {
        super();
        defaultQualityUpdateHandler = new DefaultQualityUpdateHandler();
    }

    @Override
    public boolean matches(Item item) {
        return item.name.startsWith("Conjured");
    }

    @Override
    protected void updateQuality(Item item) {
        for (int i = 0; i < TIMES_FASTER_THAN_NORMAL; i++) {
            defaultQualityUpdateHandler.updateQuality(item);
        }
    }
}
