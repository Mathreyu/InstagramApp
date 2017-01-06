package com.academy.ramon.marvelcomicviewer.components;

import com.academy.ramon.marvelcomicviewer.api.MarvelApi;
import com.academy.ramon.marvelcomicviewer.presenter.ComicListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ramon on 1/5/2017.
 */

@Module
public class ComicListModule {

    @Provides
    @Singleton
    public ComicListPresenter providePresenter(MarvelApi marvelApi) {
        return new ComicListPresenter(marvelApi);
    }
}
