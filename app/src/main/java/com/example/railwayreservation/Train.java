package com.example.railwayreservation;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Train {
    @PrimaryKey(autoGenerate = true)
    private int train_id;

    private String train_name;
    private int ac1_no;
    private int ac2_no;
    private int ac3_no;
    private int cc_no;
    private int sleeper_no;

    @Ignore
    public Train(int train_id, String train_name, int ac1_no, int ac2_no, int ac3_no, int cc_no, int sleeper_no) {
        this.train_id = train_id;
        this.train_name = train_name;
        this.ac1_no = ac1_no;
        this.ac2_no = ac2_no;
        this.ac3_no = ac3_no;
        this.cc_no = cc_no;
        this.sleeper_no = sleeper_no;
    }

    public Train(String train_name, int ac1_no, int ac2_no, int ac3_no, int cc_no, int sleeper_no) {
        this.train_name = train_name;
        this.ac1_no = ac1_no;
        this.ac2_no = ac2_no;
        this.ac3_no = ac3_no;
        this.cc_no = cc_no;
        this.sleeper_no = sleeper_no;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
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
}
