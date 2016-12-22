package com.academy.ramon.marvelcomicviewer.models;

import java.util.List;

/**
 * Created by Ramon on 12/20/2016.
 */

public class Comics {
    String collectionURI;
    List<Items>  items;

    public Comics(String collectionURI, List<Items> items) {
        this.collectionURI = collectionURI;
        this.items = items;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
