package com.app.vdlasov.yandextranslate.repository;

import com.app.vdlasov.yandextranslate.repository.local.models.TranslatePhrase;

import java.util.List;

import rx.Observable;

/**
 * Created by Denis on 04.04.2017.
 */

public interface AppDataStore {
    Observable<List<TranslatePhrase>> getTranslatePhrases();
}
