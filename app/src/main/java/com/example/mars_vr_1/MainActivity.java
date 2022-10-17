package com.example.mars_vr_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.Feedback.fragment_feedback;
import com.example.Mypage.fragment_edit;
import com.example.Mypage.fragment_mypage;
import com.example.Rank.fragment_ranking;
import com.example.mars1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    fragment_main mainFragment;
    fragment_feedback recordFragment;
    fragment_ranking rankingFragment;
    fragment_mypage mypageFragment;
    fragment_edit fragmentedit;

    private long lastTimeBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new fragment_main();
        recordFragment = new fragment_feedback();
        rankingFragment = new fragment_ranking();
        mypageFragment = new fragment_mypage();
        fragmentedit = new fragment_edit();


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
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, rankingFragment).commit();
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

    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragmentList){
            if(fragment instanceof onBackPressedListener){
                ((onBackPressedListener)fragment).onBackPressed();
                return;
            }
        }
        if(System.currentTimeMillis()-lastTimeBackPressed<1500){
            finish();
            return;
        }
        lastTimeBackPressed=System.currentTimeMillis();
        Toast.makeText(this,"'뒤로'버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
    }
}