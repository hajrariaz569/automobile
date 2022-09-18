package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetTopMechanicService {
    @Headers({"Accept: applicaton/json"})
    @GET(EndPoint.GET_TOP_MECH_URL)
    Call<JsonObject> getTopMechanic();
}