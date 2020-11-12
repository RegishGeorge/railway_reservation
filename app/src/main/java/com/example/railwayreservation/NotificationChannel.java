package com.example.railwayreservation;

import android.app.Application;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationChannel extends Application {
    public static final String BOOKING_CHANNEL_ID = "BookingDetailsChannel";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            android.app.NotificationChannel bookingDetailsChannel = new android.app.NotificationChannel(
                    BOOKING_CHANNEL_ID,
                    "Booking Details Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(bookingDetailsChannel);
        }
    }
}
