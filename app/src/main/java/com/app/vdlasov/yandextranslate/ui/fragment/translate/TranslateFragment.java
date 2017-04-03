package com.app.vdlasov.yandextranslate.ui.fragment.translate;

import com.app.vdlasov.yandextranslate.R;
import com.app.vdlasov.yandextranslate.presentation.presenter.translate.TranslatePresenter;
import com.app.vdlasov.yandextranslate.presentation.view.translate.TranslateView;
import com.app.vdlasov.yandextranslate.ui.common.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class TranslateFragment extends MvpFragment implements TranslateView {

    public static final String TAG = "TranslateFragment";

    @InjectPresenter
    TranslatePresenter mTranslatePresenter;

    private EditText edtPhrase;

    private TextView tvResult;

    public static TranslateFragment newInstance() {
        TranslateFragment fragment = new TranslateFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        edtPhrase = ((EditText) view.findViewById(R.id.fragment_translate_edit_text_phrase));
        tvResult = ((TextView) view.findViewById(R.id.fragment_translate_text_view_result));
        ((ImageButton) view.findViewById(R.id.fragment_translate_image_button_translate)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        mTranslatePresenter.requestTranslatePhrase(edtPhrase.getText().toString());
                    }
                });
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void showTranslatedPhrase(final String result) {
        tvResult.setText(result);
    }

    @Override
    public void showError(final String message) {

    }
}
