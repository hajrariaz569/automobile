package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetWorkshopDetailsService {
    @Headers({"Accept: applicaton/json"})
    @GET(EndPoint.GET_MANAGE_WORKSHOP_DETAILS_URL)
    Call<JsonObject> getManageWorkshopdetails();
}
