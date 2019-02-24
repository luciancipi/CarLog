package com.lucianamariei.hw.carlog.gui;


import androidx.fragment.app.Fragment;

/**
 * Created by Lucian Amariei on 24.02.2019.
 */
public class FragmentFactory {
    public enum FragmentType {
        OVERVIEW,
        TRIP_LIST,
        ADD_TRIP;
    }

    public static Fragment createFragment(FragmentType type) {
        switch (type) {
            case OVERVIEW:
                return new OverviewFragment();
            case TRIP_LIST:
                return new TripListFragment();
            case ADD_TRIP:
                return new AddTripFragment();
        }
        return null;
    }
}
