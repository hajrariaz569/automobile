package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.Booking;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateBookingService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.UPDATE_BOOK_STATUS_URL)
    Call<Booking> updateBooking(
            @Field("booking_id") int booking_id,
            @Field("booking_status") String booking_status,
            @Field("payment_amount") int payment_amount
    );

}
