package com.app.vdlasov.yandextranslate.presentation.view.translate;

import com.arellomobile.mvp.MvpView;

public interface TranslateView extends MvpView {

    void showTranslatedPhrase(String result);

    void showError(String message);
}
