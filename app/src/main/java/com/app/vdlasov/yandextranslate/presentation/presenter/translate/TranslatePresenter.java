package com.app.vdlasov.yandextranslate.presentation.presenter.translate;


import com.app.vdlasov.yandextranslate.Config;
import com.app.vdlasov.yandextranslate.di.DI;
import com.app.vdlasov.yandextranslate.model.YandexTranslateResponse;
import com.app.vdlasov.yandextranslate.presentation.view.translate.TranslateView;
import com.app.vdlasov.yandextranslate.repository.TranslateManager;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@InjectViewState
public class TranslatePresenter extends MvpPresenter<TranslateView> {

    @Inject
    TranslateManager translateManager;

    public TranslatePresenter() {
        DI.getDI().getComponentManager().getBusinessLogicComponent().inject(this);
    }

    public void requestTranslatePhrase(String langFrom, String langTo, String text) {
        String abbreviationLangFrom = Config.Lang_Abbreviation.get(Config.Lang_Names.indexOf(langFrom));
        String abbreviationLangTo = Config.Lang_Abbreviation.get(Config.Lang_Names.indexOf(langTo));
        translateManager.translate(abbreviationLangFrom + "-" + abbreviationLangTo, text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<YandexTranslateResponse>() {
                    @Override
                    public void call(final YandexTranslateResponse yandexTranslateResponse) {
                        getViewState().showTranslatedPhrase(yandexTranslateResponse.text.get(0));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(final Throwable throwable) {
                        if (throwable instanceof HttpException) {
                            HttpException exception = (HttpException) throwable;
                            getViewState().showError(exception.code() + " " + exception.message());
                        } else {
                            getViewState().showError(throwable.getMessage());
                        }
                    }
                });
        //getViewState().showTranslatedPhrase(text.concat(" translated"));
    }
}
