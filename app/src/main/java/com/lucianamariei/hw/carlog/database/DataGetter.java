package com.lucianamariei.hw.carlog.database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.room.Room;

/**
 * Created by Lucian Amariei on 24.02.2019.
 */
public class DataGetter {

    private  AppDatabase database;

    public DataGetter(Context applicationContext) {
        loadDatabase(applicationContext);
    }

    private void loadDatabase(Context applicationContext) {
        database = Room.databaseBuilder(applicationContext,
                AppDatabase.class, "database-name").build();
    }

    public AppDatabase getDatabase() {
        return database;
    }


    public List<Trip> getAllTrips() {
        try {
            return new GetAllAsync().execute(0).get();
        } catch (ExecutionException e) {
            return null;
        } catch (InterruptedException e) {
            return null;
        }
    }

    private class GetAllAsync extends AsyncTask<Integer, Void, List<Trip>> {
        @Override
        protected List<Trip> doInBackground(Integer... integers) {
            List<Trip> trips = database.tripDao().getAll();
            return trips;
        }
    }


    public int getTotalDistance() {
        try {
            return new GetTotalDistanceAsync().execute(0).get();
        } catch (ExecutionException e) {
            return 0;
        } catch (InterruptedException e) {
            return 0;
        }
    }

    private class GetTotalDistanceAsync extends AsyncTask<Integer, Void, Integer> {
        @Override
        protected Integer doInBackground(Integer... integers) {
            int totalDistance = database.tripDao().getTotalDistance();
            return totalDistance;
        }
    }


    public int getTotalFuel() {
        try {
            return new GetTotalFuelAsync().execute(0).get();
        } catch (ExecutionException e) {
            return 0;
        } catch (InterruptedException e) {
            return 0;
        }
    }

    private class GetTotalFuelAsync extends AsyncTask<Integer, Void, Integer> {
        @Override
        protected Integer doInBackground(Integer... integers) {
            int totalFuel = database.tripDao().getTotalFuel();
            return totalFuel;
        }
    }

    public void insertTrip(Trip trip) {
        new InsertTripAsync().execute(trip);
    }

    private class InsertTripAsync extends AsyncTask<Trip, Void, Integer> {
        @Override
        protected Integer doInBackground(Trip... trips) {
            database.tripDao().insert(trips[0]);
            return 0;
        }
    }

    public void deleteTrip(Trip trip) {
        new DeleteTripAsync().execute(trip);
    }

    private class DeleteTripAsync extends AsyncTask<Trip, Void, Integer> {
        @Override
        protected Integer doInBackground(Trip... trips) {
            database.tripDao().delete(trips[0]);
            return 0;
        }
    }
}
