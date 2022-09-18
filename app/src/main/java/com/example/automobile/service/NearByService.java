package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.NearBy;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NearByService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.NEARBY_AREA_URL)
    Call<NearBy> nearBy(
            @Field("na_title") String na_title);
}
