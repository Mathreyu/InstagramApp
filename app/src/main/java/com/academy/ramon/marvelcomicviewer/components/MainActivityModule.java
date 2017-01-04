package com.academy.ramon.marvelcomicviewer.components;

import com.academy.ramon.marvelcomicviewer.api.MarvelApi;
import com.academy.ramon.marvelcomicviewer.presenter.Presenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ramon on 1/4/2017.
 */

@Module(includes = {RetrofitModule.class})
public class MainActivityModule {

    @Provides
    @Singleton
    public Presenter providePresenter(MarvelApi marvelApi) {
        return new Presenter(marvelApi);
    }
}
