package com.app.vdlasov.yandextranslate.presentation.view;

import com.app.vdlasov.yandextranslate.model.HistoryUiItem;
import com.arellomobile.mvp.MvpView;

import android.support.annotation.StringRes;

import java.util.List;

public interface HistoryView extends MvpView {

    void showTranslateHistory(List<HistoryUiItem> history);

    void showError(String message);

    void showError(@StringRes int messageResID);

    void showSuccess(String message);

    void showSuccess(@StringRes int messageResID);
}
