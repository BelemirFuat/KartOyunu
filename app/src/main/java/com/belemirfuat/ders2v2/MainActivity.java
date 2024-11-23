package com.belemirfuat.ders2v2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    public int zorlukSeviyesi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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