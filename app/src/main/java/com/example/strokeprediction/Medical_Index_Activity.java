package com.example.strokeprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Medical_Index_Activity extends AppCompatActivity {

    Button btnPredict;
    TextView tvResult;
    String url = "https://c236-112-197-193-229.ngrok-free.app/predict";

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

        btnPredict = findViewById(R.id.btn_predict);
        tvResult = findViewById(R.id.tv_result);

        btnPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hit the API -> Volley
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.getString("Stroke");
                                    if(data.equals("1")){
                                        tvResult.setText("Bạn có nguy cơ bị đột quỵ !");
                                    }else{
                                        tvResult.setText("Bạn không có nguy cơ bị đột quỵ");
                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Medical_Index_Activity.this,error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }){

                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("age","20");
                        params.put("hypertension","0");
                        params.put("heartDisease","0");
                        params.put("everMarried","1");
                        params.put("residenceType","1");
                        params.put("avgGlucoseLevel","150");
                        params.put("bmi","22");
                        params.put("genderMale","0");
                        params.put("genderOther","0");
                        params.put("workTypeNeverWorked","0");
                        params.put("workTypePrivate","0");
                        params.put("workTypeSelfEmployed","1");
                        params.put("workTypeChildren","0");
                        params.put("smokingStatusFormerlySmoked","0");
                        params.put("smokingStatusNeverSmoked","1");
                        params.put("smokingStatusSmokes","0");
                        params.put("bmiCatIdeal","1");
                        params.put("bmiCatOverweight","0");
                        params.put("bmiCatObesity","0");
                        params.put("ageCatTeens","0");
                        params.put("ageCatAdults","1");
                        params.put("ageCatMidAdults","0");
                        params.put("ageCatElderly","0");
                        params.put("glucoseCatNormal","1");
                        params.put("glucoseCatHigh","0");
                        params.put("glucoseCatVeryHigh","0");

                        return params;
                    }

                };
                RequestQueue queue = Volley.newRequestQueue(Medical_Index_Activity.this);
                queue.add(stringRequest);
            }
        });
    }
}