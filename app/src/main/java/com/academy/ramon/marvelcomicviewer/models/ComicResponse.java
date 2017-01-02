package com.academy.ramon.marvelcomicviewer.models;

/**
 * Created by Ramon on 12/29/2016.
 */

public class ComicResponse {
    ComicData data;

    public ComicResponse(ComicData data) {
        this.data = data;
    }

    public ComicData getData() {
        return data;
    }
}
