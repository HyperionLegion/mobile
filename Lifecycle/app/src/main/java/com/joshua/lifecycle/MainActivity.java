package com.joshua.lifecycle;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;

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
Button lifetimeReset, currentReset;
Gson gson;
TextView create, start, resume, pause, stop, restart, destroy, currentCreate, currentStart, currentResume, currentPause, currentStop, currentRestart, currentDestroy;
    SampleData lifetime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gson = new GsonBuilder().create();
        sharedPreferences = getSharedPreferences("com.joshua.lifecycle", Context.MODE_PRIVATE);;
        editor = sharedPreferences.edit();
        lifetime = gson.fromJson(sharedPreferences.getString("data", "{ create:0, start:0, resume:0, pause:0, stop:0, restart:0, destroy:0 }"), SampleData.class);
        runCreate++;
        editor.putInt("onCreate", 1+sharedPreferences.getInt("onCreate", 0));
        lifetime.create = lifetime.create+1;
        editor.putString("data", gson.toJson(lifetime,SampleData.class));
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
        lifetimeReset = findViewById(R.id.lifetimeReset);
        currentReset = findViewById(R.id.currentReset);
        lifetimeReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("onCreate", 0);
                editor.putInt("onStart", 0);
                editor.putInt("onResume", 0);
                editor.putInt("onPause", 0);
                editor.putInt("onStop", 0);
                editor.putInt("onRestart", 0);
                editor.putInt("onDestroy", 0);
                lifetime.create = 0;
                lifetime.start =0;
                lifetime.resume = 0;
                lifetime.pause=0;
                lifetime.stop=0;
                lifetime.restart=0;
                lifetime.destroy=0;
                editor.putString("data", gson.toJson(lifetime,SampleData.class));
                editor.apply();
                init();
            }
        });
        currentReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runCreate = 0;
                runStart = 0;
                runResume = 0;
                runPause = 0;
                runStop = 0;
                runRestart = 0;
                runDestroy = 0;
                init();
            }
        });

        init();
    }
    public void init(){
        lifetime = gson.fromJson(sharedPreferences.getString("data", "{ create:0, start:0, resume:0, pause:0, stop:0, restart:0, destroy:0 }"), SampleData.class);
create.setText("onCreate: " +  lifetime.create);
start.setText("onStart: " + lifetime.start);
resume.setText("onResume: " +  lifetime.resume);
pause.setText("onPause: " + lifetime.pause);
stop.setText("onStop: " + lifetime.stop);
restart.setText("onRestart: " +  lifetime.restart);
destroy.setText("onDestroy: " +  lifetime.destroy);
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
        lifetime.start = lifetime.start + 1;
        editor.putString("data", gson.toJson(lifetime,SampleData.class));
        editor.apply();
        init();
        super.onStart();
    }

    @Override
    protected void onResume() {
        runResume++;
        lifetime.resume = lifetime.resume+1;
        editor.putInt("onResume", 1+sharedPreferences.getInt("onResume", 0));
        editor.putString("data", gson.toJson(lifetime,SampleData.class));
        editor.apply();
        init();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        runDestroy++;
        lifetime.destroy = lifetime.destroy+1;
        editor.putInt("onDestroy", 1+sharedPreferences.getInt("onDestroy", 0));
        editor.putString("data", gson.toJson(lifetime,SampleData.class));
        editor.apply();
        init();
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        runRestart++;
        lifetime.restart = lifetime.restart +1;
        editor.putInt("onRestart", 1+sharedPreferences.getInt("onRestart", 0));
        editor.putString("data", gson.toJson(lifetime,SampleData.class));
        editor.apply();
        init();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        runStop++;
        lifetime.stop = lifetime.stop + 1;
        editor.putString("data", gson.toJson(lifetime,SampleData.class));
        editor.putInt("onStop", 1+sharedPreferences.getInt("onStop", 0));
        editor.apply();
        init();
        super.onStop();
    }

    @Override
    protected void onPause() {
        runPause++;
        lifetime.pause = lifetime.pause + 1;
        editor.putString("data", gson.toJson(lifetime,SampleData.class));
        editor.putInt("onPause", 1+sharedPreferences.getInt("onPause", 0));
        editor.apply();
        init();
        super.onPause();
    }
    public class SampleData { //should probably make getters and setters instead next time
       public int create = 0;
       public int start = 0;
       public int resume = 0;
       public int pause = 0;
       public int stop = 0;
       public int restart = 0;
       public int destroy = 0;
        public SampleData(int runCreate, int runStart, int runResume, int runPause, int runStop, int runRestart, int runDestroy){
            create = runCreate;
            start = runStart;
            resume = runResume;
            pause = runPause;
            stop = runStop;
            restart = runRestart;
            destroy = runDestroy;
        }

    }
}