package com.app.vdlasov.yandextranslate.presentation.view.history;

import com.app.vdlasov.yandextranslate.model.HistoryUiItem;
import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface HistoryView extends MvpView {
    void showTranslateHistory(List<HistoryUiItem> history);
}
