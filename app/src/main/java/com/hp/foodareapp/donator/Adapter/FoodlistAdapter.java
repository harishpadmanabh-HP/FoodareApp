package com.hp.foodareapp.donator.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.foodareapp.R;
import com.hp.foodareapp.donator.FoodView;
import com.hp.foodareapp.donator.Models.DonarFoodListModel;
import com.hp.foodareapp.ngo.Adapters.Food_List_Adapter;

public class FoodlistAdapter extends RecyclerView.Adapter<FoodlistAdapter.Foodvh> {

    DonarFoodListModel donarFoodListModel;
    Context context;
    private AppPreferences appPreferences;

    public FoodlistAdapter(DonarFoodListModel donarFoodListModel, Context context) {
        this.donarFoodListModel = donarFoodListModel;
        this.context = context;
    }

    @NonNull
    @Override
    public Foodvh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchview= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlesearchitem, parent, false);
        appPreferences = AppPreferences.getInstance(context,context.getApplicationContext(). getResources().getString(R.string.app_name));

        return new Foodvh(searchview);

    }

    @Override
    public void onBindViewHolder(@NonNull Foodvh holder, int position) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);
        Glide.with(context)
                .asBitmap()
                .load(donarFoodListModel.getFood().get(position).getImage())
                // .load(BASE_POSTER_PATH+model.getPoster_path().trim())
                .apply(options)
                .into(new BitmapImageViewTarget(holder.mealimg) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(bitmap, transition);
                        Palette.from(bitmap).generate(palette -> setBackgroundColor(palette, holder));
                    }
                });

        holder.mealname.setText(donarFoodListModel.getFood().get(position).getFood_name());
        holder.mealname.setPadding(10,0,0,0);
        holder.itemView.setOnClickListener(v -> {

            appPreferences.saveData("donar_id",donarFoodListModel.getFood().get(position).getDonor_id());
            appPreferences.saveData("food_id_donar",donarFoodListModel.getFood().get(position).getFood_id());
            appPreferences.saveData("food_name",donarFoodListModel.getFood().get(position).getFood_name());
            appPreferences.saveData("food_type",donarFoodListModel.getFood().get(position).getFood_type());
            appPreferences.saveData("food_qty",donarFoodListModel.getFood().get(position).getQuantity());
            appPreferences.saveData("food_image",donarFoodListModel.getFood().get(position).getImage());
            appPreferences.saveData("food_add",donarFoodListModel.getFood().get(position).getAddress());
            appPreferences.saveData("food_city",donarFoodListModel.getFood().get(position).getCity());

            context.startActivity(new Intent(context, FoodView.class));


        });

    }

    @Override
    public int getItemCount() {
        return donarFoodListModel.getFood().size();
    }
    private void setBackgroundColor(Palette palette, Foodvh holder ) {
        holder.mealname.setBackgroundColor(palette.getVibrantColor(context
                .getResources().getColor(R.color.black_translucent_60)));
    }

    class Foodvh extends RecyclerView.ViewHolder{
        ImageView mealimg;
        TextView mealname;
        public Foodvh(@NonNull View itemView) {
            super(itemView);
            mealimg=itemView.findViewById(R.id.searchresult_img);
            mealname=itemView.findViewById(R.id.searchresult_name);

        }
    }
}
