package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TicketValidationActivity extends AppCompatActivity {
    private EditText txtTicketID;
    private Button btnCheck, btnBoarded;
    private TextView txtName, txtFromTo, txtSeats, txtClass, txtStatus;
    private RailwayReservationViewModel viewModel;
    private Booking tempBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_validation);

        txtTicketID = findViewById(R.id.txt_ticket_id);
        btnCheck = findViewById(R.id.btn_check);
        btnBoarded = findViewById(R.id.btn_boarded);
        txtName = findViewById(R.id.txt_name);
        txtFromTo = findViewById(R.id.txt_from_to);
        txtSeats = findViewById(R.id.txt_seats);
        txtClass = findViewById(R.id.txt_class);
        txtStatus = findViewById(R.id.txt_status);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ticketID = txtTicketID.getText().toString().trim();
                if (ticketID.isEmpty()) {
                    txtName.setVisibility(View.GONE);
                    txtFromTo.setVisibility(View.GONE);
                    txtSeats.setVisibility(View.GONE);
                    txtClass.setVisibility(View.GONE);
                    txtStatus.setVisibility(View.GONE);
                    btnBoarded.setVisibility(View.GONE);
                    Toast.makeText(TicketValidationActivity.this, "Please enter the Ticket ID", Toast.LENGTH_SHORT).show();
                } else {
                    long tid = Long.parseLong(ticketID);
                    viewModel.getTicket(tid).observe(TicketValidationActivity.this, new Observer<List<Booking>>() {
                        @Override
                        public void onChanged(List<Booking> bookings) {
                            if (bookings.size() == 0) {
                                txtName.setVisibility(View.GONE);
                                txtFromTo.setVisibility(View.GONE);
                                txtSeats.setVisibility(View.GONE);
                                txtClass.setVisibility(View.GONE);
                                txtStatus.setVisibility(View.GONE);
                                btnBoarded.setVisibility(View.GONE);
                                Toast.makeText(TicketValidationActivity.this, "Invalid Ticket ID", Toast.LENGTH_SHORT).show();
                            } else {
                                txtTicketID.getText().clear();
                                txtName.setVisibility(View.VISIBLE);
                                txtFromTo.setVisibility(View.VISIBLE);
                                txtSeats.setVisibility(View.VISIBLE);
                                txtClass.setVisibility(View.VISIBLE);
                                txtStatus.setVisibility(View.VISIBLE);
                                btnBoarded.setVisibility(View.VISIBLE);
                                tempBooking = bookings.get(0);
                                txtName.setText("Train Name: " + tempBooking.getTrainName());
                                txtFromTo.setText(tempBooking.getDeparture() + " - " + tempBooking.getArrival());
                                txtSeats.setText("No. of Seats: " + tempBooking.getTickets_no());
                                txtClass.setText("Class Type: " + tempBooking.getClass_name());
                                txtStatus.setText("Status: " + tempBooking.getStatus());
                                if (bookings.get(0).getStatus().equals("Booked")) {
                                    txtStatus.setTextColor(Color.parseColor("#fc8803"));
                                    btnBoarded.setEnabled(true);
                                } else if (bookings.get(0).getStatus().equals("Boarded")) {
                                    txtStatus.setTextColor(Color.parseColor("#03fc07"));
                                    btnBoarded.setEnabled(false);
                                } else {
                                    txtStatus.setTextColor(Color.parseColor("#ff0000"));
                                    btnBoarded.setEnabled(false);
                                }
                            }
                        }
                    });
                }
            }
        });

        btnBoarded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempBooking.setStatus("Boarded");
                viewModel.update_booking(tempBooking);
                Toast.makeText(TicketValidationActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}