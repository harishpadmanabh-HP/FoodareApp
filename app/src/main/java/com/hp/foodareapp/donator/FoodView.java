package com.hp.foodareapp.donator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.foodareapp.R;
import com.hp.foodareapp.Retrofit.Retro;
import com.hp.foodareapp.donator.Models.DeleteFoodModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodView extends AppCompatActivity {

    private ImageView foodimage;
    private TextView fname;
    private TextView ftype;
    private TextView fquant;
    private TextView fadd;
    private TextView fcity;

    private MaterialButton buy;
    private MaterialButton delete;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_view);
        initView();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

        Glide.with(FoodView.this).load(appPreferences.getData("food_image")).into(foodimage);
        fname.setText(appPreferences.getData("food_name"));
        ftype.setText(appPreferences.getData("food_type"));
        fquant.setText(appPreferences.getData("food_qty"));
        fadd.setText(appPreferences.getData("food_add"));
        fcity.setText(appPreferences.getData("food_city"));



        delete.setOnClickListener(v -> {

            new Retro().getApi().deleteFoodCall(appPreferences.getData("food_id_donar")).enqueue(new Callback<DeleteFoodModel>() {
                @Override
                public void onResponse(Call<DeleteFoodModel> call, Response<DeleteFoodModel> response) {
                    DeleteFoodModel deleteFoodModel=response.body();
                    if(deleteFoodModel.getStatus().equalsIgnoreCase("Deleted Sucessfully")){
                        Toast.makeText(FoodView.this, appPreferences.getData("food_name")+" has been deleted.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FoodView.this,DonarHome.class));


                    }else
                    {
                        Toast.makeText(FoodView.this, "Food cant be deleted.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DeleteFoodModel> call, Throwable t) {
                    Toast.makeText(FoodView.this, "DeleteFoodModel api fail "+t, Toast.LENGTH_SHORT).show();

                }
            });

        });

    }

    private void initView() {
        foodimage = findViewById(R.id.foodimage);
        fname = findViewById(R.id.fname);
        ftype = findViewById(R.id.ftype);
        fquant = findViewById(R.id.fquant);
        fadd = findViewById(R.id.fadd);
        fcity = findViewById(R.id.fcity);

        delete = findViewById(R.id.delete);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FoodView.this,DonarHome.class));
    }
}
