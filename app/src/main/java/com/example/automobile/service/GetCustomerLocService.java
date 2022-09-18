package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.Customer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetCustomerLocService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.GET_CUSTOMER_LOC)
    Call<Customer> getLocation(
            @Field("customer_id") int customer_id
    );

}
