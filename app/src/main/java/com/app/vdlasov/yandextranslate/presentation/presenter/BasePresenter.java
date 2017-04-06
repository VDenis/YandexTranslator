package com.app.vdlasov.yandextranslate.presentation.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Denis on 06.04.2017.
 */

public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {
    // unsubscribe rx subscription on destroy presenter
    private CompositeSubscription subscriptions = new CompositeSubscription();

    public void unsubscribeOnDestroy(Subscription subscription) {
        subscriptions.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscriptions.clear();
    }
}
