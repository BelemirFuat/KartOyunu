package com.belemirfuat.ders2v2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.Manifest;


public class MainActivity extends AppCompatActivity {

    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    public int zorlukSeviyesi;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        zorlukSeviyesi = 0;
        Button oynaBtn = findViewById(R.id.oyunaBaslaBtn);
        EditText isimEdt = findViewById(R.id.isimEdtTxt);
        oynaBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(zorlukSeviyesi==0)
                {
                    Toast.makeText(getApplicationContext(),"Zorluk Seviyesi Seçiniz",Toast.LENGTH_SHORT).show();
                    return;
                }
                String oyuncuIsim = isimEdt.getText().toString();
                Intent actInt = new Intent(MainActivity.this,oyunActivity.class);

                actInt.putExtra("oyuncuIsim",oyuncuIsim);
                actInt.putExtra("zorlukSeviyesi",zorlukSeviyesi);

                startActivity(actInt);
            }
        });
        Button kolayBtn = findViewById(R.id.kolayBtn);
        kolayBtn.setBackgroundColor(Color.BLUE);
        Button ortaBtn = findViewById(R.id.ortaBtn);
        ortaBtn.setBackgroundColor(Color.BLUE);

        Button zorBtn = findViewById(R.id.zorBtn);
        zorBtn.setBackgroundColor(Color.BLUE);


        kolayBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              zorlukSeviyesi = 1;
              v.setBackgroundColor(Color.GRAY);
              ortaBtn.setBackgroundColor(Color.BLUE);
              zorBtn.setBackgroundColor(Color.BLUE);
            }

        });
        zorBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              zorlukSeviyesi = 3;
              v.setBackgroundColor(Color.GRAY);
              kolayBtn.setBackgroundColor(Color.BLUE);
              ortaBtn.setBackgroundColor(Color.BLUE);

            }

        });
        ortaBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              zorlukSeviyesi = 2;
              v.setBackgroundColor(Color.GRAY);
              kolayBtn.setBackgroundColor(Color.BLUE);
              zorBtn.setBackgroundColor(Color.BLUE);

            }

        });


    }
}