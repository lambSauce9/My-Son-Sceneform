package com.google.ar.sceneform.samples.hellosceneform;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void testButton(View view){
        Intent intent = new Intent(this, HelloSceneformActivity.class);
        startActivity(intent);
    }
    public void testButton2(View view){
        Intent intent2 = new Intent(this, HelloSceneformActivity2.class);
        startActivity(intent2);
    }
    public void testButton3(View view){
        Intent intent3 = new Intent(this, HelloSceneformActivity3.class);
        startActivity(intent3);
    }
    public void testButton4(View view){
        Intent intent4 = new Intent(this, HelloSceneformActivity4.class);
        startActivity(intent4);
    }
    public void testButton5(View view){
        Intent intent5 = new Intent(this, HelloSceneformActivity5.class);
        startActivity(intent5);
    }
    public void testButton6(View view){
        Intent intent6 = new Intent(this, HelloSceneformActivity6.class);
        startActivity(intent6);
    }
    public void testButton7(View view){
        Intent intent7 = new Intent(this, HelloSceneformActivity7.class);
        startActivity(intent7);
    }
    public void testButton8(View view){
        Intent intent8 = new Intent(this, HelloSceneformActivity8.class);
        startActivity(intent8);
    }
    public void testButton9(View view){
        Intent intent8 = new Intent(this, HelloSceneformActivity9.class);
        startActivity(intent8);
    }
    public void testButton10(View view){
        Intent intent8 = new Intent(this, HelloSceneformActivity10.class);
        startActivity(intent8);
    }
}
