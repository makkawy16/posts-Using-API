package com.example.postsusingapi.ui;

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

    FragmentPostDetailsBinding binding;


    public PostDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int postID =PostDetailsFragmentArgs.fromBundle(getArguments())
                .getPostId();

        RetrofitClient.getWebService().getPostDetails(postID)
                .enqueue(new Callback<PostResponseItem>() {
                    @Override
                    public void onResponse(Call<PostResponseItem> call, Response<PostResponseItem> response) {
                        Log.d("ttttttttt", "onResponse: " + response.body());
                    }

                    @Override
                    public void onFailure(Call<PostResponseItem> call, Throwable t) {
                        Log.d("ttttttttt", "onFailure: " +t.getLocalizedMessage());
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


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}