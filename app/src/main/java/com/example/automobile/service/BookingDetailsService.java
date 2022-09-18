package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.Booking;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BookingDetailsService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.BOOKING_DETAILS_URL)
    Call<Booking> booking_details(
            @Field("fk_id") int fk_id,
            @Field("fk_customer_id") int fk_customer_id,
            @Field("user_type") String user_type,
            @Field("booking_fee") int booking_fee,
            @Field("booking_description") String booking_description);
}
