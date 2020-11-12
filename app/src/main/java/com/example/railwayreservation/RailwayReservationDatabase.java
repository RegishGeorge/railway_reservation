package com.example.railwayreservation;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Route.class, Station.class, Train.class, RouteDetails.class, Service.class, Booking.class, Seat.class, TrainSeat.class, TTE.class}, version = 1)
public abstract class RailwayReservationDatabase extends RoomDatabase {
    private static final String DB_NAME = "railway_reservation";
    private static RailwayReservationDatabase instance;

    public static synchronized RailwayReservationDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), RailwayReservationDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    public abstract RailwayReservationDao railwayReservationDao();

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new RailwayReservationDatabase.PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private RailwayReservationDao railwayReservationDao;

        public PopulateDBAsyncTask(RailwayReservationDatabase db) {
            railwayReservationDao = db.railwayReservationDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //TODO: Add data to database
            railwayReservationDao.insert_user(new User("regish", "Regish", "George", 9048791233L, "regish", 5000));

            railwayReservationDao.insert_route(new Route("TVC-KGQ", 10));

            railwayReservationDao.insert_station(new Station("Trivandrum Cntl"));
            railwayReservationDao.insert_station(new Station("Kollam Jn"));
            railwayReservationDao.insert_station(new Station("Alleppey"));
            railwayReservationDao.insert_station(new Station("Ernakulam Jn"));
            railwayReservationDao.insert_station(new Station("Thrissur"));
            railwayReservationDao.insert_station(new Station("Shoranur Jn"));
            railwayReservationDao.insert_station(new Station("Tirur"));
            railwayReservationDao.insert_station(new Station("Kozhikode"));
            railwayReservationDao.insert_station(new Station("Kannur"));
            railwayReservationDao.insert_station(new Station("Kasaragod"));

            railwayReservationDao.insert_train(new Train("Nethravathi", 0,0, 5, 10, 15));
            railwayReservationDao.insert_train(new Train("Jan Shatabdi Express", 5, 10, 15, 20, 25));

            railwayReservationDao.insert_route_details(new RouteDetails(1, 1, 1, 0, 0, 0,0,0, 0, 0));
            railwayReservationDao.insert_route_details(new RouteDetails(1, 2, 2, 1, 5, 40, 30, 25, 20, 10));
            railwayReservationDao.insert_route_details(new RouteDetails(1, 3, 3, 2, 45, 80, 60, 50, 40, 20));
            railwayReservationDao.insert_route_details(new RouteDetails(1, 4, 4, 4, 35, 110, 80, 65, 50, 27));
            railwayReservationDao.insert_route_details(new RouteDetails(1, 5, 5, 6, 0, 140, 100, 80, 60, 34));
            railwayReservationDao.insert_route_details(new RouteDetails(1, 6, 6, 7, 10, 165, 118, 92, 70, 41));
            railwayReservationDao.insert_route_details(new RouteDetails(1, 7, 7, 8, 0, 190, 136, 104, 80, 48));
            railwayReservationDao.insert_route_details(new RouteDetails(1, 8, 8, 9, 10, 215, 154, 116, 90, 55));
            railwayReservationDao.insert_route_details(new RouteDetails(1, 9, 9, 10, 45, 245, 174, 131, 100, 62));
            railwayReservationDao.insert_route_details(new RouteDetails(1, 10, 10, 12, 15, 272, 192, 144, 109, 69));

            railwayReservationDao.insert_service(new Service(1, 1, 9, 30));
            railwayReservationDao.insert_service(new Service(1, 2, 23, 15));

            railwayReservationDao.insert_TTE(new TTE("regish", "regish", "Regish"));

            return null;
        }
    }
}
