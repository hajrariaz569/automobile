package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.Complaint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DeleteComplainsService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.DELETE_COMPLAIN_URL)
    Call<Complaint> deleteComp(
            @Field("id") int id,
            @Field("type") String type
    );

}
