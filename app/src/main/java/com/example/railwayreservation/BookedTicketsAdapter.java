package com.example.railwayreservation;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookedTicketsAdapter extends RecyclerView.Adapter<BookedTicketsAdapter.BookedTicketsHolder> {
    private List<Booking> bookings = new ArrayList<>();

    @NonNull
    @Override
    public BookedTicketsAdapter.BookedTicketsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_item, parent, false);
        return new BookedTicketsAdapter.BookedTicketsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedTicketsAdapter.BookedTicketsHolder holder, int position) {
        Booking currentBooking = bookings.get(position);
        String route = currentBooking.getDeparture() + " - " + currentBooking.getArrival();
        holder.ticketID.setText("Ticket ID: " + String.valueOf(currentBooking.getTicket_id()));
        holder.trainName.setText("Train Name: " + currentBooking.getTrainName());
        holder.fromTo.setText(route);
        holder.startTime.setText("Departure Time: " + currentBooking.getStart_time());
        holder.stopTime.setText("Arrival Time: " + currentBooking.getStop_time());
        holder.className.setText("Class Name: " + currentBooking.getClass_name());
        holder.nTickets.setText("No. of tickets: " + currentBooking.getTickets_no());
        String status = currentBooking.getStatus();
        holder.status.setText(status);
        if(status.equals("Booked")) {
            holder.status.setTextColor(Color.parseColor("#fc8803"));
        } else if(status.equals("Boarded")) {
            holder.status.setTextColor(Color.parseColor("#03fc07"));
        } else {
            holder.status.setTextColor(Color.parseColor("#ff0000"));
        }
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public void setTickets(List<Booking> bookings) {
        this.bookings = bookings;
        notifyDataSetChanged();
    }

    static class BookedTicketsHolder extends RecyclerView.ViewHolder {
        private TextView ticketID, trainName, fromTo, startTime, stopTime, className, nTickets, status;

        public BookedTicketsHolder(@NonNull View itemView) {
            super(itemView);
            ticketID = itemView.findViewById(R.id.ticket_id);
            trainName = itemView.findViewById(R.id.train_name);
            fromTo = itemView.findViewById(R.id.from_to);
            startTime = itemView.findViewById(R.id.start_time);
            stopTime = itemView.findViewById(R.id.stop_time);
            className = itemView.findViewById(R.id.class_name);
            nTickets = itemView.findViewById(R.id.tickets_number);
            status = itemView.findViewById(R.id.status);
        }
    }
}
