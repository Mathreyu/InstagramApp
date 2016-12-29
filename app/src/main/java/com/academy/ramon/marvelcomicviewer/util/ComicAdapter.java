package com.academy.ramon.marvelcomicviewer.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.academy.ramon.marvelcomicviewer.R;
import com.academy.ramon.marvelcomicviewer.models.ComicData;
import com.academy.ramon.marvelcomicviewer.models.ComicResults;
import com.academy.ramon.marvelcomicviewer.models.Comics;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ramon on 12/28/2016.
 */

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {
    private List<ComicResults> comicsList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.thumbnail)
        ImageView comicImage;
        @BindView(R.id.published_date)
        TextView pubDate;
        @BindView(R.id.comic_title)
        TextView comicTitle;
        String collectionURI;
        Context context;

        public ViewHolder (View itemView){
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }
    }

    public ComicAdapter(List<ComicResults> comicsList, Context context) {
        this.comicsList = comicsList;

    }

    @Override
    public ComicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View comicItem = inflater.inflate(R.layout.comic_card_view, parent, false);
        ComicAdapter.ViewHolder viewHolder = new ComicAdapter.ViewHolder(comicItem);
        comicItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ComicAdapter.ViewHolder viewHolder, int position){
        ComicResults comic = comicsList.get(position);
        bind(viewHolder, comic);

    }
    private void bind(ViewHolder viewHolder, ComicResults comic ) {
        TextView textView = viewHolder.comicTitle;
        textView.setText(comic.getTitle());
        ImageView imageView = viewHolder.comicImage;
        Picasso.with(imageView.getContext()).load(comic.getImages().getPath() +"."+ comic.getImages().getExtension()).into(imageView);
    }


    @Override
    public int getItemCount() {
        return comicsList.size();
    }
}
