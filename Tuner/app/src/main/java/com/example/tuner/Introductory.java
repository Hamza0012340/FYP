package com.example.tuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Introductory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);
        getSupportActionBar().setTitle(R.string.select_your_instrument1);




        ImageView pamirirub = findViewById(R.id.pamirirub);
        pamirirub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Introductory.this, Pamirirub.class);
                startActivity(intent);
            }
        });

        ImageView kyrgizkom = findViewById(R.id.kyrgyzkom);
        kyrgizkom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Introductory.this, Kyrgyzkum.class);
                startActivity(intent);
            }
        });

        ImageView afghaniRubab = findViewById(R.id.afghanirubab);
        afghaniRubab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Introductory.this, AfghaniRubab.class);
                startActivity(intent);
            }
        });

        ImageView guitar = findViewById(R.id.guitar);
        guitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Introductory.this, guitar.class);
                startActivity(intent);
            }
        });




    }
}
