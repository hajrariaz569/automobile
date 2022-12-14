package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetFeedbackService {

    @Headers({"Accept: application/json"})
    @GET(EndPoint.GET_FEEDBACK_REPORT_URL)
    Call<JsonObject> getFeedback();

}
