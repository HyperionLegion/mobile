package com.joshua.fragmenets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;
    FragmentTransaction ft;
    FrameLayout fl;
    Fragment1 f1;
    Fragment2 f2;
    Fragment3 f3;
    Button mButton1, mButton2, mButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);
        f1 = Fragment1.newInstance(1, "Fragment 1");
        f2 = Fragment2.newInstance(2, "Fragment 2");
        f3 = Fragment3.newInstance(3, "Fragment 3");
        fl = findViewById(R.id.framelayout);
       // ft = getSupportFragmentManager().beginTransaction();
       // ft.replace(R.id.framelayout, new Fragment1());
      //  ft.commit();
        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText("Fragment 1");
                ft = getSupportFragmentManager().beginTransaction();
                if (f1.isAdded()) { // if the fragment is already in container
                    ft.show(f1);
                } else { // fragment needs to be added to frame container
                    ft.add(R.id.framelayout, f1, "FRAG1");
                }
                if (f2.isAdded()) { ft.hide(f2); }
                if (f3.isAdded()) { ft.hide(f3); }
                // Commit changes
                ft.commit();
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText("Fragment 2");
                ft = getSupportFragmentManager().beginTransaction();
                if (f2.isAdded()) { // if the fragment is already in container
                    ft.show(f2);
                } else { // fragment needs to be added to frame container
                    ft.add(R.id.framelayout, f2, "FRAG2");
                }
                if (f1.isAdded()) { ft.hide(f1); }
                if (f3.isAdded()) { ft.hide(f3); }
                // Commit changes
                ft.commit();
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText("Fragment 3");
                ft = getSupportFragmentManager().beginTransaction();
                if (f3.isAdded()) { // if the fragment is already in container
                    ft.show(f3);
                } else { // fragment needs to be added to frame container
                    ft.add(R.id.framelayout, f3, "FRAG3");
                }
                if (f2.isAdded()) { ft.hide(f2); }
                if (f1.isAdded()) { ft.hide(f1); }
                // Commit changes
                ft.commit();
            }
        });

    }
}