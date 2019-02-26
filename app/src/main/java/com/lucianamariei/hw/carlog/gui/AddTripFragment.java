package com.lucianamariei.hw.carlog.gui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lucianamariei.hw.carlog.MainActivity;
import com.lucianamariei.hw.carlog.R;
import com.lucianamariei.hw.carlog.database.Trip;
import com.lucianamariei.hw.carlog.util.MeasurementUnitsManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Lucian Amariei on 24.02.2019.
 */
public class AddTripFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_trip, container, false);

        loadUI(root);

        return root;
    }

    private void loadUI(final View root) {
        watchFuelRate(root);

        Button cancel = root.findViewById(R.id.add_trip_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });

        Button save = root.findViewById(R.id.add_trip_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNewTrip(root);
                exit();
            }
        });
    }

    private void watchFuelRate(View root) {
        final EditText distance, fuelRate;
        final TextView fuel;

        distance = root.findViewById(R.id.add_trip_distance);
        fuelRate = root.findViewById(R.id.add_trip_fuel_rate);
        fuel = root.findViewById(R.id.add_trip_fuel);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String d = distance.getText().toString();
                String fr = fuelRate.getText().toString();

                if(d.isEmpty() || fr.isEmpty())
                    return;

                if(!MeasurementUnitsManager.isNumeric(d) || !MeasurementUnitsManager.isNumeric(fr))
                    return;

                int dist = Integer.parseInt(d);
                float rate = Float.parseFloat(fr);

                int fuelCons = MeasurementUnitsManager.fuelRateToValue(dist, rate);

                fuel.setText(Integer.toString(fuelCons) + " " + MeasurementUnitsManager.getFuelUnit().getShortName());
            }
        };

        fuelRate.addTextChangedListener(watcher);
    }

    private void exit() {
        ((MainActivity)getActivity()).addTripCanceled();
    }

    private void saveNewTrip(View root) {
        Trip trip = new Trip();
        trip.from = ((EditText)root.findViewById(R.id.add_trip_from)).getText().toString();
        trip.to = ((EditText)root.findViewById(R.id.add_trip_to)).getText().toString();
        trip.date = ((EditText)root.findViewById(R.id.add_trip_date)).getText().toString();
        trip.distance = Integer.parseInt(((EditText)root.findViewById(R.id.add_trip_distance)).getText().toString());
        trip.fuel = Integer.parseInt(((TextView)root.findViewById(R.id.add_trip_fuel)).getText().toString().split(" ")[0]);
        trip.notes = ((EditText)root.findViewById(R.id.add_trip_notes)).getText().toString();

        ((MainActivity)getActivity()).getDataGetter().insertTrip(trip);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
