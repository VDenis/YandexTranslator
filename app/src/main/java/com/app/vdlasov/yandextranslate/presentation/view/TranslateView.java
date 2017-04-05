package com.app.vdlasov.yandextranslate.presentation.view;

import com.arellomobile.mvp.MvpView;

public interface TranslateView extends MvpView {

    void showTranslatedPhrase(String primary, String translated, String langFrom, String langTo);

    void showError(String message);
}
