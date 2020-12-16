package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.railwayreservation.AccountActivity.USERNAME;

public class AddBalanceActivity extends AppCompatActivity {
    private TextView txtBalance;
    private EditText txtCardNo, txtCVV, txtAmount;
    private RadioGroup radioGroup;
    private Button btnPay;
    private RailwayReservationViewModel viewModel;
    private int bal;
    private String firstName, lastName, password;
    private long mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_balance);

        txtBalance = findViewById(R.id.txt_balance);
        txtCardNo = findViewById(R.id.txt_cardNo);
        txtCVV = findViewById(R.id.txt_CVV);
        txtAmount = findViewById(R.id.txt_amount);
        radioGroup = findViewById(R.id.radioGroup);
        btnPay = findViewById(R.id.btn_pay);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);
        viewModel.getLoginList(USERNAME).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                bal = users.get(0).getBalance();
                firstName = users.get(0).getFirst_name();
                lastName = users.get(0).getLast_name();
                mobile = users.get(0).getMobile_no();
                password = users.get(0).getPassword();
                String balance = "Current Balance: ₹" + bal;
                txtBalance.setText(balance);
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNo, cvv, amount, type = "";
                cardNo = txtCardNo.getText().toString().trim();
                cvv = txtCVV.getText().toString().trim();
                amount = txtAmount.getText().toString().trim();
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.debit_card:
                        type = "Debit card";
                        break;
                    case R.id.credit_card:
                        type = "Credit card";
                        break;
                }
                if (cardNo.isEmpty() || cvv.isEmpty() || amount.isEmpty() || type.isEmpty()) {
                    Toast.makeText(AddBalanceActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else if (cardNo.length() < 16) {
                    Toast.makeText(AddBalanceActivity.this, "Please enter a valid card number", Toast.LENGTH_SHORT).show();
                } else if (cvv.length() < 3) {
                    Toast.makeText(AddBalanceActivity.this, "Please enter a valid CVV number", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(amount) == 0) {
                    Toast.makeText(AddBalanceActivity.this, "Minimum amount allowed is ₹1", Toast.LENGTH_SHORT).show();
                } else {
                    txtCardNo.getText().clear();
                    txtCVV.getText().clear();
                    txtAmount.getText().clear();
                    Toast.makeText(AddBalanceActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                    bal += Integer.parseInt(amount);
                    String balance = "Current Balance: ₹" + bal;
                    viewModel.update_user(new User(USERNAME, firstName, lastName, mobile, password, bal));
                    txtBalance.setText(balance);
                }
            }
        });
    }
}