package com.app.vdlasov.yandextranslate.ui.fragment.history;

import com.app.vdlasov.yandextranslate.R;
import com.app.vdlasov.yandextranslate.model.HistoryUiItem;
import com.app.vdlasov.yandextranslate.presentation.presenter.history.HistoryPresenter;
import com.app.vdlasov.yandextranslate.presentation.view.history.HistoryView;
import com.app.vdlasov.yandextranslate.ui.adapter.history.HistoryAdapter;
import com.app.vdlasov.yandextranslate.ui.common.MvpFragment;
import com.app.vdlasov.yandextranslate.ui.common.OnItemClickListener;
import com.arellomobile.mvp.presenter.InjectPresenter;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends MvpFragment implements HistoryView {

    public static final String TAG = "HistoryFragment";

    private RecyclerView recyclerView;

    private HistoryAdapter adapter;

    @InjectPresenter
    HistoryPresenter mHistoryPresenter;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        initRecyclerView(view);
        return view;
    }

    @Override
    public void showTranslateHistory(final List<HistoryUiItem> history) {
        adapter.add(history);
    }

    private void initRecyclerView(final View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_history_recycler_history);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HistoryAdapter(createDataset(), new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "position = " + position, Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mHistoryPresenter.getTranslateHistory();
    }

    private List<HistoryUiItem> createDataset() {
        List<HistoryUiItem> array = new ArrayList<HistoryUiItem>();
//        array.add(new HistoryUiItem("Apple", "Яблоко", "en-ru"));
//        array.add(new HistoryUiItem("Яблоко", "Apple", "ru-en"));
//        array.add(new HistoryUiItem("Tree", "Дерево", "en-ru"));
//        array.add(new HistoryUiItem("Дерево", "Tree", "ru-en"));
        return array;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
