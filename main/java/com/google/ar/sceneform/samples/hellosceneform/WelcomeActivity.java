package com.google.ar.sceneform.samples.hellosceneform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;

public class WelcomeActivity extends AppIntro {

    SharedPreferences sp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(SampleSlide.newInstance(R.layout.welcome_slide_0));
        addSlide(SampleSlide.newInstance(R.layout.welcome_slide_1));
        addSlide(SampleSlide.newInstance(R.layout.welcome_slide_2));

        setFadeAnimation();

        /* In your onCreate method */
        sp = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        if (sp.getBoolean("first", false)) {
            Intent intent = new Intent(this, MainActivity.class); // Call the AppIntro java class
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("first", true);
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("first", true);
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
