package com.malariadetection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HubPage extends AppCompatActivity {

    private static final String TAG =HubPage.class.getSimpleName() ;
    ChipNavigationBar bottomNav;
    private Toolbar toolbar;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_page);
        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);

        bottomNav = findViewById(R.id.bottom_nav);


        if(savedInstanceState==null){
            bottomNav.setItemSelected(R.id.home, true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        }
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;

                switch (id){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;

                  case R.id.dashboard:
                            fragment = new TestFragment();
                        break;
                    /*case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext()
                        ,MainActivity.class));
                        overridePendingTransition(0, 0);
                       break;*/

                    case R.id.report:
                        fragment = new ReportFragment();
                        break;

                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                }

                if(fragment !=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();

                }else{
                    Log.e(TAG, "ERROR creating Fragment");
                }
            }
        });
    }
}