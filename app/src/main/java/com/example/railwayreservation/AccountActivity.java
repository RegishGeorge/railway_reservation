package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {
    private TextView textHello;
    public static String USERNAME;
    private Button btnBook, btnBooked, btnAddBalance, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        USERNAME = getIntent().getStringExtra("Username");
        textHello = findViewById(R.id.text_Hello);
        btnBook = findViewById(R.id.btn_book);
        btnBooked = findViewById(R.id.btn_booked);
        btnAddBalance = findViewById(R.id.btn_addBalance);
        btnEdit = findViewById(R.id.btn_edit);

        String name = "Hello " + getIntent().getStringExtra("Name");
        textHello.setText(name);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, BookTicketActivity.class);
                startActivity(intent);
            }
        });

        btnBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, CurrentlyBookedActivity.class);
                startActivity(intent);
            }
        });

        btnAddBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, AddBalanceActivity.class);
                startActivity(intent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, EditAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}