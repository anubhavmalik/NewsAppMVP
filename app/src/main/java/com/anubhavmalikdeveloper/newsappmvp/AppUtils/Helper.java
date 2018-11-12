package com.anubhavmalikdeveloper.newsappmvp.AppUtils;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Helper {
//    public static String getFormattedDate(@NonNull String newsDate){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
//
//        return simpleDateFormat.format(newsDate);
//    }

    public static Map<String, String> getQueryOptions(Map<String, Object> mapObj) {

        Map<String, String> options = new HashMap<>();
        if (mapObj != null) {

            for (String key : mapObj.keySet()) {
                Object object = mapObj.get(key);
                if (object != null) {
                    if (object instanceof String) {
                        options.put(key, (String) object);
                    } else if (object instanceof ArrayList) {
                        String optionValue = "";
                        for (Object obj : ((ArrayList) object)) {
                            if (obj instanceof String) {
                                optionValue += (String) obj;
                            } else if (obj instanceof Long) {
                                optionValue += obj.toString();
                            }

                        }
                        options.put(key, optionValue);
                    }
                }
            }
        }
        return options;
    }

}
