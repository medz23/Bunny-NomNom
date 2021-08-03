package com.example.bunnynomnom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    ImageView settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       settings = findViewById(R.id.settingsBt);
       settings.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, SettingsANormal.class);// New activity
               startActivity(intent);
           }
       });

        navigationView = findViewById(R.id.bottomNav);
        getSupportFragmentManager().beginTransaction().replace(R.id.bodyContainer, new AddFragment()).commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.new_food:
                        fragment = new AddFragment();
                        break;
                    case R.id.prev_food:
                        fragment = new PrevFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;

                    default:
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.bodyContainer, fragment).commit();
                return true;
            }
        });

    }
}
