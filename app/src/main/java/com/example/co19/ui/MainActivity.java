package com.example.co19.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.co19.R;
import com.example.co19.pojo.CountryModel;
import com.example.co19.pojo.GlobalModel;
import com.example.co19.pojo.SummaryModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final CountryAdapter adapter = new CountryAdapter();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isNetworkConnectionAvailable()) {
            CovidViewModel viewModel = ViewModelProviders.of(this).get(CovidViewModel.class);
            viewModel.getSummary();

            RecyclerView recyclerView = findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

            viewModel.getCountryList().observe(this, new Observer<SummaryModel>() {
                @Override
                public void onChanged(SummaryModel summaryModel) {

                    List<CountryModel> countries = summaryModel.getCountries();
                    GlobalModel globalModel = summaryModel.getGlobal();

                    adapter.setList(countries);
                    setGlobalData(globalModel);

                }
            });
        }

    }

    public void networkConnectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finishAndRemoveTask();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if (isConnected) {
            Log.d("Network", "Connected");
            return true;
        } else {
            networkConnectionDialog();
            Log.d("Network", "Not Connected");
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        return true;
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