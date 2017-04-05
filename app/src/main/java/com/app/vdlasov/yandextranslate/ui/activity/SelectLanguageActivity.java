package com.app.vdlasov.yandextranslate.ui.activity;

import com.app.vdlasov.yandextranslate.Config;
import com.app.vdlasov.yandextranslate.R;
import com.app.vdlasov.yandextranslate.model.LanguageUiItem;
import com.app.vdlasov.yandextranslate.presentation.presenter.SelectLanguagePresenter;
import com.app.vdlasov.yandextranslate.presentation.view.SelectLanguageView;
import com.app.vdlasov.yandextranslate.ui.adapter.LanguageAdapter;
import com.app.vdlasov.yandextranslate.ui.common.MvpActivity;
import com.app.vdlasov.yandextranslate.ui.common.OnItemClickListener;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.functions.Action1;

public class SelectLanguageActivity extends MvpActivity implements SelectLanguageView {

    public static final String TAG = "SelectLanguageActivity";

    private RecyclerView recyclerView;

    private LanguageAdapter adapter;

    @InjectPresenter
    SelectLanguagePresenter mSelectLanguagePresenter;

    public static final String SELECT_LANGUAGE_DIRECTION = "SELECT_LANGUAGE_DIRECTION";

    public static final String SELECT_LANGUAGE_FROM = "SELECT_LANGUAGE_FROM";

    public static final String SELECT_LANGUAGE_TO = "SELECT_LANGUAGE_TO";

    // output parameter
    public static final String SELECT_LANGUAGE_RESULT = "SELECT_LANGUAGE_RESULT";

    public static Intent getIntent(final Context context, String langDirection) {
        Intent intent = new Intent(context, SelectLanguageActivity.class);
        intent.putExtra(SELECT_LANGUAGE_DIRECTION, langDirection);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        String direction = getIntent().getStringExtra(SELECT_LANGUAGE_DIRECTION);
        String lang = direction.equals(SELECT_LANGUAGE_FROM) ?
                getString(R.string.title_select_language_from) : getString(R.string.title_select_language_to);

        ((TextView) findViewById(R.id.activity_select_language_text_view_toolbar_title)).setText(lang);

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_select_language_recycler_language);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SelectLanguageActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LanguageAdapter(createDataset(), new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Toast.makeText(SelectLanguageActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
                returnSelectedLanguage(adapter.getItem(position).getName());
            }
        });
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(SelectLanguageActivity.this,
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        EditText edtFilter = (EditText) findViewById(R.id.activity_select_language_edit_text_filter);

        // TODO: make sure to unsubscribe the subscription.
        Subscription editTextSub = RxTextView
                .textChanges(edtFilter)
                .debounce(800, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(final CharSequence charSequence) {
                        adapter.getFilter().filter(charSequence.toString());
                    }
                });
    }

    private List<LanguageUiItem> createDataset() {
        List<LanguageUiItem> array = new ArrayList<>();
        for (String lang : Config.Lang_Names) {
            array.add(new LanguageUiItem(lang));
        }
        return array;
    }

    private void returnSelectedLanguage(String lang) {
        Intent intent = new Intent();
        intent.putExtra(SELECT_LANGUAGE_RESULT, lang);
        setResult(RESULT_OK, intent);
        finish();
    }

    // if user want navigate to the previous activity without select lang
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
