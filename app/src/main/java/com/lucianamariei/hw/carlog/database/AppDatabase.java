package com.lucianamariei.hw.carlog.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Lucian Amariei on 24.02.2019.
 */
@Database(entities = {Trip.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TripDao tripDao();
}

