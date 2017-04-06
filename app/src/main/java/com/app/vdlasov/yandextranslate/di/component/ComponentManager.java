package com.app.vdlasov.yandextranslate.di.component;

import com.app.vdlasov.yandextranslate.di.module.BusinessLogicModule;
import com.app.vdlasov.yandextranslate.di.module.ModelModule;

import android.content.Context;

/**
 * Created by Denis on 03.04.2017.
 */

public class ComponentManager {

    private Context mContext;

    // TODO check volatile for lock objects
    private final Object lock1 = new Object();

    private volatile AppComponent mInternalAppComponent;

    private final Object lock2 = new Object();

    private volatile BusinessLogicComponent mInternalBusinessLogicComponent;

    public ComponentManager(Context context) {
        this.mContext = context;
    }

    public BusinessLogicComponent getBusinessLogicComponent() {
        if (mInternalBusinessLogicComponent == null) {
            synchronized (lock2) {
                if (mInternalBusinessLogicComponent == null) {
                    mInternalBusinessLogicComponent = getAppComponent().plus(new BusinessLogicModule());
                }
            }
        }
        return mInternalBusinessLogicComponent;
    }

    public AppComponent getAppComponent() {
        if (mInternalAppComponent == null) {
            synchronized (lock1) {
                if (mInternalAppComponent == null) {
                    mInternalAppComponent = DaggerAppComponent.builder()
                            .modelModule(new ModelModule(mContext))
                            .build();
                }
            }
        }
        return mInternalAppComponent;
    }
}
