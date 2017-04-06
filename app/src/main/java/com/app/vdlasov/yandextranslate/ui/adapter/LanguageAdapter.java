package com.app.vdlasov.yandextranslate.ui.adapter;

import com.app.vdlasov.yandextranslate.R;
import com.app.vdlasov.yandextranslate.model.LanguageUiItem;
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
 * Created by Denis on 05.04.2017.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> implements Filterable {

    private List<LanguageUiItem> dataset;

    private List<LanguageUiItem> filteredDataset = new ArrayList<>();

    private LanguageAdapter.CustomFilter filter = new LanguageAdapter.CustomFilter(this);

    private String filterString = "";

    private OnItemClickListener clickListener;

    public LanguageAdapter(final List<LanguageUiItem> dataset, final OnItemClickListener clickListener) {
        this.dataset = dataset;
        this.filteredDataset.addAll(dataset);
        this.clickListener = clickListener;
    }

    public void add(List<LanguageUiItem> newDataset) {
        dataset.clear();
        dataset.addAll(newDataset);
        getFilter().filter(filterString);
        //notifyDataSetChanged();
    }

    public LanguageUiItem getItem(int position) {
        return filteredDataset.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.languageName.setText(filteredDataset.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return filteredDataset.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class CustomFilter extends Filter {

        private LanguageAdapter mAdapter;

        private CustomFilter(LanguageAdapter mAdapter) {
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
                filteredDataset.addAll(Search.filterByPatternLanguageUiItem(dataset, filterString));
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

        public TextView languageName;

        public ViewHolder(final View itemView, final OnItemClickListener listener) {
            super(itemView);
            languageName = (TextView) itemView.findViewById(R.id.item_language_text_view_language_name);
            setListener(listener);
        }

        public void setListener(final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (listener != null) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
