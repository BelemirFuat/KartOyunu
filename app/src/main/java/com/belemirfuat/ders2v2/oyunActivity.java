package com.belemirfuat.ders2v2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
    int hataHakki;
    boolean bekle;

    public int kalanSure=0;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("kalanSure", kalanSure);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        if(savedInstanceState != null)
        {
            kalanSure = savedInstanceState.getInt("kalanSure");
            Log.d("KalanSure", String.valueOf(kalanSure));

        }
        setContentView(R.layout.activity_oyun);
        Intent actInt = getIntent();
        oyuncuIsim = actInt.getStringExtra("oyuncuIsim");
        zorlukSeviyesi = actInt.getIntExtra("zorlukSeviyesi", 1);
        bilgiTxt = findViewById(R.id.bilgiTxt);
        bilgiTxt.setText(oyuncuIsim + " Hoş Geldiniz.");
        grd = findViewById(R.id.grdLyt);
        int satirSayisi=2;
        int sutunSayisi=2;
        hataHakki = 10;
        if(zorlukSeviyesi == 1)
        {
            satirSayisi = 2;
            sutunSayisi = 3;
            sureBaslat(10);

        }else if(zorlukSeviyesi == 2)
        {
            satirSayisi = 3;
            sutunSayisi = 4;
            hataHakki = 15;
            sureBaslat(15);

        }else
        {
            sutunSayisi = 3;
            satirSayisi = 6;
            hataHakki = 25;
            sureBaslat(20);

        }
        grd.setColumnCount(sutunSayisi);
        grd.setRowCount(satirSayisi);
        int toplamSayi = satirSayisi*sutunSayisi;
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
                    if(bekle)
                    {
                        return;
                    }
                    bilgiTxt.setText("Hata Hakki : " + hataHakki);
                    if(hataHakki < 0)
                    {
                        bilgiTxt.setText("Kaybettiniz");
                        return;
                    }
                    if(suankiKart==0)
                    {
                        kart.dondur();
                        suankiKart = kart.getId();
                    } else
                    {

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
                            hataHakki--;

                            Handler h1 = new Handler();
                            h1.postDelayed(new Runnable(){
                               @Override
                               public void run() {
                                   kart.dondur();
                                   oncekiKart.dondur();
                                   suankiKart = 0;
                                   bekle = false;
                               }
                            }, 700);
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
    public void sureBaslat(int zaman)
    {
        ProgressBar sureBar = findViewById(R.id.sureBr);
        sureBar.setMax(zaman);
        sureBar.setProgress(kalanSure, true);
        if(kalanSure > 0)
        {
            zaman = kalanSure;
        }
        CountDownTimer timer = new CountDownTimer(zaman*1000, 1000) {
        @Override
            public void onTick(long millisUntilFinished)
        {
            int sure = (int)millisUntilFinished/1000;
            sureBar.setProgress(sure, true);
            kalanSure = sure;
        }

        @Override
        public void onFinish()
        {
            Toast.makeText(getApplicationContext(), "Süre Doldu", Toast.LENGTH_SHORT).show();
            bilgiTxt.setText("Süre Doldu Kaybettiniz");
            bekle = true;
        }
        };
        timer.start();
    }
}