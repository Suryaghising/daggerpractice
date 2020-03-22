package com.surya.daggerpractice.di.main;

import com.surya.daggerpractice.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    public abstract ProfileFragment contributesProfileFragment();
}
