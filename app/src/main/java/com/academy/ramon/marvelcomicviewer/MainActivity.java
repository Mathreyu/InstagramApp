package com.academy.ramon.marvelcomicviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.academy.ramon.marvelcomicviewer.api.MarvelAPI;
import com.academy.ramon.marvelcomicviewer.presenter.Presenter;
import com.academy.ramon.marvelcomicviewer.util.HeroAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @BindView(R.id.rvHeroes)
    RecyclerView rvHeroes;

    private HeroAdapter adapter;
    private MarvelAPI service;
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new HeroAdapter();
        presenter = new Presenter();
        presenter.initPresenter();
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        rvHeroes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getHeroes(adapter);
    }


}

