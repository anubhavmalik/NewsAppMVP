package com.anubhavmalikdeveloper.newsappmvp.Data.Models;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String name;
    private String code;

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static List<Country> getCountryList(){
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country("Australia", "au"));
        countryList.add(new Country("India", "in"));
        countryList.add(new Country("United States of America", "us"));

        return countryList;
    }

    public static String getCountryNameByCode(String countryCode){
        switch (countryCode){
            case "au" :
                return "Australia";

            case "in":
                return "India";

            case "us":
                return "United States of America";

        }
        return "";
    }
}
