package com.lucianamariei.hw.carlog.gui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.lucianamariei.hw.carlog.MainActivity;
import com.lucianamariei.hw.carlog.R;
import com.lucianamariei.hw.carlog.database.DataGetter;
import com.lucianamariei.hw.carlog.database.Trip;
import com.lucianamariei.hw.carlog.util.TripListAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Lucian Amariei on 24.02.2019.
 */
public class TripListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trip_list, container, false);

        getDatabase();
        updateUI(rootView);

        return rootView;
    }


    private List<Trip> trips;
    private void getDatabase() {
        DataGetter getter = ((MainActivity)getActivity()).getDataGetter();
        trips = getter.getAllTrips();
    }

    private TripListAdapter adapter;
    private boolean isDeleting = false;
    private void updateUI(View root) {
        //set up buttons
        ImageView deleteTripButton = root.findViewById(R.id.trip_list_delete_trip_button);
        deleteTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDeleting = !isDeleting;
                if(isDeleting == true) {
                    Toast toast = Toast.makeText(getContext(), "click on a trip to delete it", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
            }
        });

        ImageView addTripButton = root.findViewById(R.id.trip_list_add_trip_button);
        addTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).loadAddTripFragment();
            }
        });

        // set up the RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.trip_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new TripListAdapter(getContext(), trips);
        adapter.setClickListener(new TripListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(isDeleting) {
                    deleteTrip(trips.get(position));
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void deleteTrip(Trip trip) {
        DataGetter getter = ((MainActivity)getActivity()).getDataGetter();
        getter.deleteTrip(trip);
        adapter.removeTrip(trip);
        reload();
    }

    private void reload() {
        ((MainActivity)getActivity()).reloadTrips();
    }
}
