package com.example.strokeprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

    String url = "https://0089-112-197-193-229.ngrok-free.app/predict";
    EditText etAge, etBmi, etGlucose;
    String genderMale = "0";
    String genderOther = "0";
    String hypertension = "0";
    String heartDisease = "0";
    String everMarried = "0";
    String residenceType = "0";
    String workTypeNeverWork = "0";
    String workTypePrivate = "0";
    String workTypeSelfEmployed = "0";
    String workTypeChildren = "0";
    String statusFormerlySmoked = "0";
    String statusNeverSmoked = "0";
    String statusSmoked = "0";
    String ageCatTeens = "0";
    String ageCatAdults = "0";
    String ageCatMidAdults = "0";
    String ageCatElderly = "0";
    String bmiCatIdeal = "0";
    String bmiCatOverweight = "0";
    String bmiCatObesity = "0";
    String glucoseCatNormal = "0";
    String glucoseCatHigh = "0";
    String glucoseCatVeryHigh = "0";
    Button btnPredict;
    TextView tvResult;

    public void resetAgeCat() {
        ageCatTeens = "0";
        ageCatAdults = "0";
        ageCatMidAdults = "0";
        ageCatElderly = "0";
    }

    public void resetBmiCat() {
        bmiCatIdeal = "0";
        bmiCatOverweight = "0";
        bmiCatObesity = "0";
    }
    public void resetGlucoseCat() {
        glucoseCatNormal = "0";
        glucoseCatHigh = "0";
        glucoseCatVeryHigh = "0";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_index);

        etAge = (EditText) findViewById(R.id.et_age);
        etBmi = (EditText) findViewById(R.id.et_bmi);
        etGlucose = (EditText) findViewById(R.id.et_glucose);

        Spinner spnWorkType;
        spnWorkType = (Spinner) findViewById(R.id.spn_work_type);

        final ArrayList<String> arrayWorkType = new ArrayList<String>();
        arrayWorkType.add("Học sinh, Sinh viên");
        arrayWorkType.add("Làm việc cho nhà nước");
        arrayWorkType.add("Tự làm chủ");
        arrayWorkType.add("Tư nhân");
        arrayWorkType.add("Thất nghiệp");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayWorkType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnWorkType.setAdapter(arrayAdapter);

        spnWorkType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Medical_Index_Activity.this,arrayWorkType.get(position), Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        workTypeNeverWork = "0";
                        workTypePrivate = "0";
                        workTypeSelfEmployed = "0";
                        workTypeChildren = "1";
                        break;
                    case 1:
                        workTypeNeverWork = "0";
                        workTypePrivate = "0";
                        workTypeSelfEmployed = "0";
                        workTypeChildren = "0";
                        break;
                    case 2:
                        workTypeNeverWork = "0";
                        workTypePrivate = "0";
                        workTypeSelfEmployed = "1";
                        workTypeChildren = "0";
                        break;
                    case 3:
                        workTypeNeverWork = "0";
                        workTypePrivate = "1";
                        workTypeSelfEmployed = "0";
                        workTypeChildren = "0";
                        break;
                    case 4:
                        workTypeNeverWork = "1";
                        workTypePrivate = "0";
                        workTypeSelfEmployed = "0";
                        workTypeChildren = "0";
                        break;
                    default:
                        Toast.makeText(Medical_Index_Activity.this,"Bạn chưa lựa chọn loại công việc", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RadioButton rbGenderMale, rbGenderFemale, rbGenderOther, rbRural, rbUrban, rbYesMarried, rbNoMarried;
        RadioButton rbYesHypertension, rbNoHypertension, rbYesHeartDisease, rbNoHeartDisease;
        RadioButton rbNeverSmoked, rbFormerlySmoked, rbSmoked;

        rbGenderMale = (RadioButton) findViewById(R.id.rb_gender_male);
        rbGenderFemale = (RadioButton) findViewById(R.id.rb_gender_female);
        rbGenderOther = (RadioButton) findViewById(R.id.rb_gender_other);
        rbRural = (RadioButton) findViewById(R.id.rb_rural);
        rbUrban = (RadioButton) findViewById(R.id.rb_urban);
        rbYesMarried = (RadioButton) findViewById(R.id.rb_yes_married);
        rbNoMarried = (RadioButton) findViewById(R.id.rb_no_married);
        rbYesHypertension = (RadioButton) findViewById(R.id.rb_yes_hypertension);
        rbNoHypertension = (RadioButton) findViewById(R.id.rb_no_hypertension);
        rbYesHeartDisease = (RadioButton) findViewById(R.id.rb_yes_heart_disease);
        rbNoHeartDisease = (RadioButton) findViewById(R.id.rb_no_heart_disease);
        rbNeverSmoked = (RadioButton) findViewById(R.id.rb_never_smoked);
        rbFormerlySmoked = (RadioButton) findViewById(R.id.rb_formerly_smoked);
        rbSmoked = (RadioButton) findViewById(R.id.rb_smoked);

        tvResult = findViewById(R.id.tv_result);
        btnPredict = findViewById(R.id.btn_predict);
        btnPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bat event click radioButton thu dong
                if(rbGenderMale.isChecked()){
                    genderMale = "1";
                    genderOther = "0";
                }
                if(rbGenderFemale.isChecked()){
                    genderMale = "0";
                    genderOther = "0";
                }
                if(rbGenderOther.isChecked()){
                    genderMale = "0";
                    genderOther = "1";
                }
                if(rbRural.isChecked()){
                    residenceType = "0";
                }
                if(rbUrban.isChecked()){
                    residenceType = "1";
                }
                if(rbYesMarried.isChecked()){
                    residenceType = "1";
                }
                if(rbNoMarried.isChecked()){
                    residenceType = "0";
                }
                if(rbYesHypertension.isChecked()){
                    hypertension = "1";
                }
                if(rbNoHypertension.isChecked()){
                    hypertension = "0";
                }
                if(rbYesHeartDisease.isChecked()){
                    heartDisease = "1";
                }
                if(rbNoHeartDisease.isChecked()){
                    heartDisease = "0";
                }
                if(rbFormerlySmoked.isChecked()){
                    statusFormerlySmoked = "1";
                    statusNeverSmoked = "0";
                    statusSmoked = "0";
                }
                if(rbNeverSmoked.isChecked()){
                    statusFormerlySmoked = "0";
                    statusNeverSmoked = "1";
                    statusSmoked = "0";
                }
                if(rbSmoked.isChecked()){
                    statusFormerlySmoked = "0";
                    statusNeverSmoked = "0";
                    statusSmoked = "1";
                }

                if(Integer.parseInt(etAge.getText().toString()) < 0 || Integer.parseInt(etAge.getText().toString()) > 200){
                   Toast.makeText(Medical_Index_Activity.this,"Tuổi bạn nhập không đúng. Hãy nhập lại!",Toast.LENGTH_SHORT).show();
                }
                if(Integer.parseInt(etAge.getText().toString()) >= 0 && Integer.parseInt(etAge.getText().toString()) <= 12){
                    resetAgeCat();
                }
                if(Integer.parseInt(etAge.getText().toString()) > 12 && Integer.parseInt(etAge.getText().toString()) <= 17){
                    resetAgeCat();
                    ageCatTeens = "1";
                }
                if(Integer.parseInt(etAge.getText().toString()) > 17 && Integer.parseInt(etAge.getText().toString()) <= 44){
                    resetAgeCat();
                    ageCatAdults = "1";
                }
                if(Integer.parseInt(etAge.getText().toString()) > 44 && Integer.parseInt(etAge.getText().toString()) <= 59){
                    resetAgeCat();
                    ageCatMidAdults = "1";
                }
                if(Integer.parseInt(etAge.getText().toString()) > 59 && Integer.parseInt(etAge.getText().toString()) <= 200){
                    resetAgeCat();
                    ageCatElderly = "1";
                }

                if(Double.parseDouble(etBmi.getText().toString()) < 0 || Double.parseDouble(etBmi.getText().toString()) > 10000){
                    Toast.makeText(Medical_Index_Activity.this,"BMI bạn nhập không đúng. Hãy nhập lại!",Toast.LENGTH_SHORT).show();
                }
                if(Double.parseDouble(etBmi.getText().toString()) >= 0 && Double.parseDouble(etBmi.getText().toString()) <= 18){
                    resetBmiCat();
                }
                if(Double.parseDouble(etBmi.getText().toString()) > 18 && Double.parseDouble(etBmi.getText().toString()) <= 24){
                    resetBmiCat();
                    bmiCatIdeal = "1";
                }
                if(Double.parseDouble(etBmi.getText().toString()) > 24 && Double.parseDouble(etBmi.getText().toString()) <= 29){
                    resetBmiCat();
                    bmiCatOverweight = "1";
                }
                if(Double.parseDouble(etBmi.getText().toString()) > 29 && Double.parseDouble(etBmi.getText().toString()) <= 10000){
                    resetBmiCat();
                    bmiCatObesity = "1";
                }

                if(Double.parseDouble(etGlucose.getText().toString()) < 0 || Double.parseDouble(etGlucose.getText().toString()) > 500){
                    Toast.makeText(Medical_Index_Activity.this,"Glucose bạn nhập không đúng. Hãy nhập lại!",Toast.LENGTH_SHORT).show();
                }
                if(Double.parseDouble(etGlucose.getText().toString()) >= 0 && Double.parseDouble(etGlucose.getText().toString()) <= 89){
                    resetGlucoseCat();
                }
                if(Double.parseDouble(etGlucose.getText().toString()) > 89 && Double.parseDouble(etGlucose.getText().toString()) <= 159){
                    resetGlucoseCat();
                    glucoseCatNormal = "1";
                }
                if(Double.parseDouble(etGlucose.getText().toString()) > 159 && Double.parseDouble(etGlucose.getText().toString()) <= 229){
                    resetGlucoseCat();
                    glucoseCatHigh = "1";
                }
                if(Double.parseDouble(etGlucose.getText().toString()) > 229 && Double.parseDouble(etGlucose.getText().toString()) <= 500){
                    resetGlucoseCat();
                    glucoseCatVeryHigh = "1";
                }

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
                        params.put("age",etAge.getText().toString());
                        params.put("hypertension",hypertension);
                        params.put("heartDisease",heartDisease);
                        params.put("everMarried",everMarried);
                        params.put("residenceType",residenceType);
                        params.put("avgGlucoseLevel",etGlucose.getText().toString());
                        params.put("bmi",etBmi.getText().toString());
                        params.put("genderMale",genderMale);
                        params.put("genderOther",genderOther);
                        params.put("workTypeNeverWorked",workTypeNeverWork);
                        params.put("workTypePrivate",workTypePrivate);
                        params.put("workTypeSelfEmployed",workTypeSelfEmployed);
                        params.put("workTypeChildren",workTypeChildren);
                        params.put("smokingStatusFormerlySmoked",statusFormerlySmoked);
                        params.put("smokingStatusNeverSmoked",statusNeverSmoked);
                        params.put("smokingStatusSmokes",statusSmoked);
                        params.put("bmiCatIdeal",bmiCatIdeal);
                        params.put("bmiCatOverweight",bmiCatOverweight);
                        params.put("bmiCatObesity",bmiCatObesity);
                        params.put("ageCatTeens",ageCatTeens);
                        params.put("ageCatAdults",ageCatAdults);
                        params.put("ageCatMidAdults",ageCatMidAdults);
                        params.put("ageCatElderly",ageCatElderly);
                        params.put("glucoseCatNormal",glucoseCatNormal);
                        params.put("glucoseCatHigh",glucoseCatHigh);
                        params.put("glucoseCatVeryHigh",glucoseCatVeryHigh);

                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(Medical_Index_Activity.this);
                queue.add(stringRequest);

/*
 *****************************  Variables Log  *****************************
                Log.d("age",etAge.getText().toString());
                Log.d("hypertension",hypertension);
                Log.d("heartDisease",heartDisease);
                Log.d("everMarried",everMarried);
                Log.d("residenceType",residenceType);
                Log.d("avgGlucoseLevel",etGlucose.getText().toString());
                Log.d("bmi",etBmi.getText().toString());
                Log.d("genderMale",genderMale);
                Log.d("genderOther",genderOther);
                Log.d("workTypeNeverWorked",workTypeNeverWork);
                Log.d("workTypePrivate",workTypePrivate);
                Log.d("workTypeSelfEmployed",workTypeSelfEmployed);
                Log.d("workTypeChildren",workTypeChildren);
                Log.d("smokingStatusFormerlySmoked",statusFormerlySmoked);
                Log.d("smokingStatusNeverSmoked",statusNeverSmoked);
                Log.d("smokingStatusSmokes",statusSmoked);
                Log.d("bmiCatIdeal",bmiCatIdeal);
                Log.d("bmiCatOverweight",bmiCatOverweight);
                Log.d("bmiCatObesity",bmiCatObesity);
                Log.d("ageCatTeens",ageCatTeens);
                Log.d("ageCatAdults",ageCatAdults);
                Log.d("ageCatMidAdults",ageCatMidAdults);
                Log.d("ageCatElderly",ageCatElderly);
                Log.d("glucoseCatNormal",glucoseCatNormal);
                Log.d("glucoseCatHigh",glucoseCatHigh);
                Log.d("glucoseCatVeryHigh",glucoseCatVeryHigh);
*/
            }
        });
    }
}