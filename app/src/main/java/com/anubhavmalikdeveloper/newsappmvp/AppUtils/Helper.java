package com.anubhavmalikdeveloper.newsappmvp.AppUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Helper {
    public static String getFormattedDate(String newsDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

        return simpleDateFormat.format(new Date(newsDate));
    }
}
