package com.surya.daggerpractice.di.main;

import androidx.lifecycle.ViewModel;


import com.surya.daggerpractice.di.ViewModelKey;
import com.surya.daggerpractice.ui.main.post.PostViewModel;
import com.surya.daggerpractice.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    public abstract ViewModel bindsPostViewModel(PostViewModel viewModel);

}




