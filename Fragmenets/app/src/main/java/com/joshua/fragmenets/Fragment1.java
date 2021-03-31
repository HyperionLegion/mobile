package com.joshua.fragmenets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    TextView frag1;
    int someInt;
    String someString;
    public static Fragment1 newInstance(int someInt, String someString){
        Fragment1 f1 = new Fragment1();
        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        args.putString("someString", someString);
        f1.setArguments(args);
        return f1;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment1, parent, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        someInt = getArguments().getInt("someInt", 0);
        someString = getArguments().getString("someString", "");
    }
    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        frag1 = view.findViewById(R.id.frag1);
        frag1.setText(someString);
        Toast.makeText(getActivity(), "Fragment #"+(someInt),
                Toast.LENGTH_LONG).show();
    }
}
