package com.hp.foodareapp.ngo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hp.foodareapp.R;
import com.hp.foodareapp.ngo.Models.Food_list_Model;

import nl.dionsegijn.steppertouch.StepperTouch;

public class Food_List_Adapter extends RecyclerView.Adapter<Food_List_Adapter.FoodVH> {
    public Food_List_Adapter(Food_list_Model food_list_model, Context context) {
        this.food_list_model = food_list_model;
        this.context = context;
    }

    Food_list_Model food_list_model;
    Context context;


    @NonNull
    @Override
    public FoodVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.single_food_item, parent, false);

        return new FoodVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodVH holder, int position) {

        holder.foodtype.setVisibility(View.GONE);
        holder.foodadd.setVisibility(View.GONE);
        holder.foodcity.setVisibility(View.GONE);
        holder.buy.setVisibility(View.GONE);



        Glide
                .with(context)
                .load(food_list_model.getFood().get(position).getImage())
             //   .centerCrop()
             //   .placeholder(R.drawable.loading_spinner)
                .into(holder.foodimage);
        holder.foodname.setText(food_list_model.getFood().get(position).getFood_name());
        holder.foodadd.setText(food_list_model.getFood().get(position).getAddress());
        holder.foodcity.setText(food_list_model.getFood().get(position).getCity());
        holder.foodquant.setText(food_list_model.getFood().get(position).getQuantity());
        holder.foodtype.setText(food_list_model.getFood().get(position).getFood_type());


        holder.stepperTouch.stepper.setMin(0);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.foodtype.setVisibility(View.VISIBLE);
                holder.foodadd.setVisibility(View.VISIBLE);
                holder.foodcity.setVisibility(View.VISIBLE);
                holder.buy.setVisibility(View.VISIBLE);

            }
        });

    }

    @Override
    public int getItemCount() {
        return food_list_model.getFood().size();
    }

    class FoodVH extends RecyclerView.ViewHolder{

        ImageView foodimage;
        TextView foodname,foodtype,foodquant,foodadd,foodcity;
        StepperTouch stepperTouch;

        Button buy;


        public FoodVH(@NonNull View itemView) {
            super(itemView);

            foodimage=itemView.findViewById(R.id.singlefoodimage);
            foodname=itemView.findViewById(R.id.singlefoodname);
            foodtype=itemView.findViewById(R.id.singlefoodtype);
            foodquant=itemView.findViewById(R.id.singlefoodquantity);
            foodadd=itemView.findViewById(R.id.singlefoodadd);
            foodcity=itemView.findViewById(R.id.singlefoodcity);
            buy=itemView.findViewById(R.id.addbtn);
            stepperTouch=itemView.findViewById(R.id.stepperTouch);


        }
    }
}
