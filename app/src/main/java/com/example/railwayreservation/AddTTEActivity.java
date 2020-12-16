package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddTTEActivity extends AppCompatActivity {
    private EditText txtName, txtUsername, txtPassword;
    private Button btnAdd;
    private RailwayReservationViewModel viewModel;
    private RailwayReservationRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_t_t_e);

        txtName = findViewById(R.id.edit_text_name);
        txtUsername = findViewById(R.id.edit_text_username);
        txtPassword = findViewById(R.id.edit_text_password);
        btnAdd = findViewById(R.id.btn_add);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);
        repository = new RailwayReservationRepository(getApplication());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = txtName.getText().toString().trim();
                final String username = txtUsername.getText().toString().trim();
                final String password = txtPassword.getText().toString().trim();
                if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(AddTTEActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            List<TTE> ttes = repository.getTTEList(username);
                            if (ttes.size() > 0) {
                                AddTTEActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AddTTEActivity.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                txtName.getText().clear();
                                txtUsername.getText().clear();
                                txtPassword.getText().clear();
                                viewModel.insert_tte(new TTE(username, password, name));
                                AddTTEActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AddTTEActivity.this, "Added TTE Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }
}