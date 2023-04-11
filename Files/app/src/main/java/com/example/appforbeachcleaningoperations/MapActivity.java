package com.example.appforbeachcleaningoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MapActivity extends AppCompatActivity {

    ImageView messagingIconBtn, homeIconBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        messagingIconBtn = findViewById(R.id.messageIcon);
        homeIconBtn = findViewById(R.id.homeIcon);

        //if user clicks on the Map Icon:
        messagingIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MapActivity.this, MessagingActivity.class);
                startActivity(i);
                finish();
            }
        });

        //if user clicks on the Home Icon:
        homeIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MapActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}