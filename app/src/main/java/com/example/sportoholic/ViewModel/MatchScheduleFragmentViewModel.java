package com.example.sportoholic.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sportoholic.Global.Repository;
import com.example.sportoholic.RetrofitApi.Schedule.ClsSchedule;

public class MatchScheduleFragmentViewModel extends AndroidViewModel {

    private Repository mRepository;

    public MatchScheduleFragmentViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);
    }

    public LiveData<ClsSchedule> getSchedule(){
        return  mRepository.GetSchedule();
    }
}
