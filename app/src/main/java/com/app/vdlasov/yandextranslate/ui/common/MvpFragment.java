package com.app.vdlasov.yandextranslate.ui.common;

import com.arellomobile.mvp.MvpAppCompatFragment;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Denis on 02.04.2017.
 */

public class MvpFragment extends MvpAppCompatFragment {
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
