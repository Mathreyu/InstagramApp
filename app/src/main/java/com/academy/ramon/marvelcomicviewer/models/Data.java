package com.academy.ramon.marvelcomicviewer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ramon on 12/22/2016.
 */

public class Data {
    @SerializedName("results")
    public List<Hero> heroes;

    public Data(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }
}
