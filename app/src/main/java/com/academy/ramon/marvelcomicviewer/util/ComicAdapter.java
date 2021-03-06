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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ramon on 12/28/2016.
 */

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {
    private List<ComicResults> comicsList;

    public ComicAdapter() {
        comicsList = new ArrayList<>();
    }

    @Override
    public ComicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View comicItem = inflater.inflate(R.layout.comic_card_view, parent, false);
        return new ViewHolder(comicItem);
    }

    @Override
    public void onBindViewHolder(ComicAdapter.ViewHolder viewHolder, int position) {
        ComicResults comic = comicsList.get(position);
        viewHolder.bind(comic);

    }

    @Override
    public int getItemCount() {
        return comicsList.size();
    }

    public void addResults(List<ComicResults> comics) {
        comicsList.addAll(comics);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail)
        ImageView comicImage;
        @BindView(R.id.comic_title)
        TextView comicTitle;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        private void bind(ComicResults comic) {

            boolean imageExists = !comic.getImages().isEmpty();
            comicTitle.setText(comic.getTitle());
            if (imageExists) {
                String imagePath = comic.getImages().get(0).getPath() + "/portrait_xlarge" + "." + comic.getImages().get(0).getExtension();
                Picasso.with(comicImage.getContext()).load(imagePath).into(comicImage);
            } else {
                Picasso.with(comicImage.getContext()).load(R.drawable.not_found).into(comicImage);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ComicDetail.class);
                    intent.putExtra("title", comic.getTitle());
                    intent.putExtra("description", comic.getDescription());
                    if (imageExists) {
                        intent.putExtra("image", comic.getImages().get(0).getPath() + "." + comic.getImages().get(0).getExtension());
                    }
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

}
