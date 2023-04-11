package com.example.appforbeachcleaningoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logout;
    TextView textView;
    ImageView mapIconBtn, messageIconBtn;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logOutBtnHome);
        textView = findViewById(R.id.homeDisplayedEmail);
        user = auth.getCurrentUser();
        mapIconBtn = findViewById(R.id.mapIcon);
        messageIconBtn = findViewById(R.id.messageIcon);

        if (user == null){
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(i);
            finish();
        }
        else {
            textView.setText(user.getEmail());
        }



        //if user presses logout button:
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        //if user clicks on the Map Icon:
        mapIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(i);
                finish();
            }
        });

        //if user clicks on the Message Icon:
        messageIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MessagingActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}