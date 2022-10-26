package com.example.postsusingapi.ui.Adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postsusingapi.data.model.CommentResponseItem;
import com.example.postsusingapi.databinding.ItemCommentLayoutBinding;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.viewHolder> {
    List<CommentResponseItem> comments;

    public void addComments(List<CommentResponseItem> comments) {
        this.comments = comments;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommentLayoutBinding binding = ItemCommentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CommentResponseItem comment = comments.get(position);

        holder.binding.commentId.setText(String.valueOf(comment.getId()));
        holder.binding.email.setText(comment.getEmail());
        holder.binding.commentBody.setText(comment.getBody());


    }

    @Override
    public int getItemCount() {
        return comments == null ? 0 : comments.size();
    }


    class viewHolder extends RecyclerView.ViewHolder {
        ItemCommentLayoutBinding binding;

        public viewHolder(ItemCommentLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
