package com.app.vdlasov.yandextranslate.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by Denis on 03.04.2017.
 */

public class YandexTranslateResponse {

    @Json(name = "code")
    public int code;

    @Json(name = "lang")
    public String lang;

    @Json(name = "text")
    public List<String> text;
}
