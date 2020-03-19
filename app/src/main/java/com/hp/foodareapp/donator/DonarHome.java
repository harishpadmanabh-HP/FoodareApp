package com.hp.foodareapp.donator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.foodareapp.R;
import com.hp.foodareapp.Retrofit.Retro;
import com.hp.foodareapp.donator.Adapter.FoodlistAdapter;
import com.hp.foodareapp.donator.Models.DonarFoodListModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonarHome extends AppCompatActivity {

    private RecyclerView foodRV;
    String donar_id;
    private AppPreferences appPreferences;
    private Guideline guideline19;
    private ExtendedFloatingActionButton addfoodFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_home);
        initView();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        donar_id = appPreferences.getData("donar_id");
        new Retro().getApi().DONAR_FOOD_LIST_MODEL_CALL(donar_id).enqueue(new Callback<DonarFoodListModel>() {
            @Override
            public void onResponse(Call<DonarFoodListModel> call, Response<DonarFoodListModel> response) {
                DonarFoodListModel donarFoodListModel = response.body();
                if (donarFoodListModel.getStatus().equalsIgnoreCase("success")) {

                    foodRV.setLayoutManager(new GridLayoutManager(DonarHome.this, 2));
                    foodRV.setAdapter(new FoodlistAdapter(donarFoodListModel, DonarHome.this));

                } else {
                    Toast.makeText(DonarHome.this, "No food Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DonarFoodListModel> call, Throwable t) {
                Toast.makeText(DonarHome.this, "DonarFoodListModel api call "+t, Toast.LENGTH_SHORT).show();
            }
        });

        addfoodFAB.setOnClickListener(v -> {
            startActivity(new Intent(DonarHome.this,AddFood.class));
        });

    }

    private void initView() {
        foodRV = findViewById(R.id.foodRV);
        guideline19 = findViewById(R.id.guideline19);
        addfoodFAB = findViewById(R.id.addfoodFAB);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
