package com.belemirfuat.ders2v2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class oyunActivity extends AppCompatActivity {

    String oyuncuIsim;
    TextView bilgiTxt;
    GridLayout grd;

    Kart[] kartlar;

    int suankiKart;

    Kart oncekiKart;

    int zorlukSeviyesi;

    boolean bekle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_oyun);
        Intent actInt = getIntent();
        oyuncuIsim = actInt.getStringExtra("oyuncuIsim");
        zorlukSeviyesi = actInt.getIntExtra("zorlukSeviyesi", 0);
        bilgiTxt = findViewById(R.id.bilgiTxt);
        bilgiTxt.setText(oyuncuIsim + " Hoş Geldiniz.");
        grd = findViewById(R.id.grdLyt);
        grd.setColumnCount(4);
        grd.setRowCount(2);
        int toplamSayi = 8;
        kartlar = new Kart[toplamSayi];
        suankiKart = 0;

        for(int i = 0; i<toplamSayi; i++)
        {
            Kart kart;
            if(i%2 == 0)
                kart = new Kart(this, i+100, i);
            else
                kart = new Kart(this, i+100, i-1);
            kart.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(suankiKart==0)
                    {
                        kart.dondur();
                        suankiKart = kart.getId();
                    } else
                    {
                        if(bekle)
                        {
                            return;
                        }
                        bekle = true;
                        oncekiKart = findViewById(suankiKart);
                        if (oncekiKart.Mevcutdurum == Kart.KartDurumu.ACIK &&
                            kart.Mevcutdurum == Kart.KartDurumu.ACIK)
                        {
                            return;
                        }
                        if (kart.Mevcutdurum != Kart.KartDurumu.ACIK)
                        {
                            kart.dondur();

                        }
                        if(oncekiKart.resId == kart.resId)
                        {
                            Toast.makeText(getApplicationContext(), "eşleşme bulundu", Toast.LENGTH_SHORT).show();
                            bekle = false;
                            suankiKart = 0;
                        }
                        else
                        {
                            Handler h1 = new Handler();
                            h1.postDelayed(new Runnable(){
                               @Override
                               public void run() {
                                   kart.dondur();
                                   oncekiKart.dondur();
                                   suankiKart = 0;
                                   bekle = false;
                               }
                            }, 2000);
                        }

                        //suankiKart = 0;

                    }

                }

            });
            kartlar[i] = kart;

        }

        kartlariKaristir();

    }
    public void kartlariKaristir()
    {
        List<Kart> kartDizisi = new ArrayList<Kart>(Arrays.asList(kartlar));
        Collections.shuffle(kartDizisi);
        for(View v : kartDizisi)
            grd.addView(v);

    }
}