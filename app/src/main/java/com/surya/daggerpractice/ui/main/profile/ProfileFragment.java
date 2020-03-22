package com.surya.daggerpractice.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.surya.daggerpractice.R;
import com.surya.daggerpractice.models.User;
import com.surya.daggerpractice.ui.auth.AuthResource;
import com.surya.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private ProfileViewModel profileViewModel;
    TextView emailText, nameText, websiteText;

    @Inject
    ViewModelProviderFactory providerFactory;

     static final String TAG = "ProfileFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Profile fragment created.====");
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        emailText = view.findViewById(R.id.emailId);
        nameText = view.findViewById(R.id.nameId);
        websiteText = view.findViewById(R.id.websiteId);
        profileViewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);

        subscribeObserver();

    }

    public void subscribeObserver() {
        profileViewModel.getAuthUser().removeObservers(getViewLifecycleOwner());
        profileViewModel.getAuthUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case AUTHENTICATED:
                            setUserDetail(userAuthResource.data);
                            break;

                        case ERROR:
                            setErrorDetail(userAuthResource.message);
                            break;

                    }
                }
            }
        });
    }

    private void setUserDetail(User user) {
        emailText.setText(user.getEmail());
        nameText.setText(user.getName());
        websiteText.setText(user.getWebsite());
    }

    private void setErrorDetail(String message) {
        emailText.setText(message);
        nameText.setText("Error=====");
        websiteText.setText("Error=====");
    }
}
