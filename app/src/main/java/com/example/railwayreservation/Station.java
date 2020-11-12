package com.example.railwayreservation;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Station {
    @PrimaryKey(autoGenerate = true)
    private int station_id;

    private String station_name;

    public Station(String station_name) {
        this.station_name = station_name;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public String getStation_name() {
        return station_name;
    }
}
