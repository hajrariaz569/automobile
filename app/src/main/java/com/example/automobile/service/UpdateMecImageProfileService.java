package com.example.automobile.service;

import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.Mechanic;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UpdateMecImageProfileService {
    @Headers({"Accept: application/json"})
    @Multipart
    @POST(EndPoint.UPDATE_MECHANIC_IMAGE)
    Call<Mechanic> mechanic_profile(
            @Part("mechanic_id") RequestBody mechanic_id,
            @Part MultipartBody.Part mechanic_profile_img);
}
