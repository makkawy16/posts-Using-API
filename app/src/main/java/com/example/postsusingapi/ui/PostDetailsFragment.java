package com.example.postsusingapi.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.postsusingapi.R;
import com.example.postsusingapi.data.model.CommentResponseItem;
import com.example.postsusingapi.data.model.PostResponseItem;
import com.example.postsusingapi.data.source.remote.RetrofitClient;
import com.example.postsusingapi.databinding.FragmentPostDetailsBinding;
import com.example.postsusingapi.ui.Adapter.CommentsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostDetailsFragment extends Fragment {

  private   FragmentPostDetailsBinding binding;
    private PostResponseItem postdetails;
    CommentsAdapter commentsAdapter;


    public PostDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         postdetails = PostDetailsFragmentArgs.fromBundle(getArguments())
                .getPostDetails();



    }

    private void initRecycler(){
        commentsAdapter=new CommentsAdapter();
        binding.commentRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.commentRecycler.setAdapter(commentsAdapter);
    }

    private void fetchPostDetails(PostResponseItem post) {

        binding.userId.setText(String.valueOf(post.getUserId()));
        binding.postId.setText(String.valueOf(post.getId()));
        binding.postTitle.setText(post.getTitle());
        binding.postBody.setText(post.getBody());

binding.progressLoading.setVisibility(View.VISIBLE);
//show Comments
        RetrofitClient.getWebService().getComments(post.getId())
                .enqueue(new Callback<List<CommentResponseItem>>() {
                    @Override
                    public void onResponse(Call<List<CommentResponseItem>> call, Response<List<CommentResponseItem>> response) {
                        commentsAdapter.addComments(response.body());
                        binding.progressLoading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<List<CommentResponseItem>> call, Throwable t) {

                        Toast.makeText(requireContext(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        binding.progressLoading.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPostDetailsBinding.bind(view);
        initRecycler();
        fetchPostDetails(postdetails);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}