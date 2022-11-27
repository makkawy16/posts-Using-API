package com.example.postsusingapi.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postsusingapi.R;
import com.example.postsusingapi.data.model.PostResponse;
import com.example.postsusingapi.data.model.PostResponseItem;
import com.example.postsusingapi.data.source.remote.RetrofitClient;
import com.example.postsusingapi.databinding.FragmentPostsBinding;
import com.example.postsusingapi.ui.Adapter.PostsAdapter;
import com.example.postsusingapi.viewModel.PostsViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class postsFragment extends Fragment implements PostsAdapter.PostCLick {

    private FragmentPostsBinding binding;
    private PostsAdapter postsAdapter;
    private ProgressDialog mloadingBar;
    private PostsViewModel postsViewModel;

    public postsFragment() {
        // Required empty public constructor
    }

    private void initRecycler() {
        postsAdapter = new PostsAdapter(this);
        binding.postsRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.postsRecycler.setAdapter(postsAdapter);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        postsViewModel = new PostsViewModel();
        postsViewModel.fetchPosts(getContext());
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
        initRecycler();
        observe();


    }

    private void observe() {

        postsViewModel.postLiveData.observe(getViewLifecycleOwner(), new Observer<List<PostResponseItem>>() {
            @Override
            public void onChanged(List<PostResponseItem> postResponseItems) {
                if (postsAdapter != null)
                    postsAdapter.addPosts(postResponseItems);
            }
        });
    }

   /* private void fetchPosts() {
        waitnig("Loading","Please Wait");
        RetrofitClient.getWebService()
                .getPosts().enqueue(new Callback<List<PostResponseItem>>() {
                    @Override
                    public void onResponse(Call<List<PostResponseItem>> call, Response<List<PostResponseItem>> response) {
                        Log.d("dddddddd", "onResponse: " + response.body());
                        mloadingBar.dismiss();
                        postsAdapter.addPosts(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<PostResponseItem>> call, Throwable t) {
                        Log.d("ddddddddd", "onFailure: " + t.getLocalizedMessage());
                        mloadingBar.dismiss();
                    }
                });
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void postCLicked(PostResponseItem postResponseItem) {
        Navigation.findNavController(getView())
                .navigate(postsFragmentDirections.actionPostsFragmentToPostDetailsFragment(postResponseItem));
    }


   /* private void waitnig(String title, String message) {
        mloadingBar = new ProgressDialog(getContext());
        mloadingBar.setTitle(title);
        mloadingBar.setMessage(message);
        mloadingBar.setCanceledOnTouchOutside(false);
        mloadingBar.show();
    }*/

}