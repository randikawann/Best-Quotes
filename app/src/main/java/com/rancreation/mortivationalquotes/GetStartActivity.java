package com.rancreation.mortivationalquotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.rancreation.mortivationalquotes.activity.MenuActivity;

public class GetStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);

        LinearLayout linear3 = findViewById(R.id.linear3);

        linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetStartActivity.this, MenuActivity.class));
            }
        });

//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                startActivity(new Intent(GetStartActivity.this, MenuActivity.class));
//                finish();
//            }
//        }).run();


    }
}
