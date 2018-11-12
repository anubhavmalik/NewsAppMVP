package com.anubhavmalikdeveloper.newsappmvp.CountrySelection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.anubhavmalikdeveloper.newsappmvp.AppUtils.DatabaseConstants;
import com.anubhavmalikdeveloper.newsappmvp.Data.DatabaseHelper;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Country;
import com.anubhavmalikdeveloper.newsappmvp.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private Context context;
    private List<Country> countryList;

    public CountryAdapter(Context context, List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CountryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_country, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CountryAdapter.CountryViewHolder viewHolder, int i) {
        final Country country = countryList.get(i);
        if (country != null) {
            if (country.getName() != null) {
                viewHolder.tvTitle.setText(country.getName());
            } else {
                viewHolder.tvTitle.setText("");
            }
            if ((new DatabaseHelper()).getSelectedCountry().equalsIgnoreCase(country.getCode())) {
                viewHolder.tvTitle.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            } else {
                viewHolder.tvTitle.setTextColor(context.getResources().getColor(R.color.color_grey));
            }
            viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    (new DatabaseHelper()).tinyDb.putString(DatabaseConstants.userCountry,country.getCode());
                    ((AppCompatActivity) context).finish();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
