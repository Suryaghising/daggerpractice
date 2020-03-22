package com.surya.daggerpractice;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.surya.daggerpractice.models.User;
import com.surya.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager{

    private static final String TAG = "SessionManager";
    MediatorLiveData<AuthResource<User>> cachedData = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source) {
        if (cachedData != null) {
            cachedData.setValue(AuthResource.loading((User) null));
            cachedData.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedData.setValue(userAuthResource);
                    cachedData.removeSource(source);
                }
            });
        }
    }

    public void logOut() {
        Log.d(TAG, "logOut: user logging out.====");
        cachedData.setValue(AuthResource.<User>logout());
    }

    public LiveData<AuthResource<User>> getUser() {
        return cachedData;
    }
}
