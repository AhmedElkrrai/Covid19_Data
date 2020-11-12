package com.example.co19.repository;

import com.example.co19.data.CovidApiService;
import com.example.co19.pojo.SummaryModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private CovidApiService covidApiService;

    @Inject
    public Repository(CovidApiService covidApiService) {
        this.covidApiService = covidApiService;
    }

    public Observable<SummaryModel> getSummary() {
        return covidApiService.getSummary();
    }

}
