package com.app.vdlasov.yandextranslate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Denis on 03.04.2017.
 */

public class Config {

    public static final String BASE_URL = "https://translate.yandex.net";

    public static final String DEBUG_YANDEX_TRANSLATE_KEY
            = "trnsl.1.1.20170331T205649Z.7fa93f64b097c03f.9fd9dade20fc175e509bfcf9a8520afc33f209d5";

    public static final long CONNECT_TIMEOUT = 10L;

    public static final long WRITE_TIMEOUT = 10L;

    public static final long READ_TIMEOUT = 10L;

    public static final List<String> Lang_Names = Arrays.asList("English", "Russian", "French");

    public static final List<String> Lang_Abbreviation = Arrays.asList("en", "ru", "fr");
}
