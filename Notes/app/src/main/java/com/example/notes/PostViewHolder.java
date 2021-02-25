package com.example.notes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {
    public TextView tanggal,name,desc;
    public ImageView deletePost;
    public  ImageView editPost;

    public PostViewHolder(View itemView) {
        super(itemView);
        tanggal = (TextView)itemView.findViewById(R.id.date);
        name = (TextView)itemView.findViewById(R.id.name);
        desc = (TextView)itemView.findViewById(R.id.desc);
        deletePost = (ImageView)itemView.findViewById(R.id.img_delete);
        editPost = (ImageView)itemView.findViewById(R.id.img_edit);
    }
}
