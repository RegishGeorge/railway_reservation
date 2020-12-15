package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private EditText txtFirstName, txtLastName, txtEmail, txtMobile, txtPassword, txtConfirm;
    private Button btnRegister;
    private RailwayReservationViewModel viewModel;
    private RailwayReservationRepository repository;
    private boolean alreadyExist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtFirstName = findViewById(R.id.edit_text_first_name);
        txtLastName = findViewById(R.id.edit_text_last_name);
        txtEmail = findViewById(R.id.edit_text_email);
        txtMobile = findViewById(R.id.edit_text_mobile);
        txtPassword = findViewById(R.id.edit_text_password);
        txtConfirm = findViewById(R.id.edit_text_confirm_password);
        btnRegister = findViewById(R.id.btn_register);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);
        repository = new RailwayReservationRepository(getApplication());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstName, lastName, email, password, confirm;
                alreadyExist = false;
                long mobile = 0;
                firstName = txtFirstName.getText().toString().trim();
                lastName = txtLastName.getText().toString().trim();
                email = txtEmail.getText().toString().trim();
                if(!txtMobile.getText().toString().trim().isEmpty()) {
                    mobile = Long.parseLong(txtMobile.getText().toString().trim());
                }
                password = txtPassword.getText().toString().trim();
                confirm = txtConfirm.getText().toString().trim();
                final long temp = mobile;

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        List<User> users = repository.getAllUsers();
                        for (User u : users) {
                            if (u.getEmail_ID().equals(email)) {
                                alreadyExist = true;
                                break;
                            }
                        }
                        if(alreadyExist) {
                            RegisterActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, "Account already exists", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || temp==0 || password.isEmpty()) {
                                RegisterActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                RegisterActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "Please enter a valid email ID", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else if(temp < 1000000000) {
                                RegisterActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                if(password.equals(confirm)) {
                                    viewModel.insert_user(new User(email, firstName, lastName, temp, password, 0));
                                    RegisterActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    txtFirstName.getText().clear();
                                    txtLastName.getText().clear();
                                    txtEmail.getText().clear();
                                    txtMobile.getText().clear();
                                    txtPassword.getText().clear();
                                    txtConfirm.getText().clear();
                                    Intent intent = new Intent(RegisterActivity.this, AccountActivity.class);
                                    intent.putExtra("Username", email);
                                    intent.putExtra("Name", firstName);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    RegisterActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this, "Please check your password", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }
                    }
                });
            }
        });
    }
}