package com.example.railwayreservation;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {@ForeignKey(entity = Route.class, parentColumns = "route_id", childColumns = "route_id"),
        @ForeignKey(entity = Station.class, parentColumns = "station_id", childColumns = "station_id")},
        primaryKeys = {"route_id", "station_id"})
public class RouteDetails {
    private int route_id;
    private int station_id;

    private int station_number;
    private int duration_hr;
    private int duration_min;
    private int ac1_fare;
    private int ac2_fare;
    private int ac3_fare;
    private int cc_fare;
    private int sleeper_fare;

    public RouteDetails(int route_id, int station_id, int station_number, int duration_hr, int duration_min, int ac1_fare, int ac2_fare, int ac3_fare, int cc_fare, int sleeper_fare) {
        this.route_id = route_id;
        this.station_id = station_id;
        this.station_number = station_number;
        this.duration_hr = duration_hr;
        this.duration_min = duration_min;
        this.ac1_fare = ac1_fare;
        this.ac2_fare = ac2_fare;
        this.ac3_fare = ac3_fare;
        this.cc_fare = cc_fare;
        this.sleeper_fare = sleeper_fare;
    }

    public int getRoute_id() {
        return route_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public int getStation_number() {
        return station_number;
    }

    public int getDuration_hr() {
        return duration_hr;
    }

    public int getDuration_min() {
        return duration_min;
    }

    public int getAc1_fare() {
        return ac1_fare;
    }

    public int getAc2_fare() {
        return ac2_fare;
    }

    public int getAc3_fare() {
        return ac3_fare;
    }

    public int getCc_fare() {
        return cc_fare;
    }

    public int getSleeper_fare() {
        return sleeper_fare;
    }
}
