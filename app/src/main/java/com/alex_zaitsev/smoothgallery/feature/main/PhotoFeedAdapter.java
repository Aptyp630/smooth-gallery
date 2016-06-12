package com.alex_zaitsev.smoothgallery.feature.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alex_zaitsev.smoothgallery.R;
import com.alex_zaitsev.smoothgallery.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Zaitsev on 6/11/16.
 */
public class PhotoFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private OnGridItemClickListener<Photo> itemClickListener;
    private List<Photo> photoList = new ArrayList<>();

    public PhotoFeedAdapter(Context context) {
        this.context = context;
    }

    public void addItems(List<Photo> photoList) {
        this.photoList.addAll(photoList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnGridItemClickListener<Photo> listener) {
        itemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Photo photo = photoList.get(position);
        PhotoViewHolder photoViewHolder = (PhotoViewHolder) holder;
        Picasso.with(context).load(photo.getUrl()).into(photoViewHolder.image);
        photoViewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClicked(photo, position);
                }
            }
        });
    }

    private static class PhotoViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}