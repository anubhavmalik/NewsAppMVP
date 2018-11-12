package com.anubhavmalikdeveloper.newsappmvp.Data.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SourceWrapper {
    @SerializedName("sources")
    private List<Source> sourceList;

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SourceWrapper(List<Source> sourceList) {
        this.sourceList = sourceList;
    }

    public List<Source> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
    }
}
