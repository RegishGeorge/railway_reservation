package com.example.railwayreservation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RailwayReservationDao {
    @Insert
    void insert_user(User user);

    @Insert
    void insert_route(Route route);

    @Insert
    void insert_station(Station station);

    @Insert
    void insert_train(Train train);

    @Insert
    void insert_route_details(RouteDetails routeDetails);

    @Insert
    void insert_service(Service service);

    @Insert
    void insert_booking(Booking booking);

    @Insert
    void insert_seat(Seat seat);

    @Insert
    void insert_train_seat(TrainSeat trainSeat);

    @Insert
    void insert_TTE(TTE tte);

    @Update
    void update_user(User user);

    @Update
    void update_train(Train train);

    @Update
    void update_train_seat(TrainSeat trainSeat);

    @Update
    void update_booking(Booking booking);

    @Delete
    void delete_train_seat(TrainSeat trainSeat);

    @Query("SELECT * FROM USER")
    List<User> getAllUsers();

    @Query("SELECT * FROM USER WHERE email_ID = :email")
    LiveData<List<User>> getLoginList(String email);

    @Query("SELECT R.route_id, T.train_id, T.train_name, T.ac1_no, T.ac2_no, T.ac3_no, T.cc_no, T.sleeper_no, RS.station_number AS start, RE.station_number AS stop, "+
           "CASE WHEN (SC.start_time_min + RS.duration_min >= 60) THEN (SC.start_time_hr + RS.duration_hr + 1) % 24 ELSE (SC.start_time_hr + RS.duration_hr) % 24 END AS departure_hr, "+
           "(SC.start_time_min + RS.duration_min) % 60 AS departure_min, "+
           "CASE WHEN (SC.start_time_min + RE.duration_min >= 60) THEN (SC.start_time_hr + RE.duration_hr + 1) % 24 ELSE (SC.start_time_hr + RE.duration_hr) % 24 END AS arrival_hr, "+
           "(SC.start_time_min + RE.duration_min) % 60 AS arrival_min "+
           "FROM ROUTE R JOIN ROUTEDETAILS RS ON (RS.route_id = R.route_id) JOIN STATION SS ON (SS.station_id = RS.station_id) JOIN ROUTEDETAILS RE ON (RE.route_id = R.route_id) "+
           "JOIN STATION SE ON (SE.station_id = RE.station_id) JOIN SERVICE SC ON (SC.route_id = R.route_id) JOIN TRAIN T ON (T.train_id = SC.train_id) WHERE SS.station_name = :start AND "+
           "SE.station_name = :stop AND RS.station_number < RE.station_number")
    LiveData<List<ServicesList>> getServices(String start, String stop);

    @Query("SELECT station_name FROM STATION")
    LiveData<List<String>> getAllStations();

    @Query("SELECT * FROM TRAIN WHERE train_id = :tid")
    LiveData<List<Train>> getTrain(int tid);

    @Query("SELECT RD.* FROM ROUTEDETAILS RD WHERE RD.route_id = :rid "+
           "AND (RD.station_id = (SELECT S.station_id FROM STATION S WHERE station_name = :start) "+
           "OR station_id = (SELECT S.station_id FROM STATION S WHERE station_name = :stop)) ORDER BY RD.station_number")
    LiveData<List<RouteDetails>> getPrice(int rid, String start, String stop);

    @Query("SELECT station_id FROM ROUTEDETAILS WHERE route_id = :rid AND station_number >= :start AND station_number < :stop")
    LiveData<List<Integer>> getStationID(int rid, int start, int stop);

    @Query("SELECT * FROM TRAINSEAT WHERE route_id = :rid AND station_id = :sid AND train_id = :tid")
    List<TrainSeat> getTrainSeat(int rid, int sid, int tid);

    @Query("SELECT * FROM BOOKING WHERE email = :username ORDER BY ticket_id DESC")
    LiveData<List<Booking>> getBookings(String username);

    @Query("SELECT * FROM TTE WHERE username = :username")
    LiveData<List<TTE>> getTTEList(String username);

    @Query("SELECT * FROM BOOKING WHERE ticket_id = :tid")
    LiveData<List<Booking>> getTicket(long tid);

    @Query("SELECT * FROM BOOKING WHERE route_id = :rid AND train_id = :tid")
    List<Booking> getAllBookings(int rid, int tid);

    @Query("SELECT * FROM TRAINSEAT WHERE route_id = :rid AND train_id = :tid")
    List<TrainSeat> getList(int rid, int tid);
}
