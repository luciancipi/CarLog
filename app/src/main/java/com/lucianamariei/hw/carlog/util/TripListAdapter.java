package com.lucianamariei.hw.carlog.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucianamariei.hw.carlog.R;
import com.lucianamariei.hw.carlog.database.Trip;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Lucian Amariei on 24.02.2019.
 */
public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.ViewHolder> {

    private List<Trip> trips;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public TripListAdapter(Context context, List<Trip> trips) {
        this.mInflater = LayoutInflater.from(context);
        this.trips = trips;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.trip_list_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trip trip = trips.get(position);
        holder.setFields(trip);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return trips.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView from, to, distance, fuel, notes;

        ViewHolder(View itemView) {
            super(itemView);

            from = itemView.findViewById(R.id.trip_list_row_from);
            to = itemView.findViewById(R.id.trip_list_row_to);
            distance = itemView.findViewById(R.id.trip_list_row_distance);
            fuel = itemView.findViewById(R.id.trip_list_row_fuel);
            notes = itemView.findViewById(R.id.trip_list_row_notes);

            itemView.setOnClickListener(this);
        }

        public void setFields(Trip trip) {
            from.setText("From: " + trip.from);
            to.setText("To: " + trip.to);
            distance.setText("Distance :" + trip.distance + DistanceUnit.KM.getShortName());
            fuel.setText("Fuel: " + trip.fuel + FuelUnit.LITRE.getShortName());
            notes.setText("Notes: " + trip.notes);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Trip getItem(int id) {
        return trips.get(id);
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
