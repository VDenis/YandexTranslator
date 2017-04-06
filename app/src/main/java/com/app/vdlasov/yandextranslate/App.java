package com.app.vdlasov.yandextranslate;

import com.app.vdlasov.yandextranslate.di.DI;
import com.facebook.stetho.Stetho;

import android.app.Application;

/**
 * Created by Denis on 03.04.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DI.getDI().init(this);

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
