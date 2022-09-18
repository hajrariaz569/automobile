package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.Customer;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CustomerLoginService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.CUSTOMER_LOGIN_URL)
    Call<Customer> customer_login(
            @Field("customer_email") String customer_email,
            @Field("customer_password") String customer_password);
}
