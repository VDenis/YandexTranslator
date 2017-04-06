package com.app.vdlasov.yandextranslate.ui.activity;

import com.app.vdlasov.yandextranslate.R;
import com.app.vdlasov.yandextranslate.presentation.presenter.NavigationPresenter;
import com.app.vdlasov.yandextranslate.presentation.view.NavigationView;
import com.app.vdlasov.yandextranslate.ui.common.MvpActivity;
import com.app.vdlasov.yandextranslate.ui.fragment.HistoryFragment;
import com.app.vdlasov.yandextranslate.ui.fragment.SettingsFragment;
import com.app.vdlasov.yandextranslate.ui.fragment.TranslateFragment;
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
        BottomNavigationView.OnNavigationItemSelectedListener, HistoryFragment.OnFragmentInteractionListener {

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
        if (bottomNavigationView.getSelectedItemId() != R.id.navigation_translate) {
            setTranslateView();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentTranslateFromHistory(final Integer databaseId) {
        bottomNavigationView.getMenu().getItem(MENU_DIALOGS).setChecked(true);
        addFragment(TranslateFragment.newInstance(databaseId));
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
