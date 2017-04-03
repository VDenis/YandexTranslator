package com.app.vdlasov.yandextranslate.di;

import com.app.vdlasov.yandextranslate.di.component.ComponentManager;

import android.content.Context;

/**
 * Created by Denis on 03.04.2017.
 */

public class DI {

    private volatile static DI di;

    public static DI getDI() {
        if (di == null) {
            synchronized (DI.class) {
                if (di == null) {
                    di = new DI();
                }
            }
        }
        return di;
    }

    private ComponentManager componentManager;

    public void init(Context context) {
        componentManager = new ComponentManager(context);
    }

    public ComponentManager getComponentManager() {
        return componentManager;
    }

    private DI() {
    }
}
