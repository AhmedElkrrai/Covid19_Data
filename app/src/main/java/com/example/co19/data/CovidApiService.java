package com.example.co19.data;


import com.example.co19.pojo.SummaryModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface CovidApiService {

    @GET("summary")
    Observable<SummaryModel> getSummary();

}
