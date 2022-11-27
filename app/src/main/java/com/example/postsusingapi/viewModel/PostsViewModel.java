package com.example.postsusingapi.viewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.postsusingapi.data.model.PostResponseItem;
import com.example.postsusingapi.data.source.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsViewModel extends LiveData {

    private ProgressDialog mloadingBar;


    // using abstruction
    // mutableLiveData can set , post and get
    private MutableLiveData<List<PostResponseItem>> _postLiveData = new MutableLiveData<>();

    //live data can only get posts put can't set data
    public LiveData<List<PostResponseItem>> postLiveData = _postLiveData;

    private MutableLiveData<String> _messageLiveData = new MutableLiveData<>();
    public LiveData<String> messageLiveData = _messageLiveData;


    public void fetchPosts(Context context) {
        waitnig("wait..","Loading" , context);

        RetrofitClient.getWebService()
                .getPosts().enqueue(new Callback<List<PostResponseItem>>() {
                    @Override
                    public void onResponse(Call<List<PostResponseItem>> call, Response<List<PostResponseItem>> response) {
                        if (response.isSuccessful())
                            _postLiveData.setValue(response.body()); //setvalue because we are in main thread
                        mloadingBar.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<PostResponseItem>> call, Throwable t) {
                        Log.d("ddddddddd", "onFailure: " + t.getLocalizedMessage());
                        _messageLiveData.setValue(t.getLocalizedMessage());
                        mloadingBar.dismiss();

                    }
                });
    }
    private void waitnig(String title, String message , Context context) {
        mloadingBar = new ProgressDialog(context);
        mloadingBar.setTitle(title);
        mloadingBar.setMessage(message);
        mloadingBar.setCanceledOnTouchOutside(false);
        mloadingBar.show();
    }
}
