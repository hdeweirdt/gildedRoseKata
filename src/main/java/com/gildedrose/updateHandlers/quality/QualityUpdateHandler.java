package com.gildedrose.updateHandlers.quality;

import com.gildedrose.Item;
import com.gildedrose.updateHandlers.UpdateHandler;
import com.gildedrose.updateHandlers.expiration.AlwaysGetsBetterExpirationUpdateHandler;
import com.gildedrose.updateHandlers.expiration.DefaultExpirationUpdateHandler;
import com.gildedrose.updateHandlers.expiration.ExpirationUpdateHandler;
import com.gildedrose.updateHandlers.expiration.QualityDropsToZeroExpirationUpdateHandler;

import java.util.List;

public abstract class QualityUpdateHandler extends UpdateHandler {

    @Override
    protected void performUpdate(Item item) {
       updateQuality(item);
    }

    protected abstract void updateQuality(Item item);

}
