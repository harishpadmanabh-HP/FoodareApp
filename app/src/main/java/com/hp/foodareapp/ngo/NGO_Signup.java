package com.hp.foodareapp.ngo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hp.foodareapp.R;
import com.hp.foodareapp.Retrofit.Retro;
import com.hp.foodareapp.Utils.Utils;
import com.hp.foodareapp.ngo.Models.CityModel;
import com.hp.foodareapp.ngo.Models.NGO_Reg_Model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NGO_Signup extends AppCompatActivity {

    private TextInputEditText orgname;
    private TextInputEditText regID;
    private TextInputEditText location;
    private TextInputEditText city;
    private TextInputEditText email;
    private TextInputLayout outlinedTextField;
    private TextInputEditText phone;
    private TextInputEditText pass;
    private Button containedButton;
    private AutoCompleteTextView Area;
    private AutoCompleteTextView district;
    private ConstraintLayout root;
    private ArrayList<String> cities;
    private ArrayList<String> districlist;

    List<String> cl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo__signup);
        initView();

        View rootView = findViewById(android.R.id.content);

        //conecting all TextInputEditText as list
        final List<TextInputEditText> textInputEditTexts = Utils.findViewsWithType(
                rootView, TextInputEditText.class);






        cities=new ArrayList<>();
        districlist=new ArrayList<>();
        districlist.add("Thiruvananthapuram");
        districlist.add("Kanyakumari");
        ArrayAdapter<String> d_adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,districlist);
        district.setThreshold(1);//will start working from first character
        district.setAdapter(d_adapter);//setting the adapter data into the AutoCompleteTextView
        district.setTextColor(Color.BLACK);

        district.setOnItemClickListener((adapterView, view, pos, l) -> {
            cities.clear();
            Area.setText("");
            new Retro().getApi().CITY_MODEL_CALL(districlist.get(pos)).enqueue(new Callback<CityModel>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<CityModel> call, Response<CityModel> response) {
                    CityModel cityModel = response.body();
                    if (cityModel.getStatus().equalsIgnoreCase("success")) {

                        cityModel.getDistrict().forEach(c -> cities.add(c.getCity()));
                        Log.e("CITY", String.valueOf(cities.size()));
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (NGO_Signup.this,android.R.layout.select_dialog_item,cities);
                        Area.setThreshold(1);//will start working from first character
                        Area.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                        Area.setTextColor(Color.BLACK);

                    } else {
                        Toast.makeText(NGO_Signup.this, "No district found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CityModel> call, Throwable t) {
                    Toast.makeText(NGO_Signup.this, ""+t, Toast.LENGTH_SHORT).show();
                }
            });

        });

        containedButton.setOnClickListener(view -> {
            //checking null values for each edittesxt
            boolean noErrors = true;
            for (TextInputEditText textInputEditText : textInputEditTexts) {
               //get strings from each edittext
                String editTextString = textInputEditText.getText().toString();
                if (editTextString.isEmpty()) {
                    textInputEditText.setError(("please fill this field"));
                    noErrors = false;
                } else {
                    textInputEditText.setError(null);
                }
            }

            if (noErrors) {
                // All fields are valid!

                new Retro().getApi().NGO_REG_MODEL_CALL(orgname.getText().toString(),
                        regID.getText().toString(),
                        Area.getText().toString(),
                        district.getText().toString(),
                        email.getText().toString(),
                        phone.getText().toString(),
                        pass.getText().toString()).enqueue(new Callback<NGO_Reg_Model>() {
                    @Override
                    public void onResponse(Call<NGO_Reg_Model> call, Response<NGO_Reg_Model> response) {
                        NGO_Reg_Model ngo_reg_model=response.body();
                        if(ngo_reg_model.getStatus().equalsIgnoreCase("success")){
                            Toast.makeText(NGO_Signup.this, "Joined Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(NGO_Signup.this,NGO_Login.class));
                        }else{
                            Toast.makeText(NGO_Signup.this, ""+ngo_reg_model.getStatus(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NGO_Reg_Model> call, Throwable t) {
                        Toast.makeText(NGO_Signup.this, "API FAILURE"+t, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }

    private void initView() {
        orgname = findViewById(R.id.orgname);
        regID = findViewById(R.id.regID);
        location = findViewById(R.id.location);
        city = findViewById(R.id.city);
        email = findViewById(R.id.email);
        outlinedTextField = findViewById(R.id.outlinedTextField);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        Area= findViewById(R.id.areacity);
        district=findViewById(R.id.district);
        root=findViewById(R.id.container);
        containedButton = findViewById(R.id.containedButton);
    }
}
