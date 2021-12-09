package com.example.maddi.fitness;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class AppIntroActivity extends AppIntro {

    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(AppIntroFragment.newInstance("Eye Catching Visuals", "Your Daily Statistics", R.drawable.appintro1, getResources().getColor(R.color.appintro1)));
        addSlide(AppIntroFragment.newInstance("", "Get Started", R.drawable.appintro4, getResources().getColor(R.color.appintro4)));

        setBarColor(Color.parseColor("#F44336"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        showSkipButton(true);
        setProgressButtonEnabled(true);

        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed() {
        Intent i = new Intent(AppIntroActivity.this, EnterInfoActivity.class);
        startActivity(i);
    }

    @Override
    public void onDonePressed() {
        Intent i = new Intent(AppIntroActivity.this, EnterInfoActivity.class);
        startActivity(i);

        Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSlideChanged() {
    }

    @Override
    public void onNextPressed() {
        Toast.makeText(getApplicationContext(), "Cannot Skip", Toast.LENGTH_SHORT).show();
    }

}