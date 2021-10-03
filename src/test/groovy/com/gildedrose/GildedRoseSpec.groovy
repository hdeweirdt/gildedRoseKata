package com.gildedrose

import spock.lang.Specification

/**
 * Spock unit tests.
 */
class GildedRoseSpec extends Specification {

    def "The Quality of an item is never negative"() {
        given: "an item"
        Item item = new Item("foo", sellIn, quality)

        and: "the application with these items"
        GildedRose app = new GildedRose(new Item[]{item})

        when: "updating quality"
        app.nextDay()

        then: "the quality has not become negative"
        app.items[0].quality >= 0

        where:
        sellIn | quality
        0      | 0
        10     | 0
    }

    def "Once the sell by date has passed, Quality degrades twice as fast"() {
        given: "an item"
        int originalQuality = 10
        Item item = new Item("foo", 1, originalQuality)

        and: "the application with these items"
        GildedRose app = new GildedRose(new Item[]{item})

        when: "updating quality the first time"
        app.nextDay()

        then: "the difference in quality is noted"
        def qualityAfterFirstDay = app.items[0].quality
        int differenceInQualityFresh = originalQuality - qualityAfterFirstDay

        when: "updating the quality the second time"
        app.nextDay()

        then: "the quality has not become negative"
        int differenceInQualityRotting = qualityAfterFirstDay - app.items[0].quality
        differenceInQualityRotting == 2 * differenceInQualityFresh
    }

    def "Aged Brie actually increases in Quality the older it gets"() {
        given:
        int originalQuality = 0
        Item brie = new Item("Aged Brie", 2, originalQuality)

        and: "the application with these items"
        GildedRose app = new GildedRose(new Item[]{brie})

        when: "updating the quality"
        app.nextDay()

        then: "the quality of the brie has increased"
        brie.quality > originalQuality
    }

    def "The quality of an item never increases above 50, Except for Sulfaras"() {
        given: "the application with an item"
        GildedRose app = new GildedRose(new Item[]{item})

        when: "updating the quality a few times"
        for (i in 0..50) {
            app.nextDay()
        }

        then: "the quality has or has not changed to the max amount"
        (item.quality > 50) == canBeAbove50

        where:
        item                                                          || canBeAbove50
        new Item("+5 Dexterity Vest", 10, 20)                         || false
        new Item("Aged Brie", 2, 0)                                   || false
        new Item("Elixir of the Mongoose", 5, 7)                      || false
        new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49) || false
        new Item("Sulfuras, Hand of Ragnaros", 0, 80)                 || true
    }

    def "Sulfuras, being a legendary item, never has to be sold"() {
        given: "Sulfuras"
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80)

        and: "the application with the item"
        GildedRose app = new GildedRose(new Item[]{sulfuras})

        when: "updating the quality"
        app.nextDay()

        then: "Sulfaras still  does not need to be sold"
        sulfuras.sellIn == 0
    }

    def "Sulfuras, being a legendary item, never decreases in quality"() {
        given: "Sulfuras"
        int originalQuality = 80
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, originalQuality)

        and: "the application with the item"
        GildedRose app = new GildedRose(new Item[]{sulfuras})

        when: "updating the quality"
        app.nextDay()

        then: "Sulfaras still  does not need to be sold"
        sulfuras.quality == originalQuality
    }

    def "Backstage passes increases in quality as its SellIn value approaches"() {
        given: "A backstage pass"
        def originalQuality = 10
        Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", daysRemaining, originalQuality)

        and: "the application with the backstagePass"
        GildedRose app = new GildedRose(new Item[]{backstagePass})

        when: "updating the quality"
        app.nextDay()

        then: "the quality of the backstagePass has dropped as expected"
        def qualityIncrease = backstagePass.quality - originalQuality
        qualityIncrease == expectedIncreaseInQuality

        where:
        daysRemaining || expectedIncreaseInQuality
        10            || 2
        9             || 2
        5             || 3
        4             || 3
        1             || 3
    }

    def "Backstage passes drop to 0 in quality after the concert"() {
        given: "High-quality backstagePass that has to be sold today"
        Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50)

        and: "the application with the backstagePass"
        GildedRose app = new GildedRose(new Item[]{backstagePass})

        when: "updating the quality"
        app.nextDay()

        then: "the quality of the backstagePass has dropped to 0"
        backstagePass.quality == 0
    }

}
