package com.app.vdlasov.yandextranslate.utils;

import com.app.vdlasov.yandextranslate.model.HistoryUiItem;
import com.app.vdlasov.yandextranslate.model.LanguageUiItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Denis on 04.04.2017.
 */

public class Search {

    public static List<HistoryUiItem> filterByPatternHistoryUiItem(List<HistoryUiItem> dataset, String pattern) {
        List<HistoryUiItem> newDataset = new ArrayList<>();
        Iterator<HistoryUiItem> iter = dataset.iterator();
        while (iter.hasNext()) {
            HistoryUiItem history = iter.next();
            if (containsIgnoreCase(history.getInputText().toLowerCase().trim(), pattern)
                    || containsIgnoreCase(history.getTranslatedText().toLowerCase().trim(), pattern)) {
                newDataset.add(history);
            }
        }
        return newDataset;
    }

    public static List<LanguageUiItem> filterByPatternLanguageUiItem(List<LanguageUiItem> dataset, String pattern) {
        List<LanguageUiItem> newDataset = new ArrayList<>();
        Iterator<LanguageUiItem> iter = dataset.iterator();
        while (iter.hasNext()) {
            LanguageUiItem language = iter.next();
            if (containsIgnoreCase(language.getName().toLowerCase().trim(), pattern)) {
                newDataset.add(language);
            }
        }
        return newDataset;
    }

    public static boolean containsIgnoreCase(String src, String what) {
        final int length = what.length();
        if (length == 0) {
            return true; // Empty string is contained
        }

        final char firstLo = Character.toLowerCase(what.charAt(0));
        final char firstUp = Character.toUpperCase(what.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp) {
                continue;
            }

            if (src.regionMatches(true, i, what, 0, length)) {
                return true;
            }
        }

        return false;
    }
}
