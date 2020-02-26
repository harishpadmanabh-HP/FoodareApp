package com.hp.foodareapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.foodareapp.donator.Donar_Login;
import com.hp.foodareapp.ngo.NGO_Login;

public class MainActivity extends AppCompatActivity {

    private LinearLayout donarLayout;
    private ImageView donatorImg;
    private LinearLayout ngoLayout;
    private ImageView ngoImg;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

   //donar click
        donarLayout.setOnClickListener(view -> {
            appPreferences.saveData("user","donor");

            Intent intent = new Intent(MainActivity.this, Donar_Login.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(MainActivity.this,
                            donatorImg,
                            ViewCompat.getTransitionName(donatorImg));
            startActivity(intent, options.toBundle());
        });

    //ngo click
        ngoLayout.setOnClickListener(view -> {
            appPreferences.saveData("user","ngo");
            Intent intent = new Intent(MainActivity.this, NGO_Login.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(MainActivity.this,
                            ngoImg,
                            ViewCompat.getTransitionName(ngoImg));
            startActivity(intent, options.toBundle());


        });

    }

    private void initView() {
        donarLayout = findViewById(R.id.donar_layout);
        donatorImg = findViewById(R.id.donator_img);
        ngoLayout = findViewById(R.id.ngo_layout);
        ngoImg = findViewById(R.id.ngo_img);
    }
}
