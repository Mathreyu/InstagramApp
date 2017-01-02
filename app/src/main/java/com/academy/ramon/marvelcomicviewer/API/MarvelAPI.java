package com.academy.ramon.marvelcomicviewer.api;

import com.academy.ramon.marvelcomicviewer.models.*;

import java.util.List;
import rx.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ramon on 12/20/2016.
 */

public interface MarvelAPI {
    String ENDPOINT = "https://gateway.marvel.com:443/v1/public/";
    String OVERHEAD = "ts=1&apikey=fdf923a5e2581b46c56ac0a1915c1907&hash=9f4b7872fce708c3df668cd84b2cbce4";

    @GET("characters?limit=100&" + OVERHEAD)
    Observable<HeroesResponse> listHeroes();

    @GET("characters/{id}/comics?" + OVERHEAD)
    Observable<ComicResponse> listComics(@Path("id") String id);

}
