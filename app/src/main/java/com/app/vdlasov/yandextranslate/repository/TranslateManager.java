package com.app.vdlasov.yandextranslate.repository;

import com.app.vdlasov.yandextranslate.Config;
import com.app.vdlasov.yandextranslate.api.RestApi;
import com.app.vdlasov.yandextranslate.model.YandexTranslateResponse;

import rx.Observable;

/**
 * Created by Denis on 03.04.2017.
 */

public class TranslateManager {

    private final RestApi mApi;

    public TranslateManager(RestApi api) {
        mApi = api;
    }

    public Observable<YandexTranslateResponse> translate(String lang, String text) {
        return mApi.translate(lang, Config.DEBUG_YANDEX_TRANSLATE_KEY, text);
    }
}
