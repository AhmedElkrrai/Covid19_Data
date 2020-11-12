package com.example.co19.ui;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.co19.pojo.SummaryModel;
import com.example.co19.repository.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CovidViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<SummaryModel> countryList = new MutableLiveData<>();

    @ViewModelInject
    public CovidViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<SummaryModel> getCountryList() {
        return countryList;
    }

    @SuppressLint("CheckResult")
    public void getSummary() {
        repository.getSummary()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> countryList.setValue(result),
                        error -> Log.e("viwModel", error.getMessage()));
    }
}
