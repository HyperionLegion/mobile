package com.joshuahsueh.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.File;
import java.text.DateFormat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.DateFormat;
public class chat extends AppCompatActivity {
    private Button logout;
    private FirebaseAuth mAuth;
    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<ChatMessage> adapter;
    private FloatingActionButton fab;
    ConstraintLayout activity_main;
    private StorageReference mStorageRef;
    public void logOut(){
        mAuth.signOut();
        System.out.println("Authen Test: logout activity");
        Toast.makeText(getApplicationContext(), "Logout success.",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mAuth = FirebaseAuth.getInstance();

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

            mAuth = FirebaseAuth.getInstance();
            activity_main = findViewById(R.id.activity_chat);
            fab = findViewById(R.id.fab);
            mStorageRef = FirebaseStorage.getInstance().getReference();
            //set send function
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText input = findViewById(R.id.input_message);
                    //push message to database
                    FirebaseDatabase.getInstance().getReference().push().setValue(
                            new ChatMessage(input.getText().toString(), mAuth.getCurrentUser().getEmail()));
                    input.setText("");//clear input box
                    System.out.println("Message sent");
                    //upload();
                }
            });
            //navigate to Signin page if not signed in
            if(mAuth.getCurrentUser()==null){
                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),
                        SIGN_IN_REQUEST_CODE);
            } else {
                Snackbar.make(activity_main,"Welcome "+mAuth.getCurrentUser().getEmail(),
                        Snackbar.LENGTH_SHORT).show();
                //load content
                displayChatMessage();
            }
        }

        private void displayChatMessage() {
            ListView messages = findViewById(R.id.messages);
            Query query = FirebaseDatabase.getInstance().getReference();
            FirebaseListOptions<ChatMessage> options = new FirebaseListOptions.Builder<ChatMessage>()
                    .setLayout(R.layout.list_item_layout)
                    .setQuery(query, ChatMessage.class)
                    .build();

            adapter = new FirebaseListAdapter<ChatMessage>(options ) {
                @Override
                protected void populateView(View v, ChatMessage model, int position) {
                    // Get references to the views of message.xml
                    TextView messageText = (TextView)v.findViewById(R.id.message_text);
                    TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                    TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                    // Set their text
                    messageText.setText(model.getMessageText());
                    messageUser.setText(model.getMessageUser());
                    // Format the date before showing it
                    messageTime.setText(DateFormat.getInstance().format(model.getMessageTime()));
                }
            };

                messages.setAdapter(adapter);
            adapter.startListening();
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==SIGN_IN_REQUEST_CODE){
                if(resultCode==RESULT_OK){
                    Snackbar.make(activity_main,"Successfully signed in. Welcome!",
                            Snackbar.LENGTH_SHORT).show();
                    displayChatMessage();
                } else {
                    Snackbar.make(activity_main,"Couldn't sign in.  Please try again.",
                            Snackbar.LENGTH_SHORT).show();
                    finish();//exit
                }
            }
        }




        @Override
        protected void onStart() {
            super.onStart();
            if(adapter!=null)
                adapter.startListening();
        }

        @Override
        protected void onStop() {
            super.onStop();
            if(adapter!=null)
                adapter.stopListening();
        }
    }