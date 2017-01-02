package com.academy.ramon.marvelcomicviewer.models;

import java.util.List;

/**
 * Created by Ramon on 12/28/2016.
 */

public class ComicData {
    private List<ComicResults> results;

    public ComicData(List<ComicResults> results) {
        this.results = results;
    }

    public List<ComicResults> getResults() {
        return results;
    }
}
