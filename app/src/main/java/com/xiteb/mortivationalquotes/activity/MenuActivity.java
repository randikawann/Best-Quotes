package com.xiteb.mortivationalquotes.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xiteb.mortivationalquotes.R;
import com.xiteb.mortivationalquotes.adapter.TapPageAdapter;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
}
