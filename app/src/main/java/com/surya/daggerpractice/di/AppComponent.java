package com.surya.daggerpractice.di;

import android.app.Application;

import com.surya.daggerpractice.BaseApplication;
import com.surya.daggerpractice.SessionManager;
import com.surya.daggerpractice.di.auth.AuthViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class,
                ViewModelProviderFactoryModule.class,
                AuthViewModelModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    SessionManager sessionManager();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
