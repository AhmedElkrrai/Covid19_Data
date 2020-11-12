 package com.example.co19.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.co19.R;
import com.example.co19.pojo.CountryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> implements Filterable {
    private List<CountryModel> countries = new ArrayList<>();
    private List<CountryModel> searchListFull;

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.country.setText(countries.get(position).getCountry());
        holder.newConfirmed.setText(countries.get(position).getNewConfirmed());
        holder.totalConfirmed.setText(countries.get(position).getTotalConfirmed());
        holder.newDeaths.setText(countries.get(position).getNewDeaths());
        holder.totalDeaths.setText(countries.get(position).getTotalDeaths());

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public List<CountryModel> sortCountriesByTotalDeaths(List<CountryModel> countries) {
        Collections.sort(countries, Collections.reverseOrder());
        return countries;
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView country, newConfirmed, totalConfirmed, newDeaths, totalDeaths;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            country = itemView.findViewById(R.id.country);
            newConfirmed = itemView.findViewById(R.id.newConfirmed);
            totalConfirmed = itemView.findViewById(R.id.totalConfirmed);
            newDeaths = itemView.findViewById(R.id.newDeaths);
            totalDeaths = itemView.findViewById(R.id.totalDeaths);

        }
    }

    void setList(List<CountryModel> countries) {
        this.countries = sortCountriesByTotalDeaths(countries);
        searchListFull = new ArrayList<>(countries);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CountryModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0)
                filteredList.addAll(searchListFull);
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CountryModel country : searchListFull) {
                    if (country.getCountry().toLowerCase().contains(filterPattern))
                        filteredList.add(country);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countries.clear();
            countries.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}