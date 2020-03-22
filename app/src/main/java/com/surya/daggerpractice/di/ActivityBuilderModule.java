package com.surya.daggerpractice.di;

import com.surya.daggerpractice.di.auth.AuthModule;
import com.surya.daggerpractice.di.auth.AuthViewModelModule;
import com.surya.daggerpractice.di.main.MainFragmentBuilderModule;
import com.surya.daggerpractice.ui.auth.AuthActivity;
import com.surya.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class,
            }
    )
    abstract AuthActivity contributesAuthActivity();

    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuilderModule.class,
            }
    )
    abstract MainActivity contributesMainActivity();
}
