package com.example.co19.data;


import com.example.co19.pojo.SummaryModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryInterface {

    @GET("summary")
    Call<SummaryModel> getSummary();

}
