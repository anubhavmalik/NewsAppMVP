package com.anubhavmalikdeveloper.newsappmvp.Trending;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.anubhavmalikdeveloper.newsappmvp.Base.BaseFragment;
import com.anubhavmalikdeveloper.newsappmvp.Callbacks.VisitNewsInterface;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Article;
import com.anubhavmalikdeveloper.newsappmvp.Network.ApiClient;
import com.anubhavmalikdeveloper.newsappmvp.Network.ApiInterface;
import com.anubhavmalikdeveloper.newsappmvp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrendingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, CompoundButton.OnCheckedChangeListener, TrendingContract.View, VisitNewsInterface {
    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toggle_trending)
    SwitchCompat toggleSwitch;

    private Context mContext;
    private TrendingPresenter presenter;
    private boolean isAccordingToPreferences;
    private ArrayList<Article> articleArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_trending, null, false);
        ButterKnife.bind(this, view);

        initViewClicks();
        initDataHelpers();

        return view;
    }

    private void initDataHelpers() {
        ApiClient apiClient = ApiClient.getInstance();

        //TODO: Init DB HELPER HERE TOO.
        initPresenter(apiClient.createService(ApiInterface.class));
    }

    private void initPresenter(ApiInterface apiInterface) {
        presenter = new TrendingPresenter(this, apiInterface);
    }

    private void initViewClicks() {
        swipeRefreshLayout.setOnRefreshListener(this);
        toggleSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onRefresh() {
        presenter.loadData(isAccordingToPreferences);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        isAccordingToPreferences = b;
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showData(@NonNull List<Article> articleList) {
        setAdapter();
    }

    private void setAdapter() {
        TrendingAdapter trendingAdapter = new TrendingAdapter(mContext, articleArrayList, this);
        rvMain.setAdapter(trendingAdapter);
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
    public void redirectToNewsSource(String url) {
        //TODO: open the web-page with the given url
    }
}
