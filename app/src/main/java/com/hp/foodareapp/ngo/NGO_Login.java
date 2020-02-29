package com.hp.foodareapp.ngo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.hp.foodareapp.R;
import com.hp.foodareapp.Retrofit.Retro;
import com.hp.foodareapp.ngo.Models.NGO_Login_MOdel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NGO_Login extends AppCompatActivity {

    private ImageView imageView2;
    private EditText phone;
    private EditText pass;
    private MaterialButton login;
    private MaterialButton signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo__login);
        initView();

        signup.setOnClickListener(view -> {

            startActivity(new Intent(NGO_Login.this, NGO_Signup.class));

        });
        login.setOnClickListener(view -> {
            if (!phone.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {

                new Retro().getApi().NGO_LOGIN_CALL(phone.getText().toString(),
                        pass.getText().toString()).enqueue(new Callback<NGO_Login_MOdel>() {
                    @Override
                    public void onResponse(Call<NGO_Login_MOdel> call, Response<NGO_Login_MOdel> response) {
                        NGO_Login_MOdel ngo_login_mOdel = response.body();
                        if (ngo_login_mOdel.getStatus().equalsIgnoreCase("success")) {
                            Toast.makeText(NGO_Login.this, "Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(NGO_Login.this, FoodList.class));
                        } else {
                            Toast.makeText(NGO_Login.this, "" + ngo_login_mOdel.getStatus(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NGO_Login_MOdel> call, Throwable t) {
                        Toast.makeText(NGO_Login.this, "" + t, Toast.LENGTH_SHORT).show();

                    }
                });


            } else {
                Toast.makeText(NGO_Login.this, "Fill all Fields", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initView() {
        imageView2 = findViewById(R.id.imageView2);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
    }
}
