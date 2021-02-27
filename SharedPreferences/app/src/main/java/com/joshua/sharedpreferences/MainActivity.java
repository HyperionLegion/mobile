package com.joshua.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button buttonTopLeft, buttonTopRight, buttonBottomLeft, buttonBottomRight;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SeekBar sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("com.joshua.sharedpreferences", Context.MODE_PRIVATE);;
        editor = sharedPreferences.edit();
        sb = findViewById(R.id.seekBar);
        buttonTopLeft = findViewById((R.id.buttonTopLeft));
        buttonTopRight = findViewById(R.id.buttonTopRight);
        buttonBottomLeft = findViewById(R.id.buttonBottomLeft);
        buttonBottomRight = findViewById(R.id.buttonBottomRight);
        buttonTopLeft.setOnClickListener(this);
        buttonTopRight.setOnClickListener(this);
        buttonBottomLeft.setOnClickListener(this);
        buttonBottomRight.setOnClickListener(this);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Toast.makeText(getApplicationContext(), "progress: " + i, Toast.LENGTH_LONG).show();
                buttonTopLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX,  i);
                buttonTopRight.setTextSize(TypedValue.COMPLEX_UNIT_PX,  i);
                buttonBottomLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX,  i);
                buttonBottomRight.setTextSize(TypedValue.COMPLEX_UNIT_PX,  i);
                editor.putInt("seekbarProgress", i);
                editor.commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "started touching seekbar", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "stopped touching seekbar", Toast.LENGTH_LONG).show();
            }
        });
        initialize();
    }

    @Override
    public void onClick(View view) {
        Button button = (Button)(view);
        button.setText(""+(Integer.parseInt(button.getText().toString())+1));
        editor.putInt("buttonTopRight", Integer.parseInt(buttonTopRight.getText().toString()));
        editor.putInt("buttonTopLeft", Integer.parseInt(buttonTopLeft.getText().toString()));
        editor.putInt("buttonBottomRight", Integer.parseInt(buttonBottomRight.getText().toString()));
        editor.putInt("buttonBottomLeft", Integer.parseInt(buttonBottomLeft.getText().toString()));
        editor.commit();
    }
    private void initialize(){
        buttonTopLeft.setText(""+sharedPreferences.getInt("buttonTopLeft", 0));
        buttonTopRight.setText(""+sharedPreferences.getInt("buttonTopRight", 0));
        buttonBottomLeft.setText(""+sharedPreferences.getInt("buttonBottomLeft", 0));
        buttonBottomRight.setText(""+sharedPreferences.getInt("buttonBottomRight", 0));
        sb.setProgress(sharedPreferences.getInt("seekbarProgress", 50));
    }
}