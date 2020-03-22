package com.surya.daggerpractice.network.main;

import com.surya.daggerpractice.models.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    // posts?userId=1

    @GET("posts")
    Flowable<List<Post>> getPosts(
            @Query("userId") int userId
    );
}
