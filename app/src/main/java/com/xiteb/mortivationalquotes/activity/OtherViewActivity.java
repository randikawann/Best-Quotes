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

        int quoteimage[] = new int[0];
        if(viewtitle.equals("Lonely")){
            quoteimage = new int[]{R.drawable.along1, R.drawable.along2, R.drawable.along3, R.drawable.along4, R.drawable.along5,
                    R.drawable.along6, R.drawable.along7, R.drawable.along8, R.drawable.along9, R.drawable.along10};
        }else if(viewtitle.equals("Angry")){
            quoteimage = new int[]{R.drawable.angry1, R.drawable.angry2, R.drawable.angry3, R.drawable.angry4, R.drawable.angry5,
                    R.drawable.angry6, R.drawable.angry7, R.drawable.angry8, R.drawable.angry9, R.drawable.angry10};
        }else if(viewtitle.equals("Anniversary")){
            quoteimage = new int[]{R.drawable.annivessary1, R.drawable.annivessary2, R.drawable.annivessary3, R.drawable.annivessary4, R.drawable.annivessary5,
                    R.drawable.annivessary6, R.drawable.annivessary7, R.drawable.annivessary8, R.drawable.annivessary9, R.drawable.annivessary10};

        }else if(viewtitle.equals("Birthday")){
            quoteimage = new int[]{R.drawable.birthday1, R.drawable.birthday2, R.drawable.birthday3, R.drawable.birthday4, R.drawable.birthday5,
                    R.drawable.birthday6, R.drawable.birthday7, R.drawable.birthday8, R.drawable.birthday9, R.drawable.birthday10};

        }else if(viewtitle.equals("Dating")){
            quoteimage = new int[]{R.drawable.dating1, R.drawable.dating2, R.drawable.dating3, R.drawable.dating4, R.drawable.dating5,
                    R.drawable.dating6, R.drawable.dating7, R.drawable.dating8, R.drawable.dating9, R.drawable.dating10};

        }else if(viewtitle.equals("Friendship")){
            quoteimage = new int[]{R.drawable.friendship1, R.drawable.friendship2, R.drawable.friendship3, R.drawable.friendship4, R.drawable.friendship5,
                    R.drawable.friendship6, R.drawable.friendship7, R.drawable.friendship8, R.drawable.friendship9, R.drawable.friendship10};

        }else if(viewtitle.equals("Good Morning")){
            quoteimage = new int[]{R.drawable.gm1, R.drawable.gm2, R.drawable.gm3, R.drawable.gm4, R.drawable.gm5,
                    R.drawable.gm6, R.drawable.gm7, R.drawable.gm8, R.drawable.gm9, R.drawable.gm10};

        }else if(viewtitle.equals("Good Night")){
            quoteimage = new int[]{R.drawable.gn1, R.drawable.gn2, R.drawable.gn3, R.drawable.gn4, R.drawable.gn5,
                    R.drawable.gn6, R.drawable.gn7, R.drawable.gn8, R.drawable.gn9, R.drawable.gn10};

        }else if(viewtitle.equals("Love")){
            quoteimage = new int[]{R.drawable.love1, R.drawable.love2, R.drawable.love3, R.drawable.love4, R.drawable.love5,
                    R.drawable.love6, R.drawable.love7, R.drawable.love8, R.drawable.love9, R.drawable.love10};

        }else if(viewtitle.equals("Relationship")){
            quoteimage = new int[]{R.drawable.relationship1, R.drawable.relationship2, R.drawable.relationship3, R.drawable.relationship4, R.drawable.relationship5,
                    R.drawable.relationship6, R.drawable.relationship7, R.drawable.relationship8, R.drawable.relationship9, R.drawable.relationship10};

        }else{

        }
//        int quoteimage[] = {
//                R.drawable.pic1,
//                R.drawable.pic2,
//                R.drawable.pic3,
//                R.drawable.pic4,
//                R.drawable.pic5,
//                R.drawable.pic6,
//                R.drawable.pic7,
//                R.drawable.pic8,
//                R.drawable.pic9,
//                R.drawable.pic10,
//                R.drawable.pic11,
//                R.drawable.pic12,
//                R.drawable.pic13,
//                R.drawable.pic14,
//                R.drawable.pic15,
//                R.drawable.pic16,
//                R.drawable.pic17,
//                R.drawable.pic18,
//                R.drawable.pic19,
//                R.drawable.pic20,
//                R.drawable.pic21,
//                R.drawable.pic22,
//                R.drawable.pic23
//
//        };

        recuclerotheractivity = findViewById(R.id.recuclerotheractivity);
        recuclerotheractivity.setHasFixedSize(true);
        quotesAdapter = new QuotesAdapter(this, quoteimage);
        recuclerotheractivity.setLayoutManager(new GridLayoutManager(this, 2));
        recuclerotheractivity.setAdapter(quotesAdapter);


//        mService = Common.getadsService();
//
//        getmortivationQuotes();




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

//        recuclerotheractivity = findViewById(R.id.recuclerotheractivity);
//        recuclerotheractivity.setHasFixedSize(true);
//        quotesAdapter = new QuotesAdapter(this, quotelist, quoteimage);
//        recuclerotheractivity.setLayoutManager(new GridLayoutManager(this, 2));
//        recuclerotheractivity.setAdapter(quotesAdapter);

    }





}
