package com.app.vdlasov.yandextranslate.ui.adapter;

import com.app.vdlasov.yandextranslate.R;
import com.app.vdlasov.yandextranslate.model.HistoryUiItem;
import com.app.vdlasov.yandextranslate.ui.common.OnItemClickListener;
import com.app.vdlasov.yandextranslate.utils.Search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 04.04.2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> implements Filterable {

    private List<HistoryUiItem> dataset;

    private List<HistoryUiItem> filteredDataset = new ArrayList<>();

    private CustomFilter filter = new CustomFilter(this);

    private String filterString = "";

    private OnItemClickListener clickListener;

    public HistoryAdapter(final List<HistoryUiItem> dataset,
            final OnItemClickListener clickListener) {
        this.dataset = dataset;
        this.filteredDataset.addAll(dataset);
        this.clickListener = clickListener;
    }

    public void add(List<HistoryUiItem> newDataset) {
        dataset.clear();
        dataset.addAll(newDataset);
        getFilter().filter(filterString);
        //notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.inputText.setText(dataset.get(position).getInputText());
//        holder.translatedText.setText(dataset.get(position).getTranslatedText());
//        holder.lang.setText(dataset.get(position).getLang());
        holder.inputText.setText(filteredDataset.get(position).getInputText());
        holder.translatedText.setText(filteredDataset.get(position).getTranslatedText());
        holder.lang.setText(filteredDataset.get(position).getLang());
    }

    @Override
    public int getItemCount() {
        //return dataset.size();
        return filteredDataset.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    private class CustomFilter extends Filter {

        private HistoryAdapter mAdapter;

        private CustomFilter(HistoryAdapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredDataset.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                filterString = "";
                filteredDataset.addAll(dataset);
            } else {
                filterString = constraint.toString().toLowerCase().trim();
                filteredDataset.addAll(Search.filterByPattern(dataset, filterString));
            }

            results.values = filteredDataset;
            results.count = filteredDataset.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView inputText;

        public TextView translatedText;

        public TextView lang;

        public ViewHolder(final View itemView, final OnItemClickListener listener) {
            super(itemView);
            inputText = (TextView) itemView.findViewById(R.id.item_history_text_view_input_text);
            translatedText = (TextView) itemView.findViewById(R.id.item_history_text_view_translated_text);
            lang = (TextView) itemView.findViewById(R.id.item_history_text_view_lang);
            setListener(listener);
        }

        private void setListener(final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
