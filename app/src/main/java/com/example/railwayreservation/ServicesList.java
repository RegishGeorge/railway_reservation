package com.example.railwayreservation;

public class ServicesList {
    private int route_id;
    private int train_id;
    private String train_name;
    private int ac1_no, ac2_no, ac3_no, cc_no, sleeper_no;
    private int start, stop;
    private int departure_hr;
    private int departure_min;
    private int arrival_hr;
    private int arrival_min;

    public ServicesList(int route_id, int train_id, String train_name, int ac1_no, int ac2_no, int ac3_no, int cc_no, int sleeper_no, int start, int stop, int departure_hr, int departure_min, int arrival_hr, int arrival_min) {
        this.route_id = route_id;
        this.train_id = train_id;
        this.train_name = train_name;
        this.ac1_no = ac1_no;
        this.ac2_no = ac2_no;
        this.ac3_no = ac3_no;
        this.cc_no = cc_no;
        this.sleeper_no = sleeper_no;
        this.start = start;
        this.stop = stop;
        this.departure_hr = departure_hr;
        this.departure_min = departure_min;
        this.arrival_hr = arrival_hr;
        this.arrival_min = arrival_min;
    }

    public int getRoute_id() {
        return route_id;
    }

    public int getTrain_id() {
        return train_id;
    }

    public String getTrain_name() {
        return train_name;
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

    public int getStart() {
        return start;
    }

    public int getStop() {
        return stop;
    }

    public int getDeparture_hr() {
        return departure_hr;
    }

    public int getDeparture_min() {
        return departure_min;
    }

    public int getArrival_hr() {
        return arrival_hr;
    }

    public int getArrival_min() {
        return arrival_min;
    }
}
