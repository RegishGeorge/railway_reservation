package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import static com.example.railwayreservation.AccountActivity.USERNAME;

public class CurrentlyBookedActivity extends AppCompatActivity {
    private TextView txtTitle, txtNoTickets;
    private RecyclerView recyclerView;
    private RailwayReservationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_booked);

        txtTitle = findViewById(R.id.title);
        txtNoTickets = findViewById(R.id.txt_no_tickets);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BookedTicketsAdapter adapter = new BookedTicketsAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);
        viewModel.getBookings(USERNAME).observe(this, new Observer<List<Booking>>() {
            @Override
            public void onChanged(List<Booking> bookings) {
                if (bookings.size() > 0) {
                    txtTitle.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    txtNoTickets.setVisibility(View.GONE);
                    adapter.setTickets(bookings);
                } else {
                    txtNoTickets.setVisibility(View.VISIBLE);
                    txtTitle.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
    }
}