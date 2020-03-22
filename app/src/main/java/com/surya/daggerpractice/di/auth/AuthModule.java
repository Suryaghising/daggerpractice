package com.surya.daggerpractice.di.auth;

import com.surya.daggerpractice.network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @Provides
    static AuthApi authApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }
}
