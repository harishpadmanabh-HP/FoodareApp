package com.hp.foodareapp.ngo;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.foodareapp.R;
import com.hp.foodareapp.Retrofit.Retro;
import com.hp.foodareapp.ngo.Models.BuyFood_model;
import com.hp.foodareapp.ngo.Models.DonarDetailsModel;

import nl.dionsegijn.steppertouch.StepperTouch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private ImageView minus;
    private TextView quantity;
    private ImageView plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__details);
        initView();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        quantity.setText("0");
        Glide.with(this).load(appPreferences.getData("foodimg")).into(foodimage);
        fname.setText(appPreferences.getData("foodname"));
        ftype.setText(appPreferences.getData("foodtype"));
        fquant.setText("Quantity : " + appPreferences.getData("foodquantity"));
        fadd.setText(appPreferences.getData("foodadd"));
        fcity.setText(appPreferences.getData("foodcity"));


        minus.setOnClickListener(v -> {
            if(Integer.parseInt(quantity.getText().toString())>=1)
            {
                quantity.setText(String.valueOf(Integer.parseInt(quantity.getText().toString())-1));
            }else  if(Integer.parseInt(quantity.getText().toString())==0)
            {

            }
        });
        plus.setOnClickListener(v -> {
            quantity.setText(String.valueOf(Integer.parseInt(quantity.getText().toString())+1));

        });

        buy.setOnClickListener(v -> {

            if(Integer.parseInt(quantity.getText().toString()) == 0)
            {
                Toast.makeText(Food_Details.this, "Quantity cant be zero to place a request.", Toast.LENGTH_SHORT).show();
            }else
            {


                String ngo_id =appPreferences.getData("ngo_id");
                String food_id=appPreferences.getData("food_id");
                String quant=quantity.getText().toString();
                String doanr_id=appPreferences.getData("donar_id");


                new Retro().getApi().BUY_FOOD_MODEL_CALL(ngo_id,
                                                      food_id,
                                                          quant).enqueue(new Callback<BuyFood_model>() {
                    @Override
                    public void onResponse(Call<BuyFood_model> call, Response<BuyFood_model> response) {
                        BuyFood_model buyFood_model=response.body();
                        if(buyFood_model.getStatus().equalsIgnoreCase("success")){
                            Toast.makeText(Food_Details.this, "Request Successfull. You can collect food .", Toast.LENGTH_LONG).show();

                            new Retro().getApi().donarDetailsCall(doanr_id).enqueue(new Callback<DonarDetailsModel>() {
                                @Override
                                public void onResponse(Call<DonarDetailsModel> call, Response<DonarDetailsModel> response) {

                                    DonarDetailsModel donarDetailsModel=response.body();
                                    if(donarDetailsModel.getStatus().equalsIgnoreCase("success"))
                                    {
                                        SmsManager smsManager = SmsManager.getDefault();
                                        smsManager.sendTextMessage(donarDetailsModel.getDonor_Details().get(0).getPhone(), null, "Your Food "+ appPreferences.getData("foodname")+" "+quant+" no:s has been requested for donation by the sender of this sms.", null, null);

                                    }else
                                    {
                                        Toast.makeText(Food_Details.this, "User details not found.", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<DonarDetailsModel> call, Throwable t) {

                                }
                            });



                            startActivity(new Intent(Food_Details.this,FoodList.class));

                        }else
                        {
                            Toast.makeText(Food_Details.this, "Some thing went wrong . Try again later", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<BuyFood_model> call, Throwable t) {
                        Toast.makeText(Food_Details.this, "API FAILURE "+t, Toast.LENGTH_SHORT).show();
                    }
                });
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
        buy = (MaterialButton) findViewById(R.id.buy);
        minus = findViewById(R.id.minus);
        quantity = findViewById(R.id.quantity);
        plus = findViewById(R.id.plus);
    }
}
