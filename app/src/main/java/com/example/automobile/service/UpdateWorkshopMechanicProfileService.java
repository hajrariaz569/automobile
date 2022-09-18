package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.Workshop;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateWorkshopMechanicProfileService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.UPDATE_WORKSHOP_MECHANIC_PROFILE)
    Call<Workshop> updateWMprofile(
            @Field("wmechanic_id") int wmechanic_id,
            @Field("wmechanic_name") String wmechanic_name,
            @Field("wmechanic_email") String wmechanic_email,
            @Field("wmechanic_contact") String wmechanic_contact,
            @Field("wmechanic_address") String wmechanic_address,
            @Field("wmechanic_lng") Double wmechanic_lng,
            @Field("wmechanic_lat") Double wmechanic_lat
    );
}
