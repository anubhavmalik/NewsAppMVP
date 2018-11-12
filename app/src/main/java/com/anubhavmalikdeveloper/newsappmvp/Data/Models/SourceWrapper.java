package com.anubhavmalikdeveloper.newsappmvp.Data.Models;

import java.util.List;

public class SourceWrapper {
    private List<Source> sourceList;

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
