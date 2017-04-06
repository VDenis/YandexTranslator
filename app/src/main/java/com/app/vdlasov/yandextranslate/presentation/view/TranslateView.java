package com.app.vdlasov.yandextranslate.presentation.view;

import com.arellomobile.mvp.MvpView;

import android.support.annotation.StringRes;

public interface TranslateView extends MvpView {

    void showTranslatedPhrase(String primary, String translated, String langFrom, String langTo);

    void showError(String message);

    void showError(@StringRes int messageResID);
}
