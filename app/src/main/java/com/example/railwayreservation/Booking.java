package com.example.railwayreservation;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Booking {
    @PrimaryKey
    private long ticket_id;

    private String email;
    private int route_id;
    private int train_id;
    private String trainName;
    private String class_name;
    private String departure;
    private String arrival;
    private String start_time;
    private String stop_time;
    private int tickets_no;
    private String status;

    public Booking(long ticket_id, String email, int route_id, int train_id, String trainName, String class_name, String departure, String arrival, String start_time, String stop_time, int tickets_no, String status) {
        this.ticket_id = ticket_id;
        this.email = email;
        this.route_id = route_id;
        this.train_id = train_id;
        this.trainName = trainName;
        this.class_name = class_name;
        this.departure = departure;
        this.arrival = arrival;
        this.start_time = start_time;
        this.stop_time = stop_time;
        this.tickets_no = tickets_no;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTicket_id() {
        return ticket_id;
    }

    public String getEmail() {
        return email;
    }

    public int getRoute_id() {
        return route_id;
    }

    public int getTrain_id() {
        return train_id;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getStop_time() {
        return stop_time;
    }

    public int getTickets_no() {
        return tickets_no;
    }

    public String getStatus() {
        return status;
    }
}
