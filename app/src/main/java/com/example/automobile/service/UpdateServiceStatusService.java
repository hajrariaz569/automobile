package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.Service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateServiceStatusService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.UPDATE_SERVICE_STATUS_URL)
    Call<Service> updateServiceStatus(
            @Field("service_id") int service_id,
            @Field("service_status") String service_status
    );

}
