package com.hp.foodareapp.ngo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.hp.foodareapp.R;
import com.hp.foodareapp.Retrofit.Retro;
import com.hp.foodareapp.ngo.Adapters.Food_List_Adapter;
import com.hp.foodareapp.ngo.Models.Food_list_Model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodList extends AppCompatActivity {
    private AppCompatAutoCompleteTextView district;
    private RecyclerView foodlist;
    private Food_list_Model food_list_model;
    private  ArrayList<String> cities;
    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        initViews();

        cities=new ArrayList<>();
        cities.add("Thiruvananthapuram");
        cities.add("Kanyakumari");

        ArrayAdapter<String> d_adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,cities);
        district.setThreshold(1);//will start working from first character
        district.setAdapter(d_adapter);//setting the adapter data into the AutoCompleteTextView
        district.setTextColor(Color.WHITE);

       district.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               selectedCity=cities.get(position);
               Toast.makeText(FoodList.this, "You have selected "+cities.get(position), Toast.LENGTH_SHORT).show();
               new Retro().getApi().FOOD_LIST_MODEL_CALL(selectedCity).enqueue(new Callback<Food_list_Model>() {
                   @Override
                   public void onResponse(Call<Food_list_Model> call, Response<Food_list_Model> response) {
                       food_list_model=response.body();
                       foodlist.setLayoutManager(new GridLayoutManager(FoodList.this,2));

                       foodlist.setAdapter(new Food_List_Adapter(food_list_model,FoodList.this));
                   }

                   @Override
                   public void onFailure(Call<Food_list_Model> call, Throwable t) {

                       Toast.makeText(FoodList.this, "API FAILURE "+t, Toast.LENGTH_SHORT).show();
                   }
               });

           }
       });




    }


    public void initViews() {

        district = (AppCompatAutoCompleteTextView) findViewById(R.id.district);
        foodlist = (RecyclerView) findViewById(R.id.foodlist);

    }

}
