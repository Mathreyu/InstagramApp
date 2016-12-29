package com.academy.ramon.marvelcomicviewer.models;

import java.util.List;

/**
 * Created by Ramon on 12/22/2016.
 */

public class Data {
    public List<Hero> results;

    public Data(List<Hero> results) {
        this.results = results;
    }

    public List<Hero> getResults() {
        return results;
    }
}
