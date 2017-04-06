package com.app.vdlasov.yandextranslate.di.module;

import com.app.vdlasov.yandextranslate.api.RestApi;
import com.app.vdlasov.yandextranslate.repository.TranslateRepository;
import com.app.vdlasov.yandextranslate.repository.local.AppLocalDataStore;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis on 03.04.2017.
 */
@Module
public class BusinessLogicModule {

    @Provides
    public TranslateRepository provideTranslateManager(RestApi api, AppLocalDataStore localDataStore) {
        return new TranslateRepository(api, localDataStore);
    }

    @Provides
    public AppLocalDataStore provideAppLocalDataStore(Context context) {
        return new AppLocalDataStore(context);
    }
}
