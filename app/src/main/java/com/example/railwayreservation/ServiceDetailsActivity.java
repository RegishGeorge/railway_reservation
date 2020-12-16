package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.railwayreservation.AccountActivity.USERNAME;
import static com.example.railwayreservation.BookTicketActivity.START;
import static com.example.railwayreservation.BookTicketActivity.STOP;

public class ServiceDetailsActivity extends AppCompatActivity {
    private TextView txtTrainName, txtPrice, txtBalance;
    private RadioGroup radioGroup;
    private RadioButton ac1, ac2, ac3, cc, sl;
    private NumberPicker numberPicker;
    private Button btnCheck, btnAddBalance, btnBook;
    private RailwayReservationViewModel viewModel;
    private RailwayReservationRepository repository;
    private int ac1No = 0, ac2No = 0, ac3No = 0, ccNo = 0, slNo = 0;
    private int nSeats = -1, nTickets, price;
    private Train tempTrain = null;
    private User tempUser = null;
    private List<Integer> tempInt = null;
    private List<TrainSeat> tempTrainSeat = null;
    private boolean isAC1, isAC2, isAC3, isCC, isSL;
    private int rid, tid, startNo, stopNo, flag = -1;
    private String className, departureTime, arrivalTime, trainName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        txtTrainName = findViewById(R.id.txt_train_name);
        txtPrice = findViewById(R.id.txt_price);
        txtBalance = findViewById(R.id.txt_balance);
        radioGroup = findViewById(R.id.radioGroup);
        ac1 = findViewById(R.id.ac1);
        ac2 = findViewById(R.id.ac2);
        ac3 = findViewById(R.id.ac3);
        cc = findViewById(R.id.cc);
        sl = findViewById(R.id.sl);
        numberPicker = findViewById(R.id.numberPicker);
        btnCheck = findViewById(R.id.btn_check);
        btnAddBalance = findViewById(R.id.btn_add_balance);
        btnBook = findViewById(R.id.btn_book);

        rid = getIntent().getIntExtra("route_id", 0);
        tid = getIntent().getIntExtra("train_id", 0);
        startNo = getIntent().getIntExtra("start_number", 0);
        stopNo = getIntent().getIntExtra("stop_number", 0);
        departureTime = getIntent().getStringExtra("departure_time");
        arrivalTime = getIntent().getStringExtra("arrival_time");

        repository = new RailwayReservationRepository(getApplication());
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);

        viewModel.getTrain(tid).observe(this, new Observer<List<Train>>() {
            @Override
            public void onChanged(List<Train> trains) {
                tempTrain = trains.get(0);
                trainName = trains.get(0).getTrain_name();
                txtTrainName.setText(trains.get(0).getTrain_name());
                ac1No = trains.get(0).getAc1_no();
                ac2No = trains.get(0).getAc2_no();
                ac3No = trains.get(0).getAc3_no();
                ccNo = trains.get(0).getCc_no();
                slNo = trains.get(0).getSleeper_no();
                getStations();
            }
        });

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        viewModel.getLoginList(USERNAME).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                tempUser = users.get(0);
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddBalance.setVisibility(View.GONE);
                txtBalance.setVisibility(View.GONE);
                isAC1 = false;
                isAC2 = false;
                isAC3 = false;
                isCC = false;
                isSL = false;
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.ac1:
                        isAC1 = true;
                        className = "1AC";
                        nSeats = ac1No;
                        break;
                    case R.id.ac2:
                        isAC2 = true;
                        className = "2AC";
                        nSeats = ac2No;
                        break;
                    case R.id.ac3:
                        isAC3 = true;
                        className = "3AC";
                        nSeats = ac3No;
                        break;
                    case R.id.cc:
                        isCC = true;
                        className = "CC";
                        nSeats = ccNo;
                        break;
                    case R.id.sl:
                        isSL = true;
                        className = "SL";
                        nSeats = slNo;
                        break;
                }
                nTickets = numberPicker.getValue();
                if (nSeats == -1) {
                    Toast.makeText(ServiceDetailsActivity.this, "Please select the class required", Toast.LENGTH_SHORT).show();
                } else {
                    if (nSeats >= nTickets) {
                        txtPrice.setVisibility(View.VISIBLE);
                        final boolean finalIsAC = isAC1;
                        final boolean finalIsAC1 = isAC2;
                        final boolean finalIsAC2 = isAC3;
                        final boolean finalIsCC = isCC;
                        final boolean finalIsSL = isSL;
                        viewModel.getPrice(rid, START, STOP).observe(ServiceDetailsActivity.this, new Observer<List<RouteDetails>>() {
                            @Override
                            public void onChanged(List<RouteDetails> routeDetails) {
                                if (finalIsAC) {
                                    price = routeDetails.get(1).getAc1_fare() - routeDetails.get(0).getAc1_fare();
                                } else if (finalIsAC1) {
                                    price = routeDetails.get(1).getAc2_fare() - routeDetails.get(0).getAc2_fare();
                                } else if (finalIsAC2) {
                                    price = routeDetails.get(1).getAc3_fare() - routeDetails.get(0).getAc3_fare();
                                } else if (finalIsCC) {
                                    price = routeDetails.get(1).getCc_fare() - routeDetails.get(0).getCc_fare();
                                } else if (finalIsSL) {
                                    price = routeDetails.get(1).getSleeper_fare() - routeDetails.get(0).getSleeper_fare();
                                }
                                price *= nTickets;
                                String priceText = "Price: ₹" + price;
                                txtPrice.setText(priceText);
                                btnBook.setVisibility(View.VISIBLE);
                            }
                        });
                    } else {
                        if (nSeats == 0) {
                            Toast.makeText(ServiceDetailsActivity.this, "Sorry, no more seats are available in this class", Toast.LENGTH_SHORT).show();
                            txtPrice.setVisibility(View.GONE);
                            btnBook.setVisibility(View.GONE);
                        } else if (nSeats == 1) {
                            Toast.makeText(ServiceDetailsActivity.this, "Only 1 seat left", Toast.LENGTH_SHORT).show();
                            txtPrice.setVisibility(View.GONE);
                            btnBook.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(ServiceDetailsActivity.this, "Only " + nSeats + " seats left", Toast.LENGTH_SHORT).show();
                            txtPrice.setVisibility(View.GONE);
                            btnBook.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });

        btnAddBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDetailsActivity.this, AddBalanceActivity.class);
                startActivity(intent);
                txtBalance.setVisibility(View.GONE);
                btnAddBalance.setVisibility(View.GONE);
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int balance = tempUser.getBalance();
                if (price > balance) {
                    Toast.makeText(ServiceDetailsActivity.this, "Unable to book due to low balance", Toast.LENGTH_SHORT).show();
                    String text = "Available Balance: ₹" + balance;
                    txtBalance.setText(text);
                    txtBalance.setVisibility(View.VISIBLE);
                    btnAddBalance.setVisibility(View.VISIBLE);
                } else {
                    balance -= price;
                    tempUser.setBalance(balance);
                    viewModel.update_user(tempUser);
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            for (Integer i : tempInt) {
                                List<TrainSeat> trainSeats = repository.getTrainSeat(rid, i, tid);
                                tempTrainSeat = trainSeats;
                                if (trainSeats.size() == 1) {
                                    flag = 0;
                                } else {
                                    flag = 1;
                                    tempTrainSeat.add(new TrainSeat(rid, i, tid, tempTrain.getAc1_no(), tempTrain.getAc2_no(), tempTrain.getAc3_no(), tempTrain.getCc_no(), tempTrain.getSleeper_no()));
                                }
                                int n = 0;
                                if (isAC1) {
                                    n = tempTrainSeat.get(0).getAc1_no();
                                    tempTrainSeat.get(0).setAc1_no(n - nTickets);
                                } else if (isAC2) {
                                    n = tempTrainSeat.get(0).getAc2_no();
                                    tempTrainSeat.get(0).setAc2_no(n - nTickets);
                                } else if (isAC3) {
                                    n = tempTrainSeat.get(0).getAc3_no();
                                    tempTrainSeat.get(0).setAc3_no(n - nTickets);
                                } else if (isCC) {
                                    n = tempTrainSeat.get(0).getCc_no();
                                    tempTrainSeat.get(0).setCc_no(n - nTickets);
                                } else if (isSL) {
                                    n = tempTrainSeat.get(0).getSleeper_no();
                                    tempTrainSeat.get(0).setSleeper_no(n - nTickets);
                                }
                                if (flag == 0) {
                                    viewModel.update_train_seat(tempTrainSeat.get(0));
                                } else if (flag == 1) {
                                    viewModel.insert_train_seat(tempTrainSeat.get(0));
                                }
                            }
                        }
                    });
                    long id = System.currentTimeMillis();
                    viewModel.insert_booking(new Booking(id, USERNAME, rid, tid, trainName, className, START, STOP, departureTime, arrivalTime, nTickets, "Booked"));
                    Toast.makeText(ServiceDetailsActivity.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                    displayDialogBox(id);
                }
            }
        });
    }

    void displayDialogBox(long id) {
        String ticketID = "Ticket ID: " + id;
        String station = START + " to " + STOP;
        String trainName = "Train Name: " + tempTrain.getTrain_name();
        String classType = "Class: " + className;
        String ticketsNo = "No. of tickets: " + nTickets;
        String time = "Departure time: " + departureTime;
        new AlertDialog.Builder(ServiceDetailsActivity.this)
                .setTitle("Booking Details")
                .setMessage(ticketID + "\n" + station + "\n" + trainName + "\n" + classType + "\n" + ticketsNo + "\n" + time)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    void getStations() {
        viewModel.getStationID(rid, startNo, stopNo).observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
                tempInt = integers;
                getTrainSeat();
            }
        });
    }

    void getTrainSeat() {
        tempTrainSeat = new ArrayList<>();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                for (Integer i : tempInt) {
                    List<TrainSeat> trainSeats = repository.getTrainSeat(rid, i, tid);
                    if (trainSeats.size() == 1) {
                        tempTrainSeat.add(trainSeats.get(0));
                    }
                }
                if (tempTrainSeat.size() > 0) {
                    ac1No = tempTrainSeat.get(0).getAc1_no();
                    ac2No = tempTrainSeat.get(0).getAc2_no();
                    ac3No = tempTrainSeat.get(0).getAc3_no();
                    ccNo = tempTrainSeat.get(0).getCc_no();
                    slNo = tempTrainSeat.get(0).getSleeper_no();
                    for (TrainSeat t : tempTrainSeat) {
                        if (t.getAc1_no() < ac1No) {
                            ac1No = t.getAc1_no();
                        }
                        if (t.getAc2_no() < ac2No) {
                            ac2No = t.getAc2_no();
                        }
                        if (t.getAc3_no() < ac3No) {
                            ac3No = t.getAc3_no();
                        }
                        if (t.getCc_no() < ccNo) {
                            ccNo = t.getCc_no();
                        }
                        if (t.getSleeper_no() < slNo) {
                            slNo = t.getSleeper_no();
                        }
                    }
                }
            }
        });
        if (ac1No == 0) {
            ac1.setVisibility(View.GONE);
        }
        if (ac2No == 0) {
            ac2.setVisibility(View.GONE);
        }
        if (ac3No == 0) {
            ac3.setVisibility(View.GONE);
        }
        if (ccNo == 0) {
            cc.setVisibility(View.GONE);
        }
        if (slNo == 0) {
            sl.setVisibility(View.GONE);
        }
    }
}