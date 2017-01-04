package com.academy.ramon.marvelcomicviewer.components;

import com.academy.ramon.marvelcomicviewer.api.MarvelAPI;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ramon on 1/3/2017.
 */
@Singleton
@Component(modules = {MarvelRetrofitModule.class})
public interface RetrofitComponent {
    MarvelAPI provideAPI();
}
