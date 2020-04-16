package com.rancreation.mortivationalquotes.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rancreation.mortivationalquotes.R;
import com.rancreation.mortivationalquotes.adapter.TapPageAdapter;

public class MenuActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        storageaccesspermission();
        //fragment layout
        ViewPager myviewPager = findViewById(R.id.main_view_pager);
        TapPageAdapter myTabPageAdapter = new TapPageAdapter(getSupportFragmentManager());
        myviewPager.setAdapter(myTabPageAdapter);
        TabLayout main_tab_bar = findViewById(R.id.main_tab_bar);

        main_tab_bar.setupWithViewPager(myviewPager);


        //fab
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,MainActivity.class));
            }
        });


    }

    private void storageaccesspermission() {
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission()) {
                setContentView(R.layout.activity_menu);
            } else {
                requestPermission();
            }
        }
        else
        {
            requestPermission();
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MenuActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MenuActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(MenuActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MenuActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
}
