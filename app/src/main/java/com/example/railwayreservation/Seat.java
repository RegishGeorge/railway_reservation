package com.example.railwayreservation;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"train_id", "class_name", "seat_no"})
public class Seat {
    private int train_id;
    @NonNull
    private String class_name;
    private int seat_no;
    private long ticket_id;

    public Seat(int train_id, String class_name, int seat_no, long ticket_id) {
        this.train_id = train_id;
        this.class_name = class_name;
        this.seat_no = seat_no;
        this.ticket_id = ticket_id;
    }

    public int getTrain_id() {
        return train_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public int getSeat_no() {
        return seat_no;
    }

    public long getTicket_id() {
        return ticket_id;
    }
}
