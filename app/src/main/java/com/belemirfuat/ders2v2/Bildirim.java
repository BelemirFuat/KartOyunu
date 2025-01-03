package com.belemirfuat.ders2v2;

import androidx.annotation.NonNull;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;



public class Bildirim extends FirebaseMessagingService {


    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("FBToken", token);
    }

}
