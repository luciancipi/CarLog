package com.lucianamariei.hw.carlog.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Created by Lucian Amariei on 24.02.2019.
 */
@Dao
public interface TripDao {
    @Query("SELECT * FROM trip")
    List<Trip> getAll();

    @Query("SELECT SUM(distance) FROM trip")
    int getTotalDistance();

    @Query("SELECT SUM(fuel) FROM trip")
    int getTotalFuel();

    @Insert
    void insert(Trip trip);

    @Delete
    void delete(Trip trip);
}
