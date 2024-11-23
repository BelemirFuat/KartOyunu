package com.belemirfuat.ders2v2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class Kart extends AppCompatButton {

    public static enum KartDurumu
    {
        ACIK,
        KAPALI
    }
    public KartDurumu Mevcutdurum;

    private Drawable arkaPlan;
    private Drawable onPlan;
    public int resId;

    public Kart(Context CNT, int kartID , int resimID) {

        super(CNT);

        arkaPlan = CNT.getDrawable(R.drawable.background64x64);
        setBackground(arkaPlan);
        Mevcutdurum = KartDurumu.KAPALI;
        setId(kartID);
        resId = resimID;
        if(resimID == 0)
        {
            onPlan = CNT.getDrawable(R.drawable.card164x64);
        }
        else if(resimID == 2)
        {
            onPlan = CNT.getDrawable(R.drawable.card264x64);
        }
        else if(resimID == 4)
        {
            onPlan = CNT.getDrawable(R.drawable.card364x64);
        }
        else if(resimID == 6)
        {
            onPlan = CNT.getDrawable(R.drawable.card464x64);
        }
        else if(resimID == 8)
        {
            onPlan = CNT.getDrawable(R.drawable.card564x64);
        }
        else if(resimID == 10)
        {
            onPlan = CNT.getDrawable(R.drawable.card664x64);
        }
        else if(resimID == 12)
        {
            onPlan = CNT.getDrawable(R.drawable.card764x64);
        }
        else if(resimID == 14)
        {
            onPlan = CNT.getDrawable(R.drawable.card864x64);
        }
        else if(resimID == 16)
        {
            onPlan = CNT.getDrawable(R.drawable.card964x64);
        }
    }
    public void dondur()
    {
     if(Mevcutdurum == KartDurumu.KAPALI)
        {
            setBackground(onPlan);
            Mevcutdurum = KartDurumu.ACIK;
        }
        else
        {
            setBackground(arkaPlan);
            Mevcutdurum = KartDurumu.KAPALI;
        }
    }
}
