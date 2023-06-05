package com.example.strokeprediction.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.strokeprediction.R;
import com.example.strokeprediction.fragment.predict.PredictViewPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PredictFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PredictFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PredictFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PredictFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PredictFragment newInstance(String param1, String param2) {
        PredictFragment fragment = new PredictFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TabLayout tabLayout;
    private ViewPager2 viewPagerFrgPredict;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_predict, container, false);

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPagerFrgPredict = view.findViewById(R.id.view_page_frg_predict);

        PredictViewPageAdapter predictViewPageAdapter = new PredictViewPageAdapter(this);
        viewPagerFrgPredict.setAdapter(predictViewPageAdapter);

        new TabLayoutMediator(tabLayout, viewPagerFrgPredict, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Index");
                    break;
                case 1:
                    tab.setText("Image");
                    break;
            }
        }).attach();
        return view;
    }
}