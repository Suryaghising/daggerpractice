package com.surya.daggerpractice.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.surya.daggerpractice.R;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

     static final String TAG = "ProfileFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Profile fragment created.====");
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }
}
