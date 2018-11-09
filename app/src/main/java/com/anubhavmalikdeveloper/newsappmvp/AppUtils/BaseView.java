package com.anubhavmalikdeveloper.newsappmvp.AppUtils;

import android.support.design.widget.Snackbar;

public interface BaseView {
    void showProgress (boolean status);
    void showSnackBar (String message, int duration);
}
