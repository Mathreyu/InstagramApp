package com.academy.ramon.marvelcomicviewer.models;

/**
 * Created by Ramon on 12/20/2016.
 */

public class Item {
    String resourceURI;
    String name;

    public Item(String resourceURI, String name) {
        this.resourceURI = resourceURI;
        this.name = name;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
