package com.example.railwayreservation;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {@ForeignKey(entity = Route.class, parentColumns = "route_id", childColumns = "route_id"),
        @ForeignKey(entity = Station.class, parentColumns = "station_id", childColumns = "station_id"),
        @ForeignKey(entity = Train.class, parentColumns = "train_id", childColumns = "train_id")},
        primaryKeys = {"route_id", "station_id", "train_id"})
public class TrainSeat {
    private int route_id;
    private int station_id;
    private int train_id;

    private int ac1_no, ac2_no, ac3_no, cc_no, sleeper_no;

    public TrainSeat(int route_id, int station_id, int train_id, int ac1_no, int ac2_no, int ac3_no, int cc_no, int sleeper_no) {
        this.route_id = route_id;
        this.station_id = station_id;
        this.train_id = train_id;
        this.ac1_no = ac1_no;
        this.ac2_no = ac2_no;
        this.ac3_no = ac3_no;
        this.cc_no = cc_no;
        this.sleeper_no = sleeper_no;
    }

    public void setAc1_no(int ac1_no) {
        this.ac1_no = ac1_no;
    }

    public void setAc2_no(int ac2_no) {
        this.ac2_no = ac2_no;
    }

    public void setAc3_no(int ac3_no) {
        this.ac3_no = ac3_no;
    }

    public void setCc_no(int cc_no) {
        this.cc_no = cc_no;
    }

    public void setSleeper_no(int sleeper_no) {
        this.sleeper_no = sleeper_no;
    }

    public int getRoute_id() {
        return route_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public int getTrain_id() {
        return train_id;
    }

    public int getAc1_no() {
        return ac1_no;
    }

    public int getAc2_no() {
        return ac2_no;
    }

    public int getAc3_no() {
        return ac3_no;
    }

    public int getCc_no() {
        return cc_no;
    }

    public int getSleeper_no() {
        return sleeper_no;
    }
}
