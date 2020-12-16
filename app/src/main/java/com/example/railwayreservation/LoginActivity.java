package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText txtUsername, txtPassword;
    private Button btnLogin;
    private TextView txtRegister;
    private RailwayReservationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.edit_txt_username);
        txtPassword = findViewById(R.id.edit_text_password);
        btnLogin = findViewById(R.id.btn_login);
        txtRegister = findViewById(R.id.txt_register);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = txtUsername.getText().toString().trim();
                final String password = txtPassword.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.getLoginList(email).observe(LoginActivity.this, new Observer<List<User>>() {
                        @Override
                        public void onChanged(List<User> users) {
                            if (users.size() == 0) {
                                Toast.makeText(LoginActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();
                            } else {
                                if (password.equals(users.get(0).getPassword())) {
                                    txtUsername.getText().clear();
                                    txtPassword.getText().clear();
                                    if (email.equals("regish") && password.equals("regish")) {
                                        Intent adminIntent = new Intent(LoginActivity.this, AdminActivity.class);
                                        adminIntent.putExtra("Name", users.get(0).getFirst_name());
                                        startActivity(adminIntent);
                                    } else {
                                        Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                                        intent.putExtra("Username", email);
                                        intent.putExtra("Name", users.get(0).getFirst_name());
                                        startActivity(intent);
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}