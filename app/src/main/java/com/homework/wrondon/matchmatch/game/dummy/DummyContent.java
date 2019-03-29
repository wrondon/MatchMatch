package com.homework.wrondon.matchmatch.game.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */

     static final String urls_kittens[] = {"https://farm8.staticflickr.com/7871/47445474751_73b9c1138f_m.jpg","https://farm8.staticflickr.com/7825/33569486248_807dc23c28_m.jpg"
                                                  ,"https://farm8.staticflickr.com/7842/47445473891_92f986cf61_m.jpg","https://farm8.staticflickr.com/7890/47445473661_c5b684ebeb_m.jpg",
                                                  "https://farm8.staticflickr.com/7850/46530243295_bc97fa7c74_m.jpg","https://farm8.staticflickr.com/7876/46722420544_579dde6acd_m.jpg",
                                                    "https://farm8.staticflickr.com/7874/46722419154_e2986c48c7_m.jpg","https://farm8.staticflickr.com/7883/46722425434_abde16ff2a_m.jpg"};

    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 16;

    private static List<Integer> lArray = new ArrayList();

    static {
        List<Integer> lArray1 = Arrays.asList(1,2,3,4,5,6,7,8);
        lArray.addAll(lArray1);
        lArray.addAll(lArray1);
        Collections.shuffle(lArray);
    }

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(lArray.get(position-1)), "Item " + position, makeDetails(position), urls_kittens[(lArray.get(position-1))%8],false,false);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;
        public final String picture_url;
        public boolean forward;
        public boolean matched;


        public DummyItem(String id, String content, String details, String picture_url, boolean forward, boolean matched) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.picture_url = picture_url;
            this.forward = forward;
            this.matched = matched;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
