package com.academy.ramon.marvelcomicviewer.components;

import com.academy.ramon.marvelcomicviewer.MainActivity;
import com.academy.ramon.marvelcomicviewer.views.ComicListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ramon on 1/3/2017.
 */

@Singleton
@Component(modules = {RetrofitModule.class, MainActivityModule.class, ComicListModule.class})
public interface RetrofitComponent {

    void inject(MainActivity mainActivity);

    void inject(ComicListActivity comicListActivity);

}
