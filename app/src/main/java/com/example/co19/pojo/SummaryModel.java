package com.example.co19.pojo;


import java.util.List;

public class SummaryModel {
    private GlobalModel Global;
    private List<CountryModel> Countries;
    private String Date;

    public SummaryModel(GlobalModel global, List<CountryModel> countries, String date) {
        Global = global;
        Countries = countries;
        Date = date;
    }

    public GlobalModel getGlobal() {
        return Global;
    }

    public void setGlobal(GlobalModel global) {
        this.Global = global;
    }

    public List<CountryModel> getCountries() {
        return Countries;
    }

    public void setCountries(List<CountryModel> countries) {
        Countries = countries;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
