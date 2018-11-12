package com.anubhavmalikdeveloper.newsappmvp.Sources;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anubhavmalikdeveloper.newsappmvp.Base.BaseFragment;
import com.anubhavmalikdeveloper.newsappmvp.Callbacks.SourceInterface;
import com.anubhavmalikdeveloper.newsappmvp.CountrySelection.CountrySelectionActivity;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Source;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.SourceWrapper;
import com.anubhavmalikdeveloper.newsappmvp.Network.ApiClient;
import com.anubhavmalikdeveloper.newsappmvp.Network.ApiInterface;
import com.anubhavmalikdeveloper.newsappmvp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourcesFragment extends BaseFragment implements SourcesContract.View, SourceInterface {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    @BindView(R.id.tv_country)
    TextView tvCountry;

    @BindView(R.id.cv_main)
    CardView cvMain;

    SourcesPresenter presenter;
    Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_sources, null, false);
        ButterKnife.bind(this, v);

        initViewClicks();
        initDataHelpers();

        return v;
    }

    private void initDataHelpers() {
        ApiClient apiClient = ApiClient.getInstance();

        initPresenter(apiClient.createService(ApiInterface.class));
    }

    private void initPresenter(ApiInterface apiInterface) {
        presenter = new SourcesPresenter(this, apiInterface);
    }

    private void initViewClicks() {

        cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, CountrySelectionActivity.class));
            }
        });
    }

    @Override
    public boolean isNetworkAvailable() {
        return false;
    }

    @Override
    public void showData(@NonNull List<Source> sources) {
        SourcesAdapter sourcesAdapter = new SourcesAdapter(mContext, sources, this);
        rvMain.setAdapter(sourcesAdapter);
        rvMain.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void showProgress(boolean status) {

    }

    @Override
    public void showSnackBar(String message, int duration) {

    }

    @Override
    public void showEmptyPlaceholder() {

    }

    @Override
    public void sourceUpdated(SourceWrapper sourceWrapper) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
