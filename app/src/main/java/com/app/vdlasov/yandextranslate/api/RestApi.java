package com.app.vdlasov.yandextranslate.api;

import com.app.vdlasov.yandextranslate.model.YandexTranslateResponse;

import rx.Observable;

/**
 * Created by Denis on 03.04.2017.
 */

public class RestApi {

    private final YandexTranslateApi mYandexTranslateApi;

    public RestApi(final YandexTranslateApi yandexTranslateApi) {
        mYandexTranslateApi = yandexTranslateApi;
    }

    public Observable<YandexTranslateResponse> translate(String lang, String key, String text) {
        return mYandexTranslateApi.translate(lang, key, text);
    }
}
