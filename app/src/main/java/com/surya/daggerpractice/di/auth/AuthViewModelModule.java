package com.surya.daggerpractice.di.auth;

import androidx.lifecycle.ViewModel;

import com.surya.daggerpractice.di.ViewModelKey;
import com.surya.daggerpractice.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel authViewModel(AuthViewModel authViewModel);
}
