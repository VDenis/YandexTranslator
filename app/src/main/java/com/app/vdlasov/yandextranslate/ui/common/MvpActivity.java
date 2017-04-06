package com.app.vdlasov.yandextranslate.ui.common;

import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Denis on 02.04.2017.
 */

public class MvpActivity extends AppCompatActivity {
    // unsubscribe rx subscription on destroy presenter
    private CompositeSubscription subscriptions = new CompositeSubscription();

    public void unsubscribeOnStop(Subscription subscription) {
        subscriptions.add(subscription);
    }

    @Override
    protected void onStop() {
        super.onStop();
        subscriptions.clear();
    }
}
