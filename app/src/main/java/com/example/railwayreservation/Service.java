package com.example.railwayreservation;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {@ForeignKey(entity = Route.class, parentColumns = "route_id", childColumns = "route_id"),
        @ForeignKey(entity = Train.class, parentColumns = "train_id", childColumns = "train_id")},
        primaryKeys = {"route_id", "train_id"})
public class Service {
    private int route_id;
    private int train_id;

    private int start_time_hr;
    private int start_time_min;

    public Service(int route_id, int train_id, int start_time_hr, int start_time_min) {
        this.route_id = route_id;
        this.train_id = train_id;
        this.start_time_hr = start_time_hr;
        this.start_time_min = start_time_min;
    }

    public int getRoute_id() {
        return route_id;
    }

    public int getTrain_id() {
        return train_id;
    }

    public int getStart_time_hr() {
        return start_time_hr;
    }

    public int getStart_time_min() {
        return start_time_min;
    }
}
