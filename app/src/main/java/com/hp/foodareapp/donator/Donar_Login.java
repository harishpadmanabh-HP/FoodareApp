package com.hp.foodareapp.donator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.foodareapp.R;
import com.hp.foodareapp.Retrofit.Retro;
import com.hp.foodareapp.donator.Models.Donar_login_Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Donar_Login extends AppCompatActivity {

    private EditText donarPhone;
    private EditText donarPass;
    private MaterialButton donarLogin;
    private MaterialButton donarSignup;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar__login);
        initView();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

        donarSignup.setOnClickListener(view -> {
            startActivity(new Intent(Donar_Login.this,Donar_Signup.class));
        });
        donarLogin.setOnClickListener(view -> {

            if(!donarPhone.getText().toString().isEmpty() && !donarPass.getText().toString().isEmpty()){
                new Retro().getApi().DONAR_LOGIN_MODEL_CALL(donarPhone.getText().toString(),
                        donarPass.getText().toString()).enqueue(new Callback<Donar_login_Model>() {
                    @Override
                    public void onResponse(Call<Donar_login_Model> call, Response<Donar_login_Model> response) {

                         Donar_login_Model donar_login_model=response.body();
                         Toast.makeText(Donar_Login.this, "Success", Toast.LENGTH_SHORT).show();
                             if(donar_login_model.getStatus().equalsIgnoreCase("success"))
                             {
                                     appPreferences.saveData("donar_id",donar_login_model.getUser_data().getDonor_id());

                                     startActivity(new Intent(Donar_Login.this,DonarHome.class));

                             }else
                             {
                                 Toast.makeText(Donar_Login.this, ""+donar_login_model.getStatus(), Toast.LENGTH_SHORT).show();
                             }

                    }

                    @Override
                    public void onFailure(Call<Donar_login_Model> call, Throwable t) {

                        Toast.makeText(Donar_Login.this, "API FAILURE : "+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }else
            {
                Toast.makeText(Donar_Login.this, "Fill all fields", Toast.LENGTH_SHORT).show();

            }

        });

    }

    private void initView() {
        donarPhone = findViewById(R.id.phone);
        donarPass = findViewById(R.id.pass);
        donarLogin = findViewById(R.id.login);
        donarSignup = findViewById(R.id.signup);
    }
}
