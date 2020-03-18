package com.hp.foodareapp.Retrofit;

import com.hp.foodareapp.donator.Models.Donar_Reg_Model;
import com.hp.foodareapp.donator.Models.Donar_login_Model;
import com.hp.foodareapp.ngo.Models.BuyFood_model;
import com.hp.foodareapp.ngo.Models.CityModel;
import com.hp.foodareapp.ngo.Models.Food_list_Model;
import com.hp.foodareapp.ngo.Models.NGO_Login_MOdel;
import com.hp.foodareapp.ngo.Models.NGO_Reg_Model;
import com.hp.foodareapp.ngo.NGO_Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Apis {

    @FormUrlEncoded
    @POST("donor_login.php")
    Call<Donar_login_Model> DONAR_LOGIN_MODEL_CALL(@Field("phone") String phone,
                                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("donor_reg.php?")
    Call<Donar_Reg_Model> DONAR_REG_MODEL_CALL(@Field("name") String name,
                                               @Field("location") String location,
                                               @Field("city") String city,
                                               @Field("email") String email,
                                               @Field("phone") String phone,
                                               @Field("password") String password);


    @GET("ngo_login.php")
    Call<NGO_Login_MOdel> NGO_LOGIN_CALL(@Query("phone") String phone,
                                         @Query("password") String password);

    @FormUrlEncoded
    @POST("ngo_reg.php")
    Call<NGO_Reg_Model> NGO_REG_MODEL_CALL(@Field("organisation_name") String organisation_name,
                                           @Field("registered_id") String registered_id,
                                           @Field("location") String location,
                                           @Field("city") String city,
                                           @Field("email") String email,
                                           @Field("phone") String phone,
                                           @Field("password") String password);


    @GET("district_view.php?")
    Call<CityModel> CITY_MODEL_CALL(@Query("district") String district);

    @FormUrlEncoded
    @POST("view_food_city.php?")
    Call<Food_list_Model> FOOD_LIST_MODEL_CALL(@Field("city") String city);

    @GET("buy_food.php?")
    Call<BuyFood_model> BUY_FOOD_MODEL_CALL(@Query("ngo_id") String ngo_id,
                                            @Query("food_id") String food_id,
                                            @Query("qtity") String qtity);


}
