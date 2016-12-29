package com.academy.ramon.marvelcomicviewer.models;

/**
 * Created by Ramon on 12/29/2016.
 */

public class ComicResults {
    String title;
    String description;
    ComicImages[] images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComicImages[] getImages() {
        return images;
    }

    public void setComicImages (ComicImages[] images) {
        this.images = images;
    }
}
