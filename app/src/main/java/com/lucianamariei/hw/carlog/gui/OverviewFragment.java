package com.lucianamariei.hw.carlog.gui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucianamariei.hw.carlog.MainActivity;
import com.lucianamariei.hw.carlog.R;
import com.lucianamariei.hw.carlog.database.DataGetter;
import com.lucianamariei.hw.carlog.util.DistanceUnit;
import com.lucianamariei.hw.carlog.util.FuelUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Lucian Amariei on 24.02.2019.
 */
public class OverviewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        getDatabase();
        updateUI(rootView);

        return rootView;
    }


    private int totalDistance, totalFuel;
    private void getDatabase() {
        DataGetter getter = ((MainActivity)getActivity()).getDataGetter();
        totalDistance = getter.getTotalDistance();
        totalFuel = getter.getTotalFuel();
    }

    private void updateUI(View root) {
        String totalDistanceString = Integer.toString(totalDistance) + " " + DistanceUnit.KM.getLongName();
        ((TextView)root.findViewById(R.id.overview_distance_value)).setText(totalDistanceString);

        String totalFuelString = Integer.toString(totalFuel) + " " + FuelUnit.LITRE.getLongName();
        ((TextView)root.findViewById(R.id.overview_fuel_value)).setText(totalFuelString);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
