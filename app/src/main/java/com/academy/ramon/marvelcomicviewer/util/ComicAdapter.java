package com.academy.ramon.marvelcomicviewer.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.academy.ramon.marvelcomicviewer.R;
import com.academy.ramon.marvelcomicviewer.models.ComicResults;
import com.academy.ramon.marvelcomicviewer.views.ComicDetail;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ramon on 12/28/2016.
 */

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {
    private ComicResults[] comicsList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.thumbnail)
        ImageView comicImage;
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

    public ComicAdapter(ComicResults[] comicsList, Context context) {
        this.comicsList = comicsList;

    }

    @Override
    public ComicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View comicItem = inflater.inflate(R.layout.comic_card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(comicItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ComicAdapter.ViewHolder viewHolder, int position){
        ComicResults comic = comicsList[position];
        bind(viewHolder, comic);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ComicDetail.class);
                intent.putExtra("title", comic.getTitle());
                intent.putExtra("description",comic.getDescription());
                if (comic.getImages()[0] != null) {
                    intent.putExtra("image", comic.getImages()[0].getPath() + "." + comic.getImages()[0].getExtension());
                }
                v.getContext().startActivity(intent);
            }
        });

    }
    private void bind(ViewHolder viewHolder, ComicResults comic ) {
        String imagePath = comic.getImages()[0].getPath() + "/portrait_xlarge" + "." + comic.getImages()[0].getExtension();
        TextView textView = viewHolder.comicTitle;
        textView.setText(comic.getTitle());
        ImageView imageView = viewHolder.comicImage;
        if (comic.getImages()[0] != null) {
            Picasso.with(imageView.getContext()).load(imagePath).into(imageView);
        } else {
            Picasso.with(imageView.getContext()).load(R.drawable.not_found).into(imageView);
        }
    }
    @Override
    public int getItemCount() {
        return comicsList.length;
    }
}
