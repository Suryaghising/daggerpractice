package com.surya.daggerpractice.di.main;

import com.surya.daggerpractice.network.main.MainApi;
import com.surya.daggerpractice.ui.main.post.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    MainApi providesMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @Provides
    PostRecyclerAdapter providesPostRecyclerAdapter() {
        return new PostRecyclerAdapter();
    }
}
