package com.example.tuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Introductory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);


        Button pamirirub = findViewById(R.id.pamirirub);
        pamirirub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Introductory.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button kyrgizkom = findViewById(R.id.kyrgyzkom);
        kyrgizkom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Introductory.this, Kyrgyzkum.class);
                startActivity(intent);
            }
        });

        Button afghaniRubab = findViewById(R.id.afghanirubab);
        afghaniRubab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Introductory.this, AfghaniRubab.class);
                startActivity(intent);
            }
        });


    }

}