package com.lucianamariei.hw.carlog.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Lucian Amariei on 24.02.2019.
 */
@Entity
public class Trip {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    public String from;

    public String to;

    public int distance;

    public int fuel;

    public String notes;
}
