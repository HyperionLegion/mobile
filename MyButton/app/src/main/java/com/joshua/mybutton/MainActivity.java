package com.joshua.mybutton;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button clickButton;
    Button secondButton;
    int count = 0;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.greeting);
        clickButton = findViewById(R.id.clicker);
        secondButton = findViewById(R.id.decrement);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Hi Mr Tra!");
                Log.i("clickResponse", "i love Android Studio");
                textView.setText(""+(++count));
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                clickButton.setBackgroundColor(color);
            }
        });
    }

    public void reduce(View view) {
        textView.setText(""+(--count));
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        secondButton.setBackgroundColor(color);
    }
}