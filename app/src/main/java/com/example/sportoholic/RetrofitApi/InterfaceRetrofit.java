package com.example.sportoholic.RetrofitApi;

import com.example.sportoholic.RetrofitApi.Schedule.ClsSchedule;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceRetrofit {

    @GET("match/schedule")
    Call<ClsSchedule> getSchedule();
}
