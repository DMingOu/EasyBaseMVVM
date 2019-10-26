package com.example.odm.basemvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.odm.basemvvm.Base.BaseActivity;

public class NavDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_data);
        init();
    }

    protected void init() {
        FragmentManager fragmentManager  = getSupportFragmentManager();
        NavDataFragment navDataFragment = (NavDataFragment)fragmentManager.findFragmentById(R.id.frg_Nav);
    }
}
