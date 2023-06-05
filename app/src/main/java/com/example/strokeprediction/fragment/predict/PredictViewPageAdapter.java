package com.example.strokeprediction.fragment.predict;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.strokeprediction.fragment.AccountFragment;
import com.example.strokeprediction.fragment.HomeFragment;
import com.example.strokeprediction.fragment.LibraryFragment;
import com.example.strokeprediction.fragment.PredictFragment;

public class PredictViewPageAdapter extends FragmentStateAdapter {
    public PredictViewPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new PredictImageFragment();
            case 0:
            default:
                return new PredictIndexFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
