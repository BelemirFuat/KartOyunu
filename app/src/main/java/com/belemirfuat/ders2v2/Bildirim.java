package com.belemirfuat.ders2v2;

import androidx.annotation.NonNull;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;


public class Bildirim extends FirebaseMessagingService {


    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("FBMesaj ", token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        Log.d("FBMesaj", message.getNotification().getTitle());
        Log.d("FBMesaj", message.getNotification().getBody());
        String icerik = message.getNotification().getBody();
        String baslik = message.getNotification().getTitle();

        NotificationChannel kanal = new NotificationChannel("bildiri", "bildiri", NotificationManager.IMPORTANCE_HIGH);
        kanal.enableVibration(true);
        kanal.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        Notification bildiri = new Notification.Builder(getApplicationContext())
                .setChannelId("bildiri")
                .setSmallIcon(R.drawable.card1064x64)
                .setContentTitle(baslik)
                .setContentText(icerik)
                .build();
        NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nm.createNotificationChannel(kanal);
        nm.notify(new Random().nextInt(), bildiri);
    }

}
