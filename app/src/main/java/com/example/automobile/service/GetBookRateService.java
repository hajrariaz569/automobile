package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.RateFeedback;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetBookRateService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.GET_BOOK_RATE_URL)
    Call<RateFeedback> ratefeedback(
            @Field("fk_booking_id") int fk_booking_id
    );

}
