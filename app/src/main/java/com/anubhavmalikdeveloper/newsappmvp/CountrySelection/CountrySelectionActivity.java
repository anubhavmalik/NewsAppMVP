package com.anubhavmalikdeveloper.newsappmvp.CountrySelection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Country;
import com.anubhavmalikdeveloper.newsappmvp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountrySelectionActivity extends AppCompatActivity {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_selection);
        ButterKnife.bind(this);

        setAdapter(Country.getCountryList());
    }

    private void setAdapter(List<Country> countryList) {
        CountryAdapter countryAdapter = new CountryAdapter(this, countryList);
        rvMain.setAdapter(countryAdapter);
        rvMain.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
