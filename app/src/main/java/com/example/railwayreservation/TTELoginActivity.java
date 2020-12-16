package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TTELoginActivity extends AppCompatActivity {
    private EditText editTxtUsername, editTxtPass;
    private Button btnLogin;
    private RailwayReservationViewModel viewModel;
    private RailwayReservationRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_t_e_login);

        editTxtUsername = findViewById(R.id.edit_txt_username);
        editTxtPass = findViewById(R.id.edit_text_password);
        btnLogin = findViewById(R.id.btn_login);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);
        repository = new RailwayReservationRepository(getApplication());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = editTxtUsername.getText().toString().trim();
                final String password = editTxtPass.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(TTELoginActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            List<TTE> ttes = repository.getTTEList(username);
                            if(ttes.size() == 0) {
                                TTELoginActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(TTELoginActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                if(password.equals(ttes.get(0).getPassword())) {
                                    editTxtUsername.getText().clear();
                                    editTxtPass.getText().clear();
                                    Intent intent = new Intent(TTELoginActivity.this, TTEAccountActivity.class);
                                    intent.putExtra("Name", ttes.get(0).getName());
                                    startActivity(intent);
                                } else {
                                    TTELoginActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(TTELoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}