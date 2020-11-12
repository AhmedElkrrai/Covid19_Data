package com.example.co19.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.co19.data.CovidClient;
import com.example.co19.pojo.SummaryModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidViewModel extends ViewModel {
    MutableLiveData<SummaryModel> covidMutableLiveData = new MutableLiveData<>();


    public void getSummary() {

        CovidClient.getINSTANCE().getSummary().enqueue(new Callback<SummaryModel>() {
            @Override
            public void onResponse(Call<SummaryModel> call, Response<SummaryModel> response) {
                covidMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SummaryModel> call, Throwable t) {

            }
        });


    }

}
