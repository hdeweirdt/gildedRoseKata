package com.gildedrose.updateHandlers.quality;

import com.gildedrose.Item;

public class DefaultQualityUpdateHandler extends QualityUpdateHandler {

    @Override
    public void performQualityUpdate(Item item) {
        item.quality-= 1;
    }

    @Override
    public boolean matches(Item item) {
        return true;
    }
}
