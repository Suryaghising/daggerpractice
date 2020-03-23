package com.surya.daggerpractice.di.main;

import com.surya.daggerpractice.network.main.MainApi;
import com.surya.daggerpractice.ui.main.post.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    MainApi providesMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @MainScope
    @Provides
    PostRecyclerAdapter providesPostRecyclerAdapter() {
        return new PostRecyclerAdapter();
    }
}
