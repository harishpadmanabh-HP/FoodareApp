package com.hp.foodareapp.ngo.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.foodareapp.MainActivity;
import com.hp.foodareapp.R;
import com.hp.foodareapp.donator.Donar_Login;
import com.hp.foodareapp.ngo.Food_Details;
import com.hp.foodareapp.ngo.Models.Food_list_Model;

import nl.dionsegijn.steppertouch.OnStepCallback;
import nl.dionsegijn.steppertouch.StepperTouch;

public class Food_List_Adapter extends RecyclerView.Adapter<Food_List_Adapter.FoodVH> {
    private AppPreferences appPreferences;

    public Food_List_Adapter(Food_list_Model food_list_model, Context context) {
        this.food_list_model = food_list_model;
        this.context = context;
    }

    Food_list_Model food_list_model;
    Context context;


    @NonNull
    @Override
    public FoodVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchview=LayoutInflater.from(parent.getContext()).inflate(R.layout.singlesearchitem, parent, false);
        appPreferences = AppPreferences.getInstance(context,context.getApplicationContext(). getResources().getString(R.string.app_name));

        return new FoodVH(searchview);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodVH holder, int position) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);
        Glide.with(context)
                .asBitmap()
                .load(food_list_model.getFood().get(position).getImage())
                // .load(BASE_POSTER_PATH+model.getPoster_path().trim())
                .apply(options)
                .into(new BitmapImageViewTarget(holder.mealimg) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(bitmap, transition);
                        Palette.from(bitmap).generate(palette -> setBackgroundColor(palette, holder));
                    }
                });

        holder.mealname.setText(food_list_model.getFood().get(position).getFood_name());
        holder.mealname.setPadding(10,0,0,0);
        holder.itemView.setOnClickListener(v -> {

            appPreferences.saveData("foodname",food_list_model.getFood().get(position).getFood_name());
            appPreferences.saveData("foodtype",food_list_model.getFood().get(position).getFood_type());
            appPreferences.saveData("foodquantity",food_list_model.getFood().get(position).getQuantity());
            appPreferences.saveData("foodadd",food_list_model.getFood().get(position).getAddress());
            appPreferences.saveData("foodcity",food_list_model.getFood().get(position).getCity());
            appPreferences.saveData("foodimg",food_list_model.getFood().get(position).getImage());
            appPreferences.saveData("food_id",food_list_model.getFood().get(position).getFood_id());
            appPreferences.saveData("donar_id",food_list_model.getFood().get(position).getDonor_id());

            Intent intent = new Intent(context, Food_Details.class);
//            ActivityOptionsCompat optionsw = ActivityOptionsCompat.
//                    makeSceneTransitionAnimation((Activity) context,
//                            holder.mealimg,
//                            ViewCompat.getTransitionName(holder.mealimg));
//            context.startActivity(intent, optionsw.toBundle());

            context.startActivity(new Intent(context, Food_Details.class));

        });
          }
    private void setBackgroundColor(Palette palette, FoodVH holder ) {
        holder.mealname.setBackgroundColor(palette.getVibrantColor(context
                .getResources().getColor(R.color.black_translucent_60)));
    }

    @Override
    public int getItemCount() {
        return food_list_model.getFood().size();
    }

    class FoodVH extends RecyclerView.ViewHolder{

        ImageView mealimg;
        TextView mealname;

        public FoodVH(@NonNull View itemView) {
            super(itemView);

            mealimg=itemView.findViewById(R.id.searchresult_img);
            mealname=itemView.findViewById(R.id.searchresult_name);



        }
    }
}
