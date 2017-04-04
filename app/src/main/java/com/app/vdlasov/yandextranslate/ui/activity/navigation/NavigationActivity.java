package com.app.vdlasov.yandextranslate.ui.activity.navigation;

import com.app.vdlasov.yandextranslate.R;
import com.app.vdlasov.yandextranslate.presentation.presenter.navigation.NavigationPresenter;
import com.app.vdlasov.yandextranslate.presentation.view.navigation.NavigationView;
import com.app.vdlasov.yandextranslate.ui.common.MvpActivity;
import com.app.vdlasov.yandextranslate.ui.fragment.about.SettingsFragment;
import com.app.vdlasov.yandextranslate.ui.fragment.history.HistoryFragment;
import com.app.vdlasov.yandextranslate.ui.fragment.translate.TranslateFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

public class NavigationActivity extends MvpActivity implements NavigationView,
        BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "NavigationActivity";

    private final static int MENU_DIALOGS = 0;

    private BottomNavigationView bottomNavigationView;

    @InjectPresenter
    NavigationPresenter mNavigationPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, NavigationActivity.class);

        return intent;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_translate: {
                addFragment(TranslateFragment.newInstance());
                break;
            }
            case R.id.navigation_history: {
                addFragment(HistoryFragment.newInstance());
                break;
            }
            case R.id.navigation_settings: {
                addFragment(SettingsFragment.newInstance());
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.navigation_translate);
        if (fragment == null) {
            setTranslateView();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            setTranslateView();
        }
    }

    private void setTranslateView() {
        bottomNavigationView.getMenu().getItem(MENU_DIALOGS).setChecked(true);
        onNavigationItemSelected(bottomNavigationView.getMenu().getItem(MENU_DIALOGS));
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fm.replace(R.id.content, fragment);
        fm.commit();
    }

}
