package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.Mechanic;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MechanicLoginService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.MECHANIC_LOGIN_URL)
    Call<Mechanic> mechanic_login(
            @Field("mechanic_email") String mechanic_email,
         @Field("mechanic_password") String mechanic_password);
}
