package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import static com.example.railwayreservation.BookTicketActivity.START;
import static com.example.railwayreservation.BookTicketActivity.STOP;

public class ServicesListActivity extends AppCompatActivity {
    private TextView txtNoService, txtTitle;
    private CardView cardView;
    private RecyclerView recyclerView;
    private RailwayReservationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);

        txtNoService = findViewById(R.id.txtNoService);
        txtTitle = findViewById(R.id.txt_title);
        cardView = findViewById(R.id.cardView);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ServicesListAdapter adapter = new ServicesListAdapter(this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);
        viewModel.getServices(START, STOP).observe(this, new Observer<List<ServicesList>>() {
            @Override
            public void onChanged(List<ServicesList> services) {
                if (services.size() == 0) {
                    txtTitle.setVisibility(View.GONE);
                    cardView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    txtNoService.setVisibility(View.VISIBLE);
                } else {
                    txtNoService.setVisibility(View.GONE);
                    txtTitle.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter.setRoutes(services);
                }
            }
        });
    }
}