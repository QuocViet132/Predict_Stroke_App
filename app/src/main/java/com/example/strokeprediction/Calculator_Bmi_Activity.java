package com.example.strokeprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ekn.gruzer.gaugelibrary.HalfGauge;

public class Calculator_Bmi_Activity extends AppCompatActivity {

    EditText etHeight, etWeight, etDayCreate;
    TextView tvResult;
    Button btnSave;
    double height, weight, bmi;
    String result;
    HalfGauge halfGauge_bmi;
    com.ekn.gruzer.gaugelibrary.Range rangeUnderWeight, rangeIdeal, rangeOverWeight, rangeObesity;
    double halfGaugeBmi = 0;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_bmi);

        etHeight = findViewById(R.id.et_calculator_bmi_height);
        etWeight = findViewById(R.id.et_calculator_bmi_weight);
        etDayCreate = findViewById(R.id.et_calculator_bmi_day_create);
        btnSave = findViewById(R.id.btn_save);
        tvResult = findViewById(R.id.tv_calculator_bmi_result);
        halfGauge_bmi = findViewById(R.id.halfGauge_bmi_cal);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height = Double.parseDouble(etHeight.getText().toString()) / 100;
                weight = Double.parseDouble(etWeight.getText().toString());
                bmi = weight / (height * height);
                bmi = (double) Math.round(bmi);

                result = etDayCreate.getText().toString() + ":\t\tBMI = " + bmi;
                tvResult.setText(result);

                setHalfGauge_bmi(bmi);
            }
        });
    }
}