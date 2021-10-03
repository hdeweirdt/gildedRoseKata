package com.gildedrose.updateHandlers;

import com.gildedrose.Item;

public class LegendaryItemUpdateHandler extends UpdateHandler {

    @Override
    public void update(Item item) {
        //No-op: legendary items always stay the same
    }

    @Override
    protected void performUpdate(Item item) {
        //No-op: legendary items always stay the same
    }

    @Override
    public boolean matches(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }
}
