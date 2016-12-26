package com.academy.ramon.marvelcomicviewer.models;

/**
 * Created by Ramon on 12/20/2016.
 */

public class Heroes {
    String name;
    String description;
    Thumbnail thumbnail;
    String resourceURI;
    Comics comics;
    int id;

    public Heroes(int id, String name, String description, Thumbnail thumbnail, String resourceURI, Comics comics) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.resourceURI = resourceURI;
        this.comics = comics;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public Comics getComics() {
        return comics;
    }

    public void setComics(Comics comics) {
        this.comics = comics;
    }
}
