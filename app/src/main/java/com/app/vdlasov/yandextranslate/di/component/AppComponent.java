package com.app.vdlasov.yandextranslate.di.component;

import com.app.vdlasov.yandextranslate.di.module.BusinessLogicModule;
import com.app.vdlasov.yandextranslate.di.module.ModelModule;
import com.app.vdlasov.yandextranslate.repository.TranslateRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Denis on 03.04.2017.
 */

@Singleton
@Component(modules = {ModelModule.class})
interface AppComponent {

    public BusinessLogicComponent plus(BusinessLogicModule businessLogicModule);

    public void inject(TranslateRepository entry);
}
