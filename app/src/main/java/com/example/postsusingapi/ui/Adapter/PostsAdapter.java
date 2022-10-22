package com.example.postsusingapi.ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postsusingapi.data.model.PostResponseItem;
import com.example.postsusingapi.databinding.ItemPostLayoutBinding;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.viewHolder> {

    private List<PostResponseItem> postResponseItemList;

    public void addPosts(List<PostResponseItem> postResponseItemList) {
        this.postResponseItemList = postResponseItemList;
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

        holder.binding.postId.setText(String.valueOf(post.getId()));
        holder.binding.postTitle.setText(post.getTitle());

    }

    @Override
    public int getItemCount() {
        return postResponseItemList == null ? 0 : postResponseItemList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        ItemPostLayoutBinding binding;

        public viewHolder(ItemPostLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
