package com.surya.daggerpractice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.surya.daggerpractice.models.User;
import com.surya.daggerpractice.ui.auth.AuthActivity;
import com.surya.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObserver();
    }

    private void subscribeObserver() {
        sessionManager.getUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING:
                            break;

                        case AUTHENTICATED:
                            Toast.makeText(BaseActivity.this, "Authenticated as====="+userAuthResource.data.getEmail(), Toast.LENGTH_SHORT).show();
                            break;

                        case NOT_AUTHENTICATED:
                            navigateToLogin();
                            break;

                        case ERROR:
                            Toast.makeText(BaseActivity.this, userAuthResource.message, Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            }
        });
    }

    public void navigateToLogin() {
        Intent intent = new Intent(BaseActivity.this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
