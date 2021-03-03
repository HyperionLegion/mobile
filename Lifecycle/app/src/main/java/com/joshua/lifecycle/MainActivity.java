package com.joshua.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
int runCreate = 0;
int runStart = 0;
int runResume = 0;
int runPause = 0;
int runStop = 0;
int runRestart = 0;
int runDestroy = 0;
TextView create, start, resume, pause, stop, restart, destroy, currentCreate, currentStart, currentResume, currentPause, currentStop, currentRestart, currentDestroy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("com.joshua.lifecycle", Context.MODE_PRIVATE);;
        editor = sharedPreferences.edit();
        runCreate++;
        editor.putInt("onCreate", 1+sharedPreferences.getInt("onCreate", 0));
        editor.apply();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create = findViewById(R.id.create);
        start = findViewById(R.id.start);
        resume = findViewById(R.id.resume);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        restart = findViewById(R.id.restart);
        destroy = findViewById(R.id.destroy);
        currentCreate = findViewById(R.id.currentCreate);
        currentStart = findViewById(R.id.currentStart);
        currentResume = findViewById(R.id.currentResume);
        currentPause = findViewById(R.id.currentPause);
        currentStop = findViewById(R.id.currentStop);
        currentRestart = findViewById(R.id.currentRestart);
        currentDestroy  = findViewById(R.id.currentDestroy);
        init();
    }
    public void init(){

create.setText("onCreate: " +  sharedPreferences.getInt("onCreate", 0));
start.setText("onStart: " + sharedPreferences.getInt("onStart", 0));
resume.setText("onResume: " +  sharedPreferences.getInt("onResume", 0));
pause.setText("onPause: " + sharedPreferences.getInt("onPause", 0));
stop.setText("onStop: " + sharedPreferences.getInt("onStop", 0));
restart.setText("onRestart: " +  sharedPreferences.getInt("onRestart", 0));
destroy.setText("onDestroy: " +  sharedPreferences.getInt("onDestroy", 0));
currentCreate.setText("onCreate: " + runCreate);
currentStart.setText("onStart: " + runStart);
currentResume.setText("onResume: " + runResume);
currentPause.setText("onPause: " + runPause);
        currentStop.setText("onStop: " + runStop);
        currentRestart.setText("onRestart : " + runRestart);
        currentDestroy.setText("onDestroy: " + runDestroy);
    }


    @Override
    protected void onStart() {
        runStart++;
        editor.putInt("onStart", 1+sharedPreferences.getInt("onStart", 0));
        editor.apply();
        init();
        super.onStart();
    }

    @Override
    protected void onResume() {
        runResume++;
        editor.putInt("onResume", 1+sharedPreferences.getInt("onResume", 0));
        editor.apply();
        init();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        runDestroy++;
        editor.putInt("onDestroy", 1+sharedPreferences.getInt("onDestroy", 0));
        editor.apply();
        init();
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        runRestart++;
        editor.putInt("onRestart", 1+sharedPreferences.getInt("onRestart", 0));
        editor.apply();
        init();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        runStop++;
        editor.putInt("onStop", 1+sharedPreferences.getInt("onStop", 0));
        editor.apply();
        init();
        super.onStop();
    }

    @Override
    protected void onPause() {
        runPause++;
        editor.putInt("onPause", 1+sharedPreferences.getInt("onPause", 0));
        editor.apply();
        init();
        super.onPause();
    }
}