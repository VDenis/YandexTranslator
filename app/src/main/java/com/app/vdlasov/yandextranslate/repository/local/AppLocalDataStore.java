package com.app.vdlasov.yandextranslate.repository.local;

import com.app.vdlasov.yandextranslate.repository.AppDataStore;
import com.app.vdlasov.yandextranslate.repository.local.models.TranslatePhrase;
import com.app.vdlasov.yandextranslate.repository.local.models.TranslatePhraseSQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Denis on 04.04.2017.
 */

public class AppLocalDataStore implements AppDataStore {

    private StorIOSQLite mStorIOSQLite;

    @Inject
    public AppLocalDataStore(Context context) {
        mStorIOSQLite = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(new DatabaseHelper(context))
                .addTypeMapping(TranslatePhrase.class,
                        new TranslatePhraseSQLiteTypeMapping())
                .build();
    }

    @Override
    public Observable<List<TranslatePhrase>> getTranslatePhrases() {

        return mStorIOSQLite.get()
                .listOfObjects(TranslatePhrase.class)
                .withQuery(DatabaseContract.TranslatePhrase.QUERY_ALL_SORTED)
                .prepare()
                .asRxObservable();
    }

    public void saveTranslatePhrasesToDatabase(List<TranslatePhrase> history) {
        mStorIOSQLite.put().objects(history).prepare().executeAsBlocking();
    }

    public void saveTranslatePhraseToDatabase(TranslatePhrase phrase) {
        mStorIOSQLite.put().object(phrase).prepare().executeAsBlocking();
    }

    public void delereTranslatePhraseFromDatabase(TranslatePhrase phrase) {
        mStorIOSQLite.delete().object(phrase).prepare().executeAsBlocking();
    }

    public void delereTranslatePhrasesFromDatabase(List<TranslatePhrase> history) {
        mStorIOSQLite.delete().objects(history).prepare().executeAsBlocking();
    }

    public void delereTranslatePhrasesFromDatabase() {
        mStorIOSQLite.delete()
                .byQuery(DatabaseContract.TranslatePhrase.DELETE_ALL)
                .prepare()
                .executeAsBlocking();
    }
}
