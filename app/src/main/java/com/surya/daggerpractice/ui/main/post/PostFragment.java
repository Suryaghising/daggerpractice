package com.surya.daggerpractice.ui.main.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.surya.daggerpractice.R;
import com.surya.daggerpractice.SessionManager;
import com.surya.daggerpractice.models.Post;
import com.surya.daggerpractice.ui.main.Resource;
import com.surya.daggerpractice.util.VerticalSpaceItemDecoration;
import com.surya.daggerpractice.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {
    private static final String TAG = "PostFragment";

    PostViewModel postViewModel;
    RecyclerView recyclerView;

    @Inject
    PostRecyclerAdapter postRecyclerAdapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerViewId);
        postViewModel = ViewModelProviders.of(this, providerFactory).get(PostViewModel.class);

        setUpRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers() {
        postViewModel.observePosts().removeObservers(getViewLifecycleOwner());
        postViewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null) {
                    Log.d(TAG, "onChanged: Observing data===="+listResource);
                    switch (listResource.status) {
                        case ERROR:
                            Log.d(TAG, "onChanged: Error occured=====");
                            break;

                        case LOADING:
                            Log.d(TAG, "onChanged: Fetching data.===");
                            break;

                        case SUCCESS:
                            postRecyclerAdapter.setPosts(listResource.data);
                            break;
                    }
                }
            }
        });
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(postRecyclerAdapter);
    }
}
