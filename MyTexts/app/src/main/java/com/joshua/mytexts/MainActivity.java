package com.joshua.mytexts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int index =0;
    String[] strings;
    TextView textView, team;
    EditText editText;
    Button submit, bswitch;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        team = findViewById(R.id.team);
        editText = findViewById(R.id.editText);
        submit = findViewById(R.id.submit);
        image = findViewById(R.id.imageView);
        bswitch = findViewById(R.id.bswitch);
        Resources res = getResources();
        strings = res.getStringArray(R.array.teams);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText());
            }
        });
        bswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if(index==strings.length)
                    index=0;
                team.setText(strings[index]);
                int id=0;
                switch(index){
                    case 0:
                        id = R.drawable.bears;
                        break;
                    case 1:
                        id = R.drawable.chiefs;
                        break;
                    case 2:
                        id = R.drawable.bills;
                        break;
                    case 3:
                        id = R.drawable.colts;
                        break;
                    case 4:
                        id = R.drawable.steelers;
                        break;
                    case 5:
                        id = R.drawable.browns;
                        break;
                    case 6:
                        id = R.drawable.titans;
                        break;
                    case 7:
                        id = R.drawable.ravens;
                        break;
                    case 8:
                        id = R.drawable.packers;
                        break;
                    case 9:
                        id = R.drawable.saints;
                        break;
                    case 10:
                        id = R.drawable.seahawks;
                        break;
                    case 11:
                        id = R.drawable.rams;
                        break;
                    case 12:
                        id = R.drawable.washington;
                        break;
                    case 13:
                        id = R.drawable.buccaneers;
                        break;
                }
                image.setImageResource(id);

            }
        });
    }
}