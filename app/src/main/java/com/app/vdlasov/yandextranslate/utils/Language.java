package com.app.vdlasov.yandextranslate.utils;

/**
 * Created by Denis on 05.04.2017.
 */

public class Language {
    public static String parseLangFrom(String lang) {
        return lang.split("-")[0];
    }

    public static String parseLangTo(String lang) {
        return lang.split("-")[1];
    }
}
