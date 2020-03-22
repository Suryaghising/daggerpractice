package com.surya.daggerpractice.di;


import com.surya.daggerpractice.di.auth.AuthModule;
import com.surya.daggerpractice.di.auth.AuthViewModelsModule;
import com.surya.daggerpractice.di.main.MainFragmentBuildersModule;
import com.surya.daggerpractice.di.main.MainViewModelsModule;
import com.surya.daggerpractice.ui.auth.AuthActivity;
import com.surya.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();


    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class}
    )
    abstract MainActivity contributeMainActivity();

}