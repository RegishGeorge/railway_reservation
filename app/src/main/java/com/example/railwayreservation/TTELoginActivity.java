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

public class TTELoginActivity extends AppCompatActivity {
    private EditText editTxtUsername, editTxtPass;
    private Button btnLogin;
    private RailwayReservationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_t_e_login);

        editTxtUsername = findViewById(R.id.edit_txt_username);
        editTxtPass = findViewById(R.id.edit_text_password);
        btnLogin = findViewById(R.id.btn_login);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTxtUsername.getText().toString().trim();
                final String password = editTxtPass.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(TTELoginActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.getTTEList(username).observe(TTELoginActivity.this, new Observer<List<TTE>>() {
                        @Override
                        public void onChanged(List<TTE> ttes) {
                            if(ttes.size() == 0) {
                                Toast.makeText(TTELoginActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();
                            } else {
                                if(password.equals(ttes.get(0).getPassword())) {
                                    editTxtUsername.getText().clear();
                                    editTxtPass.getText().clear();
                                    Intent intent = new Intent(TTELoginActivity.this, TTEAccountActivity.class);
                                    intent.putExtra("Name", ttes.get(0).getName());
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(TTELoginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }

            }
        });
    }
}