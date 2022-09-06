package com.example.allinoneapplication.service;

import com.example.allinoneapplication.constant.EndPoint;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetAdminComplaintService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.GET_COMPLAIN_URL)
    Call<JsonObject> getComplaints(
            @Field("comp_status") String comp_status
    );
}
