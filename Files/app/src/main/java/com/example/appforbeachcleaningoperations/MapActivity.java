package com.example.appforbeachcleaningoperations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    ImageView messagingIconBtn, homeIconBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initialiseMap();

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

    //Initialises Google Map Implementation
    public void initialiseMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
    }

}