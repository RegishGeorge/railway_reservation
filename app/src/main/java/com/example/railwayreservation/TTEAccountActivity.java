package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TTEAccountActivity extends AppCompatActivity {
    private TextView txtHello;
    private Button btnTicket, btnTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_t_e_account);

        txtHello = findViewById(R.id.txt_hello);
        btnTicket = findViewById(R.id.btn_ticket);
        btnTrain = findViewById(R.id.btn_train);

        String message = "Hello " + getIntent().getStringExtra("Name");
        txtHello.setText(message);

        btnTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TTEAccountActivity.this, TicketValidationActivity.class);
                startActivity(intent);
            }
        });

        btnTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TTEAccountActivity.this, TrainStatusActivity.class);
                startActivity(intent);
            }
        });
    }
}