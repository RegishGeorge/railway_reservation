package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ModifyTrainActivity extends AppCompatActivity {
    private EditText txtTrainID, txt1AC, txt2AC, txt3AC, txtCC, txtSL;
    private TextView txtTrainName;
    private Button btnCheck, btnUpdate;
    private ConstraintLayout layout;
    private RailwayReservationViewModel viewModel;
    private RailwayReservationRepository repository;
    private String tid, trainName;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_train);

        txtTrainID = findViewById(R.id.txt_train_id);
        txt1AC = findViewById(R.id.edit_txt_ac1);
        txt2AC = findViewById(R.id.edit_txt_ac2);
        txt3AC = findViewById(R.id.edit_txt_ac3);
        txtCC = findViewById(R.id.edit_txt_cc);
        txtSL = findViewById(R.id.edit_txt_sleeper);
        txtTrainName = findViewById(R.id.txt_train_name);
        btnCheck = findViewById(R.id.btn_check);
        btnUpdate = findViewById(R.id.btn_update);
        layout = findViewById(R.id.layout);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RailwayReservationViewModel.class);
        repository = new RailwayReservationRepository(getApplication());

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tid = txtTrainID.getText().toString().trim();
                if (tid.isEmpty()) {
                    layout.setVisibility(View.GONE);
                    Toast.makeText(ModifyTrainActivity.this, "Please enter the train ID", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    id = Integer.parseInt(tid);
                    txtTrainID.getText().clear();
                }
                viewModel.getTrain(id).observe(ModifyTrainActivity.this, new Observer<List<Train>>() {
                    @Override
                    public void onChanged(List<Train> trains) {
                        if (trains.size() == 0) {
                            layout.setVisibility(View.GONE);
                            Toast.makeText(ModifyTrainActivity.this, "Invalid train ID", Toast.LENGTH_SHORT).show();
                        } else {
                            trainName = trains.get(0).getTrain_name();
                            txtTrainName.setText("Train Name: " + trainName);
                            txt1AC.setText(String.valueOf(trains.get(0).getAc1_no()));
                            txt2AC.setText(String.valueOf(trains.get(0).getAc2_no()));
                            txt3AC.setText(String.valueOf(trains.get(0).getAc3_no()));
                            txtCC.setText(String.valueOf(trains.get(0).getCc_no()));
                            txtSL.setText(String.valueOf(trains.get(0).getSleeper_no()));
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    List<TrainSeat> trainSeats = repository.getTrainList(id);
                                    if (trainSeats.size() > 0) {
                                        ModifyTrainActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                btnUpdate.setEnabled(false);
                                                Toast.makeText(ModifyTrainActivity.this, "Cannot perform any updates as the train has not completed its service!", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    } else {
                                        ModifyTrainActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                btnUpdate.setEnabled(true);
                                            }
                                        });
                                    }
                                }
                            });
                            layout.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nac1, nac2, nac3, ncc, nsl;
                final int ac1, ac2, ac3, cc, sl;
                nac1 = txt1AC.getText().toString().trim();
                nac2 = txt2AC.getText().toString().trim();
                nac3 = txt3AC.getText().toString().trim();
                ncc = txtCC.getText().toString().trim();
                nsl = txtSL.getText().toString().trim();
                if (nac1.isEmpty() || nac2.isEmpty() || nac3.isEmpty() || ncc.isEmpty() || nsl.isEmpty()) {
                    Toast.makeText(ModifyTrainActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    ac1 = Integer.parseInt(nac1);
                    ac2 = Integer.parseInt(nac2);
                    ac3 = Integer.parseInt(nac3);
                    cc = Integer.parseInt(ncc);
                    sl = Integer.parseInt(nsl);
                }
                viewModel.update_train(new Train(id, trainName, ac1, ac2, ac3, cc, sl));
                Toast.makeText(ModifyTrainActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}