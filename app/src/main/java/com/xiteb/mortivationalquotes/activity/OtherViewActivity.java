package com.xiteb.mortivationalquotes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.xiteb.mortivationalquotes.R;
import com.xiteb.mortivationalquotes.adapter.QuotesAdapter;
import com.xiteb.mortivationalquotes.controller.Common;
import com.xiteb.mortivationalquotes.model.Quote;
import com.xiteb.mortivationalquotes.remote.QuoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherViewActivity extends AppCompatActivity {

    public QuoteService mService;
    private RecyclerView recuclerotheractivity;
    private QuotesAdapter quotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_view);

        String viewtitle = getIntent().getStringExtra("viewtitle");
        Log.i("1234","VIew title is :"+viewtitle);

        getSupportActionBar().setTitle(viewtitle);

        mService = Common.getadsService();

        getmortivationQuotes();


    }

    private void getmortivationQuotes() {
        mService.getmortiqoutes().enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
//                Log.i("1234","response "+response.body());
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                Log.i("1234","error "+t.toString());

            }
        });
    }

    private void generateDataList(List<Quote> quotelist) {

        recuclerotheractivity = findViewById(R.id.recuclerotheractivity);
        recuclerotheractivity.setHasFixedSize(true);
        quotesAdapter = new QuotesAdapter(this, quotelist);
        recuclerotheractivity.setLayoutManager(new GridLayoutManager(this, 2));
        recuclerotheractivity.setAdapter(quotesAdapter);

    }





}
