package com.gildedrose.updateHandlers.quality;

import com.gildedrose.Item;
import com.gildedrose.updateHandlers.UpdateHandler;

public abstract class QualityUpdateHandler extends UpdateHandler {

    @Override
    protected void performUpdate(Item item) {
       updateQuality(item);
    }

    protected abstract void updateQuality(Item item);

}
