package com.example.co19.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.co19.R;
import com.example.co19.pojo.CountryModel;
import com.example.co19.pojo.GlobalModel;
import com.example.co19.pojo.SummaryModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CovidViewModel covidViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        covidViewModel = ViewModelProviders.of(this).get(CovidViewModel.class);
        covidViewModel.getSummary();


        RecyclerView recyclerView = findViewById(R.id.recycler);
        final CountryAdapter adapter = new CountryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        covidViewModel.covidMutableLiveData.observe(this, new Observer<SummaryModel>() {
            @Override
            public void onChanged(SummaryModel summaryModel) {

                List<CountryModel> countries = summaryModel.getCountries();
                GlobalModel globalModel = summaryModel.getGlobal();

                adapter.setList(countries);
                setGlobalData(globalModel);

            }
        });

    }

    private void setGlobalData(GlobalModel model) {
        TextView newConfirmed, totalConfirmed, newDeaths, totalDeaths;

        newConfirmed = findViewById(R.id.global_newConfirmed);
        totalConfirmed = findViewById(R.id.global_totalConfirmed);
        newDeaths = findViewById(R.id.global_newDeaths);
        totalDeaths = findViewById(R.id.global_total_Deaths);

        newConfirmed.setText(model.getNewConfirmed());
        totalConfirmed.setText(model.getTotalConfirmed());
        newDeaths.setText(model.getNewDeaths());
        totalDeaths.setText(model.getTotalDeaths());
    }
}