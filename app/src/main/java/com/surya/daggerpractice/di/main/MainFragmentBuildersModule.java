package com.surya.daggerpractice.di.main;



import com.surya.daggerpractice.ui.main.post.PostFragment;
import com.surya.daggerpractice.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostFragment contributesPostFragment();

}
