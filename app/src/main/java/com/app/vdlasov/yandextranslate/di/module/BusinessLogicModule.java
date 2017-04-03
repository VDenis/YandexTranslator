package com.app.vdlasov.yandextranslate.di.module;

import com.app.vdlasov.yandextranslate.api.RestApi;
import com.app.vdlasov.yandextranslate.repository.TranslateManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis on 03.04.2017.
 */
@Module
public class BusinessLogicModule {

    @Provides
    public TranslateManager provideTranslateManager(RestApi api) {
        return new TranslateManager(api);
    }
}
