package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
    private TextView txtHello;
    private Button btnAddTTE, btnModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        String msg = "Hello " + getIntent().getStringExtra("Name");

        txtHello = findViewById(R.id.text_Hello);
        btnAddTTE = findViewById(R.id.btn_add_tte);
        btnModify = findViewById(R.id.btn_modify);

        txtHello.setText(msg);

        btnAddTTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddTTEActivity.class);
                startActivity(intent);
            }
        });

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ModifyTrainActivity.class);
                startActivity(intent);
            }
        });

    }
}