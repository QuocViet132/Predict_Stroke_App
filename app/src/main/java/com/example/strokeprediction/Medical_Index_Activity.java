package com.example.strokeprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
import com.ekn.gruzer.gaugelibrary.HalfGauge;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Medical_Index_Activity extends AppCompatActivity {

    String url = "https://4657-112-197-193-112.ngrok-free.app/predict";
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
    TextView tvResult, tvAnalysisAge, tvAnalysisBmi, tvAnalysisGlucose;

    RadioButton rbGenderMale, rbGenderFemale, rbGenderOther, rbRural, rbUrban, rbYesMarried, rbNoMarried;
    RadioButton rbYesHypertension, rbNoHypertension, rbYesHeartDisease, rbNoHeartDisease;
    RadioButton rbNeverSmoked, rbFormerlySmoked, rbSmoked;
    HalfGauge halfGauge_age, halfGauge_bmi, halfGauge_glucose;

    com.ekn.gruzer.gaugelibrary.Range rangeChildren, rangeTeens, rangeAdults, rangeMidAdults, rangeElderly;
    com.ekn.gruzer.gaugelibrary.Range rangeUnderWeight, rangeIdeal, rangeOverWeight, rangeObesity;
    com.ekn.gruzer.gaugelibrary.Range rangeLowGlucose ,rangeNormalGlucose, rangeHighGlucose, rangeVeryHighGlucose;
    int halfGaugeAge = 0;
    double halfGaugeBmi = 0, halfGaugeGlucose = 0;

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
    public void setHalfGauge_age(int halfGaugeAge) {
        rangeChildren = new com.ekn.gruzer.gaugelibrary.Range();
        rangeTeens = new com.ekn.gruzer.gaugelibrary.Range();
        rangeAdults = new com.ekn.gruzer.gaugelibrary.Range();
        rangeMidAdults = new com.ekn.gruzer.gaugelibrary.Range();
        rangeElderly = new com.ekn.gruzer.gaugelibrary.Range();

        rangeChildren.setFrom(0); rangeChildren.setTo(13);
        rangeTeens.setFrom(13); rangeTeens.setTo(18);
        rangeAdults.setFrom(18); rangeAdults.setTo(45);
        rangeMidAdults.setFrom(45); rangeMidAdults.setTo(60);
        rangeElderly.setFrom(60); rangeElderly.setTo(150);

        rangeChildren.setColor(Color.parseColor("#4285f4"));
        rangeTeens.setColor(Color.parseColor("#34A853"));
        rangeAdults.setColor(Color.parseColor("#FBBC05"));
        rangeMidAdults.setColor(Color.parseColor("#E50914"));
        rangeElderly.setColor(Color.parseColor("#232F3E"));

        halfGauge_age.addRange(rangeChildren);
        halfGauge_age.addRange(rangeTeens);
        halfGauge_age.addRange(rangeAdults);
        halfGauge_age.addRange(rangeMidAdults);
        halfGauge_age.addRange(rangeElderly);

        halfGauge_age.setMinValue(0);
        halfGauge_age.setMaxValue(150);
        halfGauge_age.setValue(halfGaugeAge);
    }

    public void setHalfGauge_bmi(double halfGaugeBmi) {
        rangeUnderWeight = new com.ekn.gruzer.gaugelibrary.Range();
        rangeIdeal = new com.ekn.gruzer.gaugelibrary.Range();
        rangeOverWeight = new com.ekn.gruzer.gaugelibrary.Range();
        rangeObesity = new com.ekn.gruzer.gaugelibrary.Range();

        rangeUnderWeight.setFrom(0); rangeUnderWeight.setTo(18);
        rangeIdeal.setFrom(18); rangeIdeal.setTo(25);
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
    public void setHalfGauge_glucose(double halfGaugeGlucose) {
        rangeLowGlucose = new com.ekn.gruzer.gaugelibrary.Range();
        rangeNormalGlucose = new com.ekn.gruzer.gaugelibrary.Range();
        rangeHighGlucose = new com.ekn.gruzer.gaugelibrary.Range();
        rangeVeryHighGlucose = new com.ekn.gruzer.gaugelibrary.Range();

        rangeLowGlucose.setFrom(0); rangeLowGlucose.setTo(90);
        rangeNormalGlucose.setFrom(90); rangeNormalGlucose.setTo(160);
        rangeHighGlucose.setFrom(160); rangeHighGlucose.setTo(230);
        rangeVeryHighGlucose.setFrom(230); rangeVeryHighGlucose.setTo(380);

        rangeLowGlucose.setColor(Color.parseColor("#4285f4"));
        rangeNormalGlucose.setColor(Color.parseColor("#34A853"));
        rangeHighGlucose.setColor(Color.parseColor("#FBBC05"));
        rangeVeryHighGlucose.setColor(Color.parseColor("#E50914"));

        halfGauge_glucose.addRange(rangeLowGlucose);
        halfGauge_glucose.addRange(rangeNormalGlucose);
        halfGauge_glucose.addRange(rangeHighGlucose);
        halfGauge_glucose.addRange(rangeVeryHighGlucose);

        halfGauge_glucose.setMinValue(0);
        halfGauge_glucose.setMaxValue(380);
        halfGauge_glucose.setValue(halfGaugeGlucose);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_index);
//        try {
//            Intent intentLinkApi = getIntent();
//            url = intentLinkApi.getStringExtra("LinkApi");
//            Log.d("url",url);
//        } catch (Exception NullPointerException) {
////            Toast.makeText(Medical_Index_Activity.this,"URL Default",Toast.LENGTH_SHORT).show();
//        }

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

        halfGauge_age = findViewById(R.id.halfGauge_age);
        halfGauge_bmi = findViewById(R.id.halfGauge_bmi);
        halfGauge_glucose = findViewById(R.id.halfGauge_glucose);
        halfGauge_glucose = findViewById(R.id.halfGauge_glucose);

        tvAnalysisAge = findViewById(R.id.tv_analysis_age);
        tvAnalysisBmi = findViewById(R.id.tv_analysis_bmi);
        tvAnalysisGlucose = findViewById(R.id.tv_analysis_glucose);

        rbGenderMale = findViewById(R.id.rb_gender_male);
        rbGenderFemale =  findViewById(R.id.rb_gender_female);
        rbGenderOther = findViewById(R.id.rb_gender_other);
        rbRural = findViewById(R.id.rb_rural);
        rbUrban = findViewById(R.id.rb_urban);
        rbYesMarried = findViewById(R.id.rb_yes_married);
        rbNoMarried = findViewById(R.id.rb_no_married);
        rbYesHypertension = findViewById(R.id.rb_yes_hypertension);
        rbNoHypertension = findViewById(R.id.rb_no_hypertension);
        rbYesHeartDisease = findViewById(R.id.rb_yes_heart_disease);
        rbNoHeartDisease = findViewById(R.id.rb_no_heart_disease);
        rbNeverSmoked = findViewById(R.id.rb_never_smoked);
        rbFormerlySmoked = findViewById(R.id.rb_formerly_smoked);
        rbSmoked = findViewById(R.id.rb_smoked);

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
                    tvAnalysisAge.setText(getResources().getString(R.string.advice_for_age_children));
                }
                if(Integer.parseInt(etAge.getText().toString()) > 12 && Integer.parseInt(etAge.getText().toString()) <= 17){
                    resetAgeCat();
                    ageCatTeens = "1";
                    tvAnalysisAge.setText(getResources().getString(R.string.advice_for_age_teens));
                }
                if(Integer.parseInt(etAge.getText().toString()) > 17 && Integer.parseInt(etAge.getText().toString()) <= 44){
                    resetAgeCat();
                    ageCatAdults = "1";
                    tvAnalysisAge.setText(getResources().getString(R.string.advice_for_age_adults));
                }
                if(Integer.parseInt(etAge.getText().toString()) > 44 && Integer.parseInt(etAge.getText().toString()) <= 59){
                    resetAgeCat();
                    ageCatMidAdults = "1";
                    tvAnalysisAge.setText(getResources().getString(R.string.advice_for_age_mid_adults));
                }
                if(Integer.parseInt(etAge.getText().toString()) > 59 && Integer.parseInt(etAge.getText().toString()) <= 200){
                    resetAgeCat();
                    ageCatElderly = "1";
                    tvAnalysisAge.setText(getResources().getString(R.string.advice_for_age_elderly));
                }

                if(Double.parseDouble(etBmi.getText().toString()) < 0 || Double.parseDouble(etBmi.getText().toString()) > 10000){
                    Toast.makeText(Medical_Index_Activity.this,"BMI bạn nhập không đúng. Hãy nhập lại!",Toast.LENGTH_SHORT).show();
                }
                if(Double.parseDouble(etBmi.getText().toString()) >= 0 && Double.parseDouble(etBmi.getText().toString()) < 18){
                    resetBmiCat();
                    tvAnalysisBmi.setText(getResources().getString(R.string.advice_for_bmi_underweight));
                }
                if(Double.parseDouble(etBmi.getText().toString()) >= 18 && Double.parseDouble(etBmi.getText().toString()) < 25){
                    resetBmiCat();
                    bmiCatIdeal = "1";
                    tvAnalysisBmi.setText(getResources().getString(R.string.advice_for_bmi_ideal));
                }
                if(Double.parseDouble(etBmi.getText().toString()) >= 25 && Double.parseDouble(etBmi.getText().toString()) < 30){
                    resetBmiCat();
                    bmiCatOverweight = "1";
                    tvAnalysisBmi.setText(getResources().getString(R.string.advice_for_bmi_overweight));
                }
                if(Double.parseDouble(etBmi.getText().toString()) >= 30 && Double.parseDouble(etBmi.getText().toString()) < 84){
                    resetBmiCat();
                    bmiCatObesity = "1";
                    tvAnalysisBmi.setText(getResources().getString(R.string.advice_for_bmi_obesity));
                }

                if(Double.parseDouble(etGlucose.getText().toString()) < 0 || Double.parseDouble(etGlucose.getText().toString()) > 500){
                    Toast.makeText(Medical_Index_Activity.this,"Glucose bạn nhập không đúng. Hãy nhập lại!",Toast.LENGTH_SHORT).show();
                }
                if(Double.parseDouble(etGlucose.getText().toString()) >= 0 && Double.parseDouble(etGlucose.getText().toString()) <= 89){
                    resetGlucoseCat();
                    tvAnalysisGlucose.setText(getResources().getString(R.string.advice_for_glucose_low));
                }
                if(Double.parseDouble(etGlucose.getText().toString()) > 89 && Double.parseDouble(etGlucose.getText().toString()) <= 159){
                    resetGlucoseCat();
                    glucoseCatNormal = "1";
                    tvAnalysisGlucose.setText(getResources().getString(R.string.advice_for_glucose_normal));
                }
                if(Double.parseDouble(etGlucose.getText().toString()) > 159 && Double.parseDouble(etGlucose.getText().toString()) <= 229){
                    resetGlucoseCat();
                    glucoseCatHigh = "1";
                    tvAnalysisGlucose.setText(getResources().getString(R.string.advice_for_glucose_high));
                }
                if(Double.parseDouble(etGlucose.getText().toString()) > 229 && Double.parseDouble(etGlucose.getText().toString()) <= 500){
                    resetGlucoseCat();
                    glucoseCatVeryHigh = "1";
                    tvAnalysisGlucose.setText(getResources().getString(R.string.advice_for_glucose_veryhigh));
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
//                                Toast.makeText(Medical_Index_Activity.this, Objects.requireNonNull(error.getMessage()).toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(Medical_Index_Activity.this, "Loi ", Toast.LENGTH_SHORT).show();
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

                setHalfGauge_age(Integer.parseInt(etAge.getText().toString()));
                setHalfGauge_bmi(Double.parseDouble(etBmi.getText().toString()));
                setHalfGauge_glucose(Double.parseDouble(etGlucose.getText().toString()));

            }
        });
    }
}