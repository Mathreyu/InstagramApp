package com.academy.ramon.marvelcomicviewer.views;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.academy.ramon.marvelcomicviewer.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ramon on 12/30/2016.
 */

public class ComicDetail extends AppCompatActivity {
    @BindView(R.id.comic_title)
    TextView comicTitle;
    @BindView(R.id.comic_description)
    TextView comicDescription;
    @BindView(R.id.comic_image)
    ImageView comicImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String title;
    String description;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_comic);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();

        title = getIntent().getExtras().getString("title");
        description = getIntent().getExtras().getString("description");
        image = getIntent().getExtras().getString("image");
        if (image != null) {
            Picasso.with(this).load(image).into(comicImage);
        }
        comicTitle.setText(title);
        comicDescription.setText(description);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

}
