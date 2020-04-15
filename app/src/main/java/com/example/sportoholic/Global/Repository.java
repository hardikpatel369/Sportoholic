package com.example.sportoholic.Global;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sportoholic.RetrofitApi.InterfaceRetrofit;
import com.example.sportoholic.RetrofitApi.Schedule.ClsSchedule;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private Context context;

    public Repository(Context context) {
        this.context = context;
    }

    public LiveData<ClsSchedule> GetSchedule() {
        final MutableLiveData<ClsSchedule> mutableLiveData = new MutableLiveData<>();
        InterfaceRetrofit Interface = ApiClient.getRetrofitInstance().create(InterfaceRetrofit.class);

        Call<ClsSchedule> call = Interface.getSchedule();
        Log.e("--URL--", "************  before call : " + call.request().url());

        call.enqueue(new Callback<ClsSchedule>() {
            @Override
            public void onResponse(Call<ClsSchedule> call, Response<ClsSchedule> response) {
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetSchedule---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsSchedule> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: GetSchedule" + t.toString());
            }
        });
        return mutableLiveData;
    }
}
