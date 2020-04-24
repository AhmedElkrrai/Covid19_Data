package com.example.co19.data;

import com.example.co19.pojo.SummaryModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidClient {

    private static final String BASE_URL = "https://api.covid19api.com/";

    private CountryInterface countryInterface;
    private static CovidClient INSTANCE;

    public CovidClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        countryInterface = retrofit.create(CountryInterface.class);
    }

    public static CovidClient getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new CovidClient();
        return INSTANCE;
    }

    public Call<SummaryModel> getSummary() {
        return countryInterface.getSummary();
    }
}
