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
import com.example.postsusingapi.data.model.PostResponse;
import com.example.postsusingapi.data.model.PostResponseItem;
import com.example.postsusingapi.data.source.remote.RetrofitClient;
import com.example.postsusingapi.databinding.FragmentPostsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class postsFragment extends Fragment {

    private FragmentPostsBinding binding;

    public postsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPostsBinding.bind(view);
        fetchPosts();

    }

    private void fetchPosts(){
        RetrofitClient.getWebService()
                .getPosts().enqueue(new Callback<List<PostResponseItem>>() {
                    @Override
                    public void onResponse(Call<List<PostResponseItem>> call, Response<List<PostResponseItem>> response) {
                        Log.d("dddddddd", "onResponse: "+response.body());
                    }

                    @Override
                    public void onFailure(Call<List<PostResponseItem>> call, Throwable t) {
                        Log.d("ddddddddd", "onFailure: " + t.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}