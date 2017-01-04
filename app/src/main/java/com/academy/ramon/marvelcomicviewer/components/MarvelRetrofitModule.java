package com.academy.ramon.marvelcomicviewer.components;

import com.academy.ramon.marvelcomicviewer.api.MarvelAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ramon on 1/4/2017.
 */
@Module
public class MarvelRetrofitModule implements RetrofitModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MarvelAPI.ENDPOINT)
                .build();
    }

    @Provides
    @Singleton
    public MarvelAPI provideAPI(Retrofit retrofit) {
        return retrofit.create(MarvelAPI.class);
    }
}
