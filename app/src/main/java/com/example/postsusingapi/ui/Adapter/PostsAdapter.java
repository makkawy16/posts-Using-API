package com.example.postsusingapi.ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postsusingapi.data.model.PhotoResponse;
import com.example.postsusingapi.data.model.PhotoResponseItem;
import com.example.postsusingapi.data.model.PostResponseItem;
import com.example.postsusingapi.databinding.ItemPostLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.viewHolder> {

    private List<PostResponseItem> postResponseItemList;

    public void addPosts(List<PostResponseItem> postResponseItemList) {
        this.postResponseItemList = postResponseItemList;
        notifyDataSetChanged();
    }

    private List<PhotoResponseItem> photoResponseItemList;

    public void addPhotos(List<PhotoResponseItem> photoResponseItemList) {
        this.photoResponseItemList = photoResponseItemList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostLayoutBinding binding =
                ItemPostLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PostResponseItem post = postResponseItemList.get(position);
        PhotoResponseItem photo = photoResponseItemList.get(position);

            holder.binding.postId.setText(String.valueOf(post.getId()));
            holder.binding.postTitle.setText(post.getTitle());


            Picasso.get().load(photo.getUrl()).into(holder.binding.imgInPost);
    }


    @Override
    public int getItemCount() {
        return photoResponseItemList == null ? 0 : photoResponseItemList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        ItemPostLayoutBinding binding;

        public viewHolder(ItemPostLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
