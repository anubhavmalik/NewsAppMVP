package com.anubhavmalikdeveloper.newsappmvp.Base;

public interface BaseView {
    void showProgress(boolean status);

    void showSnackBar(String message, int duration);

    void showEmptyPlaceholder();
}
