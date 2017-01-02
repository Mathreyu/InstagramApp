package com.academy.ramon.marvelcomicviewer.models;

import java.util.List;

/**
 * Created by Ramon on 12/29/2016.
 */

public class ComicResults {
    String title;
    String description;
    List<ComicImages> images;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<ComicImages> getImages() {
        return images;
    }

}
