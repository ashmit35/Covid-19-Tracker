package com.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.covid19tracker.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private  Context context;
    private List<Model> data;

    public Adapter(Context context,List<Model> data){
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.testing,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.confirmed.setText(data.get(position).getConfirmed());
        holder.active.setText(data.get(position).getActive());
        holder.recovered.setText(data.get(position).getRecovered());
        holder.deceased.setText(data.get(position).getDeceased());
        holder.city.setText(data.get(position).getCity());
        holder.state.setText(data.get(position).getState());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView city,state,confirmed,active,recovered,deceased;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.city_id);
            state = itemView.findViewById(R.id.state_id);
            confirmed = itemView.findViewById(R.id.confirmed_id);
            active = itemView.findViewById(R.id.active_id);
            recovered = itemView.findViewById(R.id.recovered_id);
            deceased = itemView.findViewById(R.id.deceased_id);

        }
    }
}
