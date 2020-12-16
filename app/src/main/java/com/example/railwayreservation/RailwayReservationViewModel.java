package com.example.railwayreservation;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import static android.content.ContentValues.TAG;

public class RailwayReservationViewModel extends AndroidViewModel {
    private RailwayReservationRepository repository;
    private LiveData<List<User>> loginList;
    private LiveData<List<ServicesList>> services;
    private LiveData<List<String>> allStations;
    private LiveData<List<Train>> train;
    private LiveData<List<RouteDetails>> price;
    private LiveData<List<Integer>> stationID;
    private LiveData<List<Booking>> bookings;
    private LiveData<List<TTE>> TTEList;
    private LiveData<List<Booking>> ticket;
    private LiveData<List<TrainSeat>> trainList;

    public RailwayReservationViewModel(@NonNull Application application) {
        super(application);
        repository = new RailwayReservationRepository(application);
        allStations = repository.getAllStations();
    }

    public void insert_user(User user) {
        repository.insert_user(user);
    }

    public void insert_booking(Booking booking) {
        repository.insert_booking(booking);
    }

    public void insert_train_seat(TrainSeat trainSeat) {
        repository.insert_train_seat(trainSeat);
    }

    public void insert_tte(TTE tte) {
        repository.insert_tte(tte);
    }

    public void update_user(User user) {
        repository.update_user(user);
    }

    public void update_train_seat(TrainSeat trainSeat) {
        repository.update_train_seat(trainSeat);
    }

    public void update_booking(Booking booking) {
        repository.update_booking(booking);
    }

    public void update_train(Train train) {
        repository.update_train(train);
    }

    public void delete_train_seat(TrainSeat trainSeat) {
        repository.delete_train_seat(trainSeat);
    }

    public LiveData<List<String>> getAllStations() {
        return allStations;
    }

    public LiveData<List<User>> getLoginList(String email) {
        loginList = repository.getLoginList(email);
        return loginList;
    }

    public LiveData<List<ServicesList>> getServices(String start, String stop) {
        services = repository.getServices(start, stop);
        return services;
    }

    public LiveData<List<Train>> getTrain(int tid) {
        train = repository.getTrain(tid);
        return train;
    }

    public LiveData<List<RouteDetails>> getPrice(int rid, String start, String stop) {
        price = repository.getPrice(rid, start, stop);
        return price;
    }

    public LiveData<List<Integer>> getStationID(int rid, int start, int stop) {
        stationID = repository.getStationID(rid, start, stop);
        return stationID;
    }

    public LiveData<List<Booking>> getBookings(String username) {
        bookings = repository.getBookings(username);
        return bookings;
    }

    public LiveData<List<Booking>> getTicket(long tid) {
        ticket = repository.getTicket(tid);
        return ticket;
    }
}
