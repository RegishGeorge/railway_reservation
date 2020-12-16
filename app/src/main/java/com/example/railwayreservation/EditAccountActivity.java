package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.railwayreservation.AccountActivity.USERNAME;

public class EditAccountActivity extends AppCompatActivity {
    private Button btnChangePass, btnSave;
    private EditText editTxtFirstName, editTxtLastName, editTxtMobile, editTxtCurPass, editTxtNewPass, editTxtConfirmPass;
    private TextView txtEmail;
    private RailwayReservationViewModel viewModel;
    private String pass;
    private boolean changePassClicked = false;
    int balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        editTxtFirstName = findViewById(R.id.edit_txt_firstName);
        editTxtLastName = findViewById(R.id.edit_txt_lastName);
        txtEmail = findViewById(R.id.txt_email);
        editTxtMobile = findViewById(R.id.edit_txt_mobile);
        editTxtCurPass = findViewById(R.id.edit_txt_curPass);
        editTxtNewPass = findViewById(R.id.edit_text_newPass);
        editTxtConfirmPass = findViewById(R.id.edit_txt_confirmPass);
        btnChangePass = findViewById(R.id.btn_changePass);
        btnSave = findViewById(R.id.btn_save);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);
        viewModel.getLoginList(USERNAME).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                editTxtFirstName.setText(users.get(0).getFirst_name());
                editTxtLastName.setText(users.get(0).getLast_name());
                String email = "Email ID: " + USERNAME;
                txtEmail.setText(email);
                String mobile = users.get(0).getMobile_no() + "";
                editTxtMobile.setText(mobile);
                pass = users.get(0).getPassword();
                editTxtNewPass.setText(pass);
                balance = users.get(0).getBalance();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName, lastName, curPass, password, confirm;
                long mobile = 0;
                firstName = editTxtFirstName.getText().toString().trim();
                lastName = editTxtLastName.getText().toString().trim();
                if (!editTxtMobile.getText().toString().trim().isEmpty()) {
                    mobile = Long.parseLong(editTxtMobile.getText().toString().trim());
                }
                curPass = editTxtCurPass.getText().toString().trim();
                password = editTxtNewPass.getText().toString().trim();
                confirm = editTxtConfirmPass.getText().toString().trim();
                if (changePassClicked) {
                    if (firstName.isEmpty() || lastName.isEmpty() || mobile == 0 || curPass.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                        Toast.makeText(EditAccountActivity.this, "Please provide all the fields", Toast.LENGTH_SHORT).show();
                    } else if (mobile < 1000000000) {
                        EditAccountActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(EditAccountActivity.this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        if (curPass.equals(pass)) {
                            if (confirm.equals(password)) {
                                viewModel.update_user(new User(USERNAME, firstName, lastName, mobile, password, balance));
                                Toast.makeText(EditAccountActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(EditAccountActivity.this, "Please check your new password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EditAccountActivity.this, "Current password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if (firstName.isEmpty() || lastName.isEmpty() || mobile == 0) {
                        Toast.makeText(EditAccountActivity.this, "Please provide all the fields", Toast.LENGTH_SHORT).show();
                    } else if (mobile < 1000000000) {
                        EditAccountActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(EditAccountActivity.this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        viewModel.update_user(new User(USERNAME, firstName, lastName, mobile, password, balance));
                        Toast.makeText(EditAccountActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassClicked = true;
                editTxtNewPass.getText().clear();
                editTxtCurPass.setVisibility(View.VISIBLE);
                editTxtNewPass.setVisibility(View.VISIBLE);
                editTxtConfirmPass.setVisibility(View.VISIBLE);
                btnChangePass.setVisibility(View.GONE);
            }
        });
    }
}