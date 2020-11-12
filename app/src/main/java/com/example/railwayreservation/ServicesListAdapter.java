package com.example.railwayreservation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ServicesListAdapter extends RecyclerView.Adapter<ServicesListAdapter.ServicesListHolder> {
    private List<ServicesList> services = new ArrayList<>();
    private Context mContext;

    public ServicesListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ServicesListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_item, parent, false);
        return new ServicesListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesListHolder holder, int position) {
        final ServicesList currentService = services.get(position);
        String departure_hr, departure_min, arrival_hr, arrival_min;
        if(currentService.getDeparture_hr() < 10) {
            departure_hr = "0" + currentService.getDeparture_hr();
        } else {
            departure_hr = Integer.toString(currentService.getDeparture_hr());
        }
        if(currentService.getDeparture_min() < 10) {
            departure_min = "0" + currentService.getDeparture_min();
        } else {
            departure_min = Integer.toString(currentService.getDeparture_min());
        }
        if(currentService.getArrival_hr() < 10) {
            arrival_hr = "0" + currentService.getArrival_hr();
        } else {
            arrival_hr = Integer.toString(currentService.getArrival_hr());
        }
        if(currentService.getArrival_min() < 10) {
            arrival_min = "0" + currentService.getArrival_min();
        } else {
            arrival_min = Integer.toString(currentService.getArrival_min());
        }
        final String departure = departure_hr + ":" + departure_min;
        final String arrival = arrival_hr + ":" + arrival_min;
        String time = departure + " - " + arrival;
        String classes = "";
        if(currentService.getAc1_no()>0) {
            classes += "1AC ";
        }
        if(currentService.getAc2_no()>0) {
            classes += "2AC ";
        }
        if(currentService.getAc3_no()>0) {
            classes += "3AC ";
        }
        if(currentService.getCc_no()>0) {
            classes += "CC ";
        }
        if(currentService.getSleeper_no()>0) {
            classes += "SL ";
        }
        holder.txtServiceName.setText(currentService.getTrain_name());
        holder.txtTime.setText(time);
        holder.txtClass.setText(classes);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ServiceDetailsActivity.class);
                intent.putExtra("route_id", currentService.getRoute_id());
                intent.putExtra("train_id", currentService.getTrain_id());
                intent.putExtra("start_number", currentService.getStart());
                intent.putExtra("stop_number", currentService.getStop());
                intent.putExtra("departure_time", departure);
                intent.putExtra("arrival_time", arrival);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public void setRoutes(List<ServicesList> services) {
        this.services = services;
        notifyDataSetChanged();
    }

    static class ServicesListHolder extends RecyclerView.ViewHolder {
        private TextView txtServiceName, txtTime, txtClass;
        private CardView parent;

        public ServicesListHolder(@NonNull View itemView) {
            super(itemView);
            txtServiceName = itemView.findViewById(R.id.txtServiceName);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtClass = itemView.findViewById(R.id.txt_class);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
