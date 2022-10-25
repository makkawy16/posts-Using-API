package com.example.postsusingapi.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postsusingapi.R;
import com.example.postsusingapi.data.model.PostResponseItem;
import com.example.postsusingapi.data.source.remote.RetrofitClient;
import com.example.postsusingapi.databinding.FragmentPostDetailsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostDetailsFragment extends Fragment {

  private   FragmentPostDetailsBinding binding;
    private ProgressDialog mloadingBar;


    public PostDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int postId = PostDetailsFragmentArgs.fromBundle(getArguments())
                .getPostId();
        waitnig("Loading" , "Please Wait");
        RetrofitClient.getWebService().getPostDetails(postId)
                .enqueue(new Callback<PostResponseItem>() {
                    @Override
                    public void onResponse(Call<PostResponseItem> call, Response<PostResponseItem> response) {
                        Log.d("ttttttttt", "onResponse: " + response.body());
                        mloadingBar.dismiss();
                        if (response.isSuccessful())
                            fetchPostDetails(response.body());
                    }

                    @Override
                    public void onFailure(Call<PostResponseItem> call, Throwable t) {
                        Log.d("ttttttttt", "onFailure: " + t.getLocalizedMessage());
                        mloadingBar.dismiss();
                    }
                });


    }

    private void fetchPostDetails(PostResponseItem post) {

        binding.userId.setText(String.valueOf(post.getUserId()));
        binding.postId.setText(String.valueOf(post.getId()));
        binding.postTitle.setText(post.getTitle());
        binding.postBody.setText(post.getBody());
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


    }
    private void waitnig(String title , String message) {
        mloadingBar = new ProgressDialog(getContext());
        mloadingBar.setTitle(title);
        mloadingBar.setMessage(message);
        mloadingBar.setCanceledOnTouchOutside(false);
        mloadingBar.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}