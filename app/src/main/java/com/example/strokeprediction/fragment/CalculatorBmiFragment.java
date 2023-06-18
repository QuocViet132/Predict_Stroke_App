package com.example.strokeprediction.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.example.strokeprediction.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorBmiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorBmiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculatorBmiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorBmiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorBmiFragment newInstance(String param1, String param2) {
        CalculatorBmiFragment fragment = new CalculatorBmiFragment();
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

    EditText etHeight, etWeight, etDayCreate;
    TextView tvResult;
    Button btnSave;
    Float height, weight, bmi;
    String result;
    HalfGauge halfGauge_bmi;
    com.ekn.gruzer.gaugelibrary.Range rangeUnderWeight, rangeIdeal, rangeOverWeight, rangeObesity;

    public void setHalfGauge_bmi(double halfGaugeBmi) {
        rangeUnderWeight = new com.ekn.gruzer.gaugelibrary.Range();
        rangeIdeal = new com.ekn.gruzer.gaugelibrary.Range();
        rangeOverWeight = new com.ekn.gruzer.gaugelibrary.Range();
        rangeObesity = new com.ekn.gruzer.gaugelibrary.Range();

        rangeUnderWeight.setFrom(0); rangeUnderWeight.setTo(19);
        rangeIdeal.setFrom(19); rangeIdeal.setTo(25);
        rangeOverWeight.setFrom(25); rangeOverWeight.setTo(30);
        rangeObesity.setFrom(30); rangeObesity.setTo(83);

        rangeUnderWeight.setColor(Color.parseColor("#4285f4"));
        rangeIdeal.setColor(Color.parseColor("#34A853"));
        rangeOverWeight.setColor(Color.parseColor("#FBBC05"));
        rangeObesity.setColor(Color.parseColor("#E50914"));

        halfGauge_bmi.addRange(rangeUnderWeight);
        halfGauge_bmi.addRange(rangeIdeal);
        halfGauge_bmi.addRange(rangeOverWeight);
        halfGauge_bmi.addRange(rangeObesity);

        halfGauge_bmi.setMinValue(0);
        halfGauge_bmi.setMaxValue(83);
        halfGauge_bmi.setValue(halfGaugeBmi);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator_bmi, container, false);

        etHeight = view.findViewById(R.id.et_fr_calculator_bmi_height);
        etWeight = view.findViewById(R.id.et_fr_calculator_bmi_weight);
        etDayCreate = view.findViewById(R.id.et_fr_calculator_bmi_day_create);
        btnSave = view.findViewById(R.id.btn_save);
        tvResult = view.findViewById(R.id.tv_fr_calculator_bmi_result);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height = Float.parseFloat(etHeight.getText().toString()) / 100;
                weight = Float.parseFloat(etWeight.getText().toString());
                bmi = weight / (height * height);
                bmi = (float) Math.round(bmi);

                result = etDayCreate.getText().toString() + ": BMI = " + bmi.toString();
                tvResult.setText(result);

                setHalfGauge_bmi(30);
            }
        });

        return view;
    }
}