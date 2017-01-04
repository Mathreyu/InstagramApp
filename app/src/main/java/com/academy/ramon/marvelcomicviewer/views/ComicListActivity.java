package com.academy.ramon.marvelcomicviewer.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.academy.ramon.marvelcomicviewer.R;
import com.academy.ramon.marvelcomicviewer.api.MarvelApi;
import com.academy.ramon.marvelcomicviewer.components.DaggerRetrofitComponent;
import com.academy.ramon.marvelcomicviewer.components.RetrofitModule;
import com.academy.ramon.marvelcomicviewer.presenter.Presenter;
import com.academy.ramon.marvelcomicviewer.util.ComicAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ramon on 12/28/2016.
 */

public class ComicListActivity extends Activity {

    @BindView(R.id.rvComics)
    RecyclerView rvComics;

    @Inject
    Presenter presenter;

    private int idExtra;
    private ComicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comics_list);
        ButterKnife.bind(this);
        DaggerRetrofitComponent.builder().retrofitModule(new RetrofitModule(MarvelApi.ENDPOINT)).build().inject(this);

        idExtra = getIntent().getExtras().getInt("heroID");

        adapter = new ComicAdapter();
        rvComics.setLayoutManager(new GridLayoutManager(this, 2));
        rvComics.setItemAnimator(new DefaultItemAnimator());
        rvComics.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getComics(idExtra, adapter);
    }

}
