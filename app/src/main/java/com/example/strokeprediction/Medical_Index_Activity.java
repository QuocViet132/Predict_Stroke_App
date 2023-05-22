package com.example.strokeprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Medical_Index_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_index);

        Spinner spnWorkType;
        spnWorkType = (Spinner) findViewById(R.id.spn_work_type);

        final ArrayList<String> arrayWorkType = new ArrayList<String>();
        arrayWorkType.add("Học sinh, Sinh viên");
        arrayWorkType.add("Làm việc cho nhà nước");
        arrayWorkType.add("Tự làm chủ");
        arrayWorkType.add("Tư nhân");
        arrayWorkType.add("Thất nghiệp");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayWorkType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnWorkType.setAdapter(arrayAdapter);

        spnWorkType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Medical_Index_Activity.this,arrayWorkType.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}