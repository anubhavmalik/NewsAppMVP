package com.anubhavmalikdeveloper.newsappmvp.Sources;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.anubhavmalikdeveloper.newsappmvp.Callbacks.SourceInterface;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Source;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.SourceWrapper;
import com.anubhavmalikdeveloper.newsappmvp.R;

import java.util.List;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.SourceViewHolder> {

    private Context mContext;
    private List<Source> sourceList;
    private SourceInterface sourceInterface;

    public SourcesAdapter(Context mContext, List<Source> sourceList, SourceInterface sourceInterface) {
        this.mContext = mContext;
        this.sourceList = sourceList;
        this.sourceInterface = sourceInterface;
    }

    @NonNull
    @Override
    public SourceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SourceViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_source, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SourceViewHolder sourceViewHolder, int i) {
        final Source source = sourceList.get(i);
        if (source != null) {
            if (source.getName() != null) {
                sourceViewHolder.checkedTextView.setText(source.getName());
            } else {
                sourceViewHolder.checkedTextView.setText("");
            }
            sourceViewHolder.checkedTextView.setChecked(source.isFavorite());
            sourceViewHolder.checkedTextView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    sourceList.get(sourceList.indexOf(source)).setFavorite(b);
                    sourceInterface.sourceUpdated(new SourceWrapper(sourceList));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }


    static class SourceViewHolder extends RecyclerView.ViewHolder {
        private AppCompatCheckBox checkedTextView;

        public SourceViewHolder(@NonNull View itemView) {
            super(itemView);
            checkedTextView = itemView.findViewById(R.id.tv_title);
        }
    }
}
