package com.academy.ramon.marvelcomicviewer.models;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Ramon on 12/28/2016.
 */

public class ComicData {
    ComicResults[] results;

    public ComicData(ComicResults[] results) {
        this.results = results;
    }

    public ComicResults[] getResults() {
        return results;
    }

    public void setResults(ComicResults[] results) {
        this.results = results;
    }
}
