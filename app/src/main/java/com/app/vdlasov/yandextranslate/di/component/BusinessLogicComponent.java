package com.app.vdlasov.yandextranslate.di.component;


import com.app.vdlasov.yandextranslate.di.module.BusinessLogicModule;
import com.app.vdlasov.yandextranslate.presentation.presenter.history.HistoryPresenter;
import com.app.vdlasov.yandextranslate.presentation.presenter.translate.TranslatePresenter;
import com.app.vdlasov.yandextranslate.ui.fragment.about.SettingsFragment;

import dagger.Subcomponent;

/**
 * Created by Denis on 03.04.2017.
 */

@Subcomponent(modules = {BusinessLogicModule.class})
public interface BusinessLogicComponent {

    public void inject(TranslatePresenter entry);

    public void inject(HistoryPresenter entry);

    public void inject(SettingsFragment entry);
}
