package com.example.railwayreservation;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @NonNull
    @PrimaryKey
    private String email_ID;

    private String first_name;
    private String last_name;
    private long mobile_no;
    private String password;
    private int balance;

    public User(String email_ID, String first_name, String last_name, long mobile_no, String password, int balance) {
        this.email_ID = email_ID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile_no = mobile_no;
        this.password = password;
        this.balance = balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getEmail_ID() {
        return email_ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public long getMobile_no() {
        return mobile_no;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }
}
