package com.academy.ramon.marvelcomicviewer.models;

import java.util.List;

/**
 * Created by Ramon on 12/22/2016.
 */

public class Data {
    public List<Heroes> results;

    public Data(List<Heroes> results) {
        this.results = results;
    }

    public List<Heroes> getResults() {
        return results;
    }
}
