package com.surya.daggerpractice.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.surya.daggerpractice.R;
import com.surya.daggerpractice.models.User;
import com.surya.daggerpractice.ui.main.MainActivity;
import com.surya.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    @Inject
    Drawable drawable;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    AuthViewModel authViewModel;

    EditText userInput;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userInput = findViewById(R.id.userInputId);
        progressBar = findViewById(R.id.progressBarId);

        findViewById(R.id.loginButtonId).setOnClickListener(this);
        showProgressBar(false);

        authViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel.class);
        subscribeObserver();
        setLogo();
    }

    private void subscribeObserver() {
        authViewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING:
                            showProgressBar(true);
                            break;

                        case AUTHENTICATED:
                            showProgressBar(false);
                            System.out.println("Authenticated =======");
                            loadMainScreen();
                            Toast.makeText(AuthActivity.this, "Authenticated as====="+userAuthResource.data.getEmail(), Toast.LENGTH_SHORT).show();
                            break;

                        case NOT_AUTHENTICATED:
                            showProgressBar(false);
                            System.out.println("User not authenticated=====");
                            Toast.makeText(AuthActivity.this, "User not authenticated=====", Toast.LENGTH_SHORT).show();
                            break;

                        case ERROR:
                            showProgressBar(false);
                            System.out.println("error occured=====");
                            Toast.makeText(AuthActivity.this, userAuthResource.message, Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            }
        });
    }

    private void loadMainScreen() {
        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setLogo() {
        requestManager
                .load(drawable)
                .into((ImageView) findViewById(R.id.imageId));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.loginButtonId:
                attemptLogin();
                break;
        }
    }

    private void attemptLogin() {
        if (TextUtils.isEmpty(userInput.getText().toString())) {
            return;
        }
        authViewModel.authenticationUserWithId(Integer.parseInt(userInput.getText().toString()));
    }
}
