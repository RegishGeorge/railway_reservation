package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTTEActivity extends AppCompatActivity {
    private EditText txtName, txtUsername, txtPassword;
    private Button btnAdd;
    private RailwayReservationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_t_t_e);

        txtName = findViewById(R.id.edit_text_name);
        txtUsername = findViewById(R.id.edit_text_username);
        txtPassword = findViewById(R.id.edit_text_password);
        btnAdd = findViewById(R.id.btn_add);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString().trim();
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                if(name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(AddTTEActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    txtName.getText().clear();
                    txtUsername.getText().clear();
                    txtPassword.getText().clear();
                    viewModel.insert_tte(new TTE(username, password, name));
                    Toast.makeText(AddTTEActivity.this, "Added TTE Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}