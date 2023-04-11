package com.example.appforbeachcleaningoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MessagingActivity extends AppCompatActivity {

    ImageView mapIconBtn, homeIconBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        mapIconBtn = findViewById(R.id.mapIcon);
        homeIconBtn = findViewById(R.id.homeIcon);

        //if user clicks on the Map Icon:
        mapIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MessagingActivity.this, MapActivity.class);
                startActivity(i);
                finish();
            }
        });

        //if user clicks on the Home Icon:
        homeIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MessagingActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}