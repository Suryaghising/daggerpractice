package com.surya.daggerpractice.ui.main.post;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.surya.daggerpractice.SessionManager;
import com.surya.daggerpractice.models.Post;
import com.surya.daggerpractice.network.main.MainApi;
import com.surya.daggerpractice.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {

    SessionManager sessionManager;
    MainApi mainApi;

    private static final String TAG = "PostViewModel";

    MediatorLiveData<Resource<List<Post>>> posts;

    @Inject
    public PostViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostViewModel: Post view model created.=====");
    }


    public LiveData<Resource<List<Post>>>  observePosts() {
        if (posts == null) {
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Post>) null));

            final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPosts(sessionManager.getUser().getValue().data.getId())

                    .onErrorReturn(new Function<Throwable, List<Post>>() {
                        @Override
                        public List<Post> apply(Throwable throwable) throws Exception {
                            Post errorPost = new Post();
                            errorPost.setId(-1);
                            List<Post> errorPostList = new ArrayList<>();
                            errorPostList.add(errorPost);
                            return errorPostList;
                        }
                    })

                    .map(new Function<List<Post>, Resource<List<Post>>>() {
                        @Override
                        public Resource<List<Post>> apply(List<Post> posts) throws Exception {
                            if (posts.size() > 0) {
                                if (posts.get(0).getId() == -1) {
                                    Resource.error("Something went wrong.====", null);
                                }
                            }
                            return Resource.success(posts);
                        }
                    })

                    .subscribeOn(Schedulers.io())
            );

            posts.addSource(source, new Observer<Resource<List<Post>>>() {
                @Override
                public void onChanged(Resource<List<Post>> listResource) {
                    posts.setValue(listResource);
                    posts.removeSource(source);
                }
            });

        }
        return posts;
    }
}
