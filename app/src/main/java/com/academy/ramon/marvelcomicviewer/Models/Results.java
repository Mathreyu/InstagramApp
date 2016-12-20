package com.academy.ramon.marvelcomicviewer.models;

import java.util.List;

/**
 * Created by Ramon on 12/20/2016.
 */

public class Results {
    public List<Heroes> results;

    public List<Heroes> getResults() {
        return results;
    }

    public void setResults(List<Heroes> results) {
        this.results = results;
    }

    public Results(List<Heroes> results) {

        this.results = results;
    }
}
