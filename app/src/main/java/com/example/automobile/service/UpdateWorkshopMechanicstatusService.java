package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.Workshop;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateWorkshopMechanicstatusService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.UPDATE_WORKSHOP_MECHANIC_STATUS_URL)
    Call<Workshop> updateStatus(
            @Field("wmechanic_id") int wmechanic_id,
            @Field("wmechanic_status") String wmechanic_status
    );
}
