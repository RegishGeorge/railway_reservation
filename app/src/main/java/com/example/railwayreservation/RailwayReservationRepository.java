package com.example.railwayreservation;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import static android.content.ContentValues.TAG;

public class RailwayReservationRepository {
    private RailwayReservationDao railwayReservationDao;
    private List<User> allUsers;
    private LiveData<List<User>> loginList;
    private LiveData<List<ServicesList>> services;
    private LiveData<List<String>> allStations;
    private LiveData<List<Train>> train;
    private LiveData<List<RouteDetails>> price;
    private LiveData<List<Integer>> stationID;
    private List<TrainSeat> trainSeat;
    private LiveData<List<Booking>> bookings;
    private LiveData<List<TTE>> TTEList;
    private LiveData<List<Booking>> ticket;
    private List<Booking> allBookings;
    private List<TrainSeat> list;

    public RailwayReservationRepository(Application application) {
        RailwayReservationDatabase database = RailwayReservationDatabase.getInstance(application);
        railwayReservationDao = database.railwayReservationDao();
        allStations = railwayReservationDao.getAllStations();
    }

    public void insert_user(User user) {
        new InsertUserAsyncTask(railwayReservationDao).execute(user);
    }

    public void insert_booking(Booking booking) {
        new InsertBookingAsyncTask(railwayReservationDao).execute(booking);
    }

    public void insert_train_seat(TrainSeat trainSeat) {
        new InsertTrainSeatAsyncTask(railwayReservationDao).execute(trainSeat);
    }

    public void insert_tte(TTE tte) {
        new InsertTTEAsyncTask(railwayReservationDao).execute(tte);
    }

    public void update_user(User user) {
        new UpdateUserAsyncTask(railwayReservationDao).execute(user);
    }

    public void update_train_seat(TrainSeat trainSeat) {
        new UpdateTrainSeatAsyncTask(railwayReservationDao).execute(trainSeat);
    }

    public void update_booking(Booking booking) {
        new UpdateBookingAsyncTask(railwayReservationDao).execute(booking);
    }

    public void delete_train_seat(TrainSeat trainSeat) {
        new DeleteTrainSeatAsyncTask(railwayReservationDao).execute(trainSeat);
    }

    public List<User> getAllUsers() {
        allUsers = railwayReservationDao.getAllUsers();
        return allUsers;
    }

    public LiveData<List<String>> getAllStations() {
        return allStations;
    }

    public LiveData<List<User>> getLoginList(String email) {
        loginList = railwayReservationDao.getLoginList(email);
        return loginList;
    }

    public LiveData<List<ServicesList>> getServices(String start, String stop) {
        services = railwayReservationDao.getServices(start, stop);
        return services;
    }

    public LiveData<List<Train>> getTrain(int tid) {
        train = railwayReservationDao.getTrain(tid);
        return train;
    }

    public LiveData<List<RouteDetails>> getPrice(int rid, String start, String stop) {
        price = railwayReservationDao.getPrice(rid, start, stop);
        return price;
    }

    public LiveData<List<Integer>> getStationID(int rid, int start, int stop) {
        stationID = railwayReservationDao.getStationID(rid, start, stop);
        return stationID;
    }

    public List<TrainSeat> getTrainSeat(int rid, int sid, int tid) {
        trainSeat = railwayReservationDao.getTrainSeat(rid, sid, tid);
        return trainSeat;
    }

    public LiveData<List<Booking>> getBookings(String username) {
        bookings = railwayReservationDao.getBookings(username);
        return bookings;
    }

    public LiveData<List<TTE>> getTTEList(String username) {
        TTEList = railwayReservationDao.getTTEList(username);
        return TTEList;
    }

    public LiveData<List<Booking>> getTicket(long tid) {
        ticket = railwayReservationDao.getTicket(tid);
        return ticket;
    }

    public List<Booking> getAllBookings(int rid, int tid) {
        allBookings = railwayReservationDao.getAllBookings(rid, tid);
        return allBookings;
    }

    public List<TrainSeat> getList(int rid, int tid) {
        list = railwayReservationDao.getList(rid, tid);
        return list;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private RailwayReservationDao railwayReservationDao;

        private InsertUserAsyncTask(RailwayReservationDao railwayReservationDao) {
            this.railwayReservationDao = railwayReservationDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            railwayReservationDao.insert_user(users[0]);
            return null;
        }
    }

    private static class InsertBookingAsyncTask extends AsyncTask<Booking, Void, Void> {
        private RailwayReservationDao railwayReservationDao;

        private InsertBookingAsyncTask(RailwayReservationDao railwayReservationDao) {
            this.railwayReservationDao = railwayReservationDao;
        }

        @Override
        protected Void doInBackground(Booking... bookings) {
            railwayReservationDao.insert_booking(bookings[0]);
            return null;
        }
    }

    private static class InsertTrainSeatAsyncTask extends AsyncTask<TrainSeat, Void, Void> {
        private RailwayReservationDao railwayReservationDao;

        private InsertTrainSeatAsyncTask(RailwayReservationDao railwayReservationDao) {
            this.railwayReservationDao = railwayReservationDao;
        }

        @Override
        protected Void doInBackground(TrainSeat... trainSeats) {
            railwayReservationDao.insert_train_seat(trainSeats[0]);
            return null;
        }
    }

    private static class InsertTTEAsyncTask extends AsyncTask<TTE, Void, Void> {
        private RailwayReservationDao railwayReservationDao;

        private InsertTTEAsyncTask(RailwayReservationDao railwayReservationDao) {
            this.railwayReservationDao = railwayReservationDao;
        }

        @Override
        protected Void doInBackground(TTE... ttes) {
            railwayReservationDao.insert_TTE(ttes[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private RailwayReservationDao railwayReservationDao;

        private UpdateUserAsyncTask(RailwayReservationDao railwayReservationDao) {
            this.railwayReservationDao = railwayReservationDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            railwayReservationDao.update_user(users[0]);
            return null;
        }
    }

    private static class UpdateTrainSeatAsyncTask extends AsyncTask<TrainSeat, Void, Void> {
        private RailwayReservationDao railwayReservationDao;

        private UpdateTrainSeatAsyncTask(RailwayReservationDao railwayReservationDao) {
            this.railwayReservationDao = railwayReservationDao;
        }

        @Override
        protected Void doInBackground(TrainSeat... trainSeats) {
            railwayReservationDao.update_train_seat(trainSeats[0]);
            return null;
        }
    }

    private static class UpdateBookingAsyncTask extends AsyncTask<Booking, Void, Void> {
        private RailwayReservationDao railwayReservationDao;

        private UpdateBookingAsyncTask(RailwayReservationDao railwayReservationDao) {
            this.railwayReservationDao = railwayReservationDao;
        }

        @Override
        protected Void doInBackground(Booking... bookings) {
            railwayReservationDao.update_booking(bookings[0]);
            return null;
        }
    }

    private static class DeleteTrainSeatAsyncTask extends AsyncTask<TrainSeat, Void, Void> {
        private RailwayReservationDao railwayReservationDao;

        private DeleteTrainSeatAsyncTask(RailwayReservationDao railwayReservationDao) {
            this.railwayReservationDao = railwayReservationDao;
        }

        @Override
        protected Void doInBackground(TrainSeat... trainSeats) {
            railwayReservationDao.delete_train_seat(trainSeats[0]);
            return null;
        }
    }
}