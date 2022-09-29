package com.example.refreshlayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {


    private List<String> mDataList = new ArrayList<>();

    public ImagesAdapter() {
    }

    public void setDataList(List<String> images) {
        mDataList.clear();
        mDataList.addAll(images);
        notifyDataSetChanged();
    }

    public void addDataList(List<String> images) {
        mDataList.addAll(images);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.img);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.iv).load(mDataList.get(position)).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
