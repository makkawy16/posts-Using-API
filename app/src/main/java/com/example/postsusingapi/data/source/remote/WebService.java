package com.example.postsusingapi.data.source.remote;

import com.example.postsusingapi.data.model.PostResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {

    @GET("posts")
    Call<PostResponse> getPosts(); // leh call 3shan ana msh 3arf w2tha amta m3rfsh htakhod ad eh  call btshtghl fl main thread

}
