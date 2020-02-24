package com.hp.foodareapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout donarLayout;
    private ImageView donatorImg;
    private LinearLayout ngoLayout;
    private ImageView ngoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        donarLayout = findViewById(R.id.donar_layout);
        donatorImg = findViewById(R.id.donator_img);
        ngoLayout = findViewById(R.id.ngo_layout);
        ngoImg = findViewById(R.id.ngo_img);
    }
}
