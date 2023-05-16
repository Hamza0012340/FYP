package com.example.tuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class help extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView actionBarTitle = (TextView) findViewById(R.id.action_bar_title);
        actionBarTitle.setText(R.string.Help);

        ImageButton language = findViewById(R.id.language_button);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("clicked");
                PopupMenu popupMenu = new PopupMenu(help.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.language_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if (menuItem.getItemId() == R.id.english){
                            Locale locale = new Locale("en");
                            Locale.setDefault(locale);
                            Configuration configuration = new Configuration();
                            configuration.setLocale(locale);
                            getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                            Toast.makeText(help.this,"english",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        else if (menuItem.getItemId() == R.id.russian){
                            Locale locale = new Locale("ru");
                            Locale.setDefault(locale);
                            Configuration configuration = new Configuration();
                            configuration.setLocale(locale);
                            getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                            Toast.makeText(help.this,"russian",Toast.LENGTH_LONG).show();
                            return true;

                        }
                        return false;
                    };
                });

                popupMenu.show();


            }
        });

    }
}