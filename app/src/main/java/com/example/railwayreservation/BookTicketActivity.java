package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookTicketActivity extends AppCompatActivity {
    public static String START, STOP;
    private AutoCompleteTextView txtFrom, txtTo;
    private Button btnFind;
    private RailwayReservationViewModel viewModel;
    private ArrayList<String> stations;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);

        txtFrom = findViewById(R.id.txt_from);
        txtTo = findViewById(R.id.txt_to);
        btnFind = findViewById(R.id.btn_find);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);
        viewModel.getAllStations().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> stationsList) {
                addStations(stationsList);
            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                START = txtFrom.getText().toString().trim();
                STOP = txtTo.getText().toString().trim();
                if (START.isEmpty() || STOP.isEmpty()) {
                    Toast.makeText(BookTicketActivity.this, "Please provide both the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    txtFrom.getText().clear();
                    txtTo.getText().clear();
                    Intent intent = new Intent(getBaseContext(), ServicesListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void addStations(List<String> stationsList) {
        stations = new ArrayList<>(stationsList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stations);
        txtFrom.setAdapter(adapter);
        txtTo.setAdapter(adapter);
    }
}