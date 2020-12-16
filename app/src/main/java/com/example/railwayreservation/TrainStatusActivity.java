package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class TrainStatusActivity extends AppCompatActivity {
    private EditText txtRouteID, txtTrainID;
    private Button btnComplete;
    private RailwayReservationRepository repository;
    private List<Booking> bookings;
    private List<TrainSeat> trainSeats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_status);

        txtRouteID = findViewById(R.id.txt_route_id);
        txtTrainID = findViewById(R.id.txt_train_id);
        btnComplete = findViewById(R.id.btn_completed);

        repository = new RailwayReservationRepository(getApplication());

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String routeID = txtRouteID.getText().toString().trim();
                final String trainID = txtTrainID.getText().toString().trim();
                if (routeID.isEmpty() || trainID.isEmpty()) {
                    Toast.makeText(TrainStatusActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            int rid = Integer.parseInt(routeID);
                            int tid = Integer.parseInt(trainID);
                            bookings = repository.getAllBookings(rid, tid);
                            if (bookings.size() > 0) {
                                txtRouteID.getText().clear();
                                txtTrainID.getText().clear();
                                for (Booking b : bookings) {
                                    b.setStatus("Expired");
                                    repository.update_booking(b);
                                }
                                deleteTrainSeats(rid, tid);
                            } else {
                                TrainStatusActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(TrainStatusActivity.this, "No bookings found for this train!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                            }
                        }
                    });

                }
            }
        });
    }

    private void deleteTrainSeats(final int rid, final int tid) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                trainSeats = repository.getList(rid, tid);
                if (trainSeats.size() > 0) {
                    for (TrainSeat ts : trainSeats) {
                        repository.delete_train_seat(ts);
                    }
                }
                TrainStatusActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TrainStatusActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }
}