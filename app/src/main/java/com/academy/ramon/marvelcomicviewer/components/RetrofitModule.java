package com.academy.ramon.marvelcomicviewer.components;

import com.academy.ramon.marvelcomicviewer.api.MarvelAPI;

import retrofit2.Retrofit;

/**
 * Created by Ramon on 1/3/2017.
 */

public interface RetrofitModule {


     Retrofit provideRetrofit();

     MarvelAPI provideAPI(Retrofit retrofit);
}
