package com.example.strokeprediction.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.strokeprediction.Calculator_Bmi_Activity;
import com.example.strokeprediction.MainActivity2;
import com.example.strokeprediction.Medical_Image_Activity;
import com.example.strokeprediction.Medical_Index_Activity;
import com.example.strokeprediction.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    CardView cardIndex, cardImage, cardLibrary, cardSchedule, cardUser, cardBmi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cardIndex = view.findViewById(R.id.card_index_predict);
        cardImage = view.findViewById(R.id.card_image_predict);
        cardLibrary = view.findViewById(R.id.card_library);
        cardSchedule = view.findViewById(R.id.card_calender);
        cardUser = view.findViewById(R.id.card_user);
        cardBmi = view.findViewById(R.id.card_bmi);

        cardIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIndex = new Intent(view.getContext(), Medical_Index_Activity.class);
                startActivity(intentIndex);
            }
        });

        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(view.getContext(), Medical_Image_Activity.class);
                startActivity(intentImage);
            }
        });

        cardLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), MainActivity2.class);
                Bundle bundle = new Bundle();

                bundle.putInt("library",2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        cardBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBmi = new Intent(view.getContext(), Calculator_Bmi_Activity.class);
                startActivity(intentBmi);
            }
        });

        cardUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), MainActivity2.class);
                Bundle bundle = new Bundle();

                bundle.putInt("user",3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }
}