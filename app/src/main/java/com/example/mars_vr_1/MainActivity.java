package com.example.mars_vr_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.mars1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    fragment_main mainFragment;
    fragment_record recordFragment;
    fragment_lisence lisenceFragment;
    fragment_mypage mypageFragment;
    fragment_lisence_f lisencefront;
    fragment_schedule scheduleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new fragment_main();
        recordFragment = new fragment_record();
        lisenceFragment = new fragment_lisence();
        mypageFragment = new fragment_mypage();
        lisencefront = new fragment_lisence_f();
        scheduleFragment = new fragment_schedule();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
        BottomNavigationView bottom_menu = findViewById(R.id.bottom_menu);
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.main:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
                        return true;
                    case R.id.record:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, recordFragment).commit();
                        return true;
                    case R.id.lisence:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, lisenceFragment).commit();
                        return true;
                    case R.id.mypage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mypageFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }
}