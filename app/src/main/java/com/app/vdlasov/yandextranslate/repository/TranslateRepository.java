package com.app.vdlasov.yandextranslate.repository;

import com.app.vdlasov.yandextranslate.Config;
import com.app.vdlasov.yandextranslate.api.RestApi;
import com.app.vdlasov.yandextranslate.model.YandexTranslateResponse;
import com.app.vdlasov.yandextranslate.repository.local.AppLocalDataStore;
import com.app.vdlasov.yandextranslate.repository.local.DatabaseContract;
import com.app.vdlasov.yandextranslate.repository.local.models.TranslatePhrase;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Denis on 03.04.2017.
 */

public class TranslateRepository {

    private final RestApi mApi;

    private final AppLocalDataStore mAppLocalDataStore;

    public TranslateRepository(RestApi api, AppLocalDataStore appLocalDataStore) {
        mApi = api;
        mAppLocalDataStore = appLocalDataStore;
    }

    public Observable<YandexTranslateResponse> translate(final String lang, final String text) {
        return mApi.translate(lang, Config.DEBUG_YANDEX_TRANSLATE_KEY, text).doOnNext(
                new Action1<YandexTranslateResponse>() {
                    @Override
                    public void call(final YandexTranslateResponse yandexTranslateResponse) {
                        mAppLocalDataStore.saveTranslatePhraseToDatabase(
                                new TranslatePhrase(null, text, yandexTranslateResponse.text.get(0), lang,
                                        DatabaseContract.TranslatePhrase.persistDate(new Date()), false));
                    }
                });
    }

    public Observable<List<TranslatePhrase>> getTranslateHistory() {
        return mAppLocalDataStore.getTranslatePhrases();
    }

    public void clearTranslateHistory() {
        mAppLocalDataStore.delereTranslatePhrasesFromDatabase();
    }
}
