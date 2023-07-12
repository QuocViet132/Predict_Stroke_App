package com.example.strokeprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.strokeprediction.fragment.ViewPageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPager2 = findViewById(R.id.view_page);
        bottomNavigationView = findViewById(R.id.bottom_nav_menu);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);
        viewPager2.setAdapter(viewPageAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_predict).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_library).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_account).setChecked(true);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                viewPager2.setCurrentItem(0);
            } else if (itemId == R.id.menu_predict) {
                viewPager2.setCurrentItem(1);
            } else if (itemId == R.id.menu_library) {
                viewPager2.setCurrentItem(2);
            } else if (itemId == R.id.menu_account) {
                viewPager2.setCurrentItem(3);
            }
            return true;
        });
        int library;
        int user;
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            library = bundle.getInt("library",0);
            user = bundle.getInt("user",0);
        } catch (Exception NullPointerException) {
//            Toast.makeText(MainActivity2.this,"Not Bundle",Toast.LENGTH_SHORT).show();
            library = 0;
            user = 0;
        }

        if (library == 2) {
            viewPager2.setCurrentItem(2);
        }
        if (user == 3) {
            viewPager2.setCurrentItem(3);
        }
    }
}