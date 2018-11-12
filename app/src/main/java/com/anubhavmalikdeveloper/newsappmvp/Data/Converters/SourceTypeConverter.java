package com.anubhavmalikdeveloper.newsappmvp.Data.Converters;

import android.arch.persistence.room.TypeConverter;

import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Source;
import com.google.gson.Gson;

public class SourceTypeConverter {
    @TypeConverter
    public static Source fromString(String value) {
//        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
//        return new Gson().fromJson(value, listType);
        return new Gson().fromJson(value, Source.class);
    }

    @TypeConverter
    public static String fromObject(Source source) {
        Gson gson = new Gson();
        String json = gson.toJson(source);
        return json;
    }
}
