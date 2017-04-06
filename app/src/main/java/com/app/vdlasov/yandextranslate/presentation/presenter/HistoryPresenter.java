package com.app.vdlasov.yandextranslate.presentation.presenter;


import com.app.vdlasov.yandextranslate.R;
import com.app.vdlasov.yandextranslate.di.DI;
import com.app.vdlasov.yandextranslate.model.HistoryUiItem;
import com.app.vdlasov.yandextranslate.presentation.view.HistoryView;
import com.app.vdlasov.yandextranslate.repository.TranslateRepository;
import com.app.vdlasov.yandextranslate.repository.local.models.TranslatePhrase;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {

    @Inject
    TranslateRepository translateManager;

    public HistoryPresenter() {
        DI.getDI().getComponentManager().getBusinessLogicComponent().inject(this);
    }

    public void getTranslateHistory() {
        translateManager.getTranslateHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<TranslatePhrase>>() {
                    @Override
                    public void call(final List<TranslatePhrase> translatePhrases) {
                        List<HistoryUiItem> list = new ArrayList<HistoryUiItem>();
                        for (TranslatePhrase phrase : translatePhrases) {
                            list.add(new HistoryUiItem(phrase.getId(), phrase.getPrimary(), phrase.getTranslated(),
                                    phrase.getLang()));
                        }
                        getViewState().showTranslateHistory(list);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(final Throwable throwable) {
                        getViewState().showError(R.string.error_database_crash);
                    }
                });
    }

    public void deleteTranslatePhrasesFromDatabase(int id) {
        translateManager.deleteTranslatePhrasesFromDatabase(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DeleteResult>() {
                    @Override
                    public void call(final DeleteResult deleteResult) {
                        getViewState().showSuccess(R.string.status_delete_history_item);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(final Throwable throwable) {
                        // on error reload all items from database
                        getTranslateHistory();
                    }
                });
    }
}
