package com.example.postsusingapi.data.source.remote;

import com.example.postsusingapi.data.model.CommentResponseItem;
import com.example.postsusingapi.data.model.PostResponse;
import com.example.postsusingapi.data.model.PostResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {

    @GET("posts")
    Call<List<PostResponseItem>> getPosts(); // leh call 3shan ana msh 3arf w2tha amta m3rfsh htakhod ad eh  call btshtghl fl main thread

    @GET("posts/{id}")
    Call<PostResponseItem> getPostDetails(@Path("id") int id);

    @GET("comments")
    Call<List<CommentResponseItem>> getComments(@Query("postId") int postId);
}
