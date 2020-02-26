package com.hp.foodareapp.donator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.hp.foodareapp.R;
import com.hp.foodareapp.Retrofit.Retro;
import com.hp.foodareapp.donator.Models.Donar_Reg_Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Donar_Signup extends AppCompatActivity {

    private EditText dName;
    private EditText dEmail;
    private EditText dPhone;
    private EditText dLocation;
    private EditText dCity;
    private EditText dPassword;
    private MaterialButton dSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar__signup);
        initView();

        dSignup.setOnClickListener(view -> {
            if(!dName.getText().toString().isEmpty() &&
                    !dCity.getText().toString().isEmpty() &&
                    !dLocation.getText().toString().isEmpty() &&
                    !dEmail.getText().toString().isEmpty() &&
                    !dPhone.getText().toString().isEmpty() &&
                    !dPassword.getText().toString().isEmpty() ){
                new Retro().getApi().DONAR_REG_MODEL_CALL(dName.getText().toString(),
                        dLocation.getText().toString(),
                        dCity.getText().toString(),
                        dEmail.getText().toString(),
                        dPhone.getText().toString(),
                        dPassword.getText().toString()).enqueue(new Callback<Donar_Reg_Model>() {
                    @Override
                    public void onResponse(Call<Donar_Reg_Model> call, Response<Donar_Reg_Model> response) {
                        Donar_Reg_Model donar_reg_model=response.body();
                        if (donar_reg_model != null) {
                            if(donar_reg_model.getStatus().equalsIgnoreCase("success")){
                                Toast.makeText(Donar_Signup.this, "Successfully Joined!", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(Donar_Signup.this,Donar_Login.class));
                            }else
                            {
                                Toast.makeText(Donar_Signup.this, ""+donar_reg_model.getStatus(), Toast.LENGTH_SHORT).show();
                            }
                        }else
                        {
                            Toast.makeText(Donar_Signup.this, "No data registered", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Donar_Reg_Model> call, Throwable t) {
                        Toast.makeText(Donar_Signup.this, "API FAILURE : "+t, Toast.LENGTH_SHORT).show();

                    }
                });



            }else
            {
                Toast.makeText(Donar_Signup.this, "Fill All fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        dName = findViewById(R.id.d_name);
        dEmail = findViewById(R.id.d_email);
        dPhone = findViewById(R.id.d_phone);
        dLocation = findViewById(R.id.d_location);
        dCity = findViewById(R.id.d_city);
        dPassword = findViewById(R.id.d_password);
        dSignup = findViewById(R.id.d_signup);
    }
}
