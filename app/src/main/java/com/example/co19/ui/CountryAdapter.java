package com.example.co19.ui;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.co19.R;
import com.example.co19.pojo.CountryModel;
import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private List<CountryModel> countries = new ArrayList<>();

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

    public void setList(List<CountryModel> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
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
}