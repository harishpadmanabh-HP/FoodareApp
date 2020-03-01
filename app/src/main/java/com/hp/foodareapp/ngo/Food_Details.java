package com.hp.foodareapp.ngo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.foodareapp.R;

import nl.dionsegijn.steppertouch.OnStepCallback;
import nl.dionsegijn.steppertouch.StepperTouch;

public class Food_Details extends AppCompatActivity {

    private ImageView foodimage;
    private TextView fname;
    private TextView ftype;
    private TextView fquant;
    private TextView fadd;
    private TextView fcity;
    private StepperTouch stepper;
    private MaterialButton buy;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__details);
        initView();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

        Glide.with(this).load(appPreferences.getData("foodimg")).into(foodimage);
        fname.setText(appPreferences.getData("foodname"));
        ftype.setText(appPreferences.getData("foodtype"));
        fquant.setText("Quantity : "+appPreferences.getData("foodquantity"));
        fadd.setText(appPreferences.getData("foodadd"));
        fcity.setText(appPreferences.getData("foodcity"));

        stepper.stepper.addStepCallback(new OnStepCallback() {
            @Override
            public void onStep(int i, boolean b) {

            }
        });

    }

    private void initView() {
        foodimage = (ImageView) findViewById(R.id.foodimage);
        fname = (TextView) findViewById(R.id.fname);
        ftype = (TextView) findViewById(R.id.ftype);
        fquant = (TextView) findViewById(R.id.fquant);
        fadd = (TextView) findViewById(R.id.fadd);
        fcity = (TextView) findViewById(R.id.fcity);
        stepper = (StepperTouch) findViewById(R.id.stepper);
        buy = (MaterialButton) findViewById(R.id.buy);
    }
}
