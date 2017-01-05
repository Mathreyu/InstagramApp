package com.academy.ramon.marvelcomicviewer.components;

import com.academy.ramon.marvelcomicviewer.api.MarvelApi;
import com.academy.ramon.marvelcomicviewer.presenter.MainActivityPresenter;

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
    public MainActivityPresenter providePresenter(MarvelApi marvelApi) {
        return new MainActivityPresenter(marvelApi);
    }
}
