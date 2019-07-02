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
        if(viewtitle.equals("Along")){
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
            quoteimage = new int[]{R.drawable.birthday1};

        }else if(viewtitle.equals("Friendship")){
            quoteimage = new int[]{R.drawable.birthday1};

        }else if(viewtitle.equals("Good Morning")){
            quoteimage = new int[]{R.drawable.birthday1};

        }else if(viewtitle.equals("Good Night")){
            quoteimage = new int[]{R.drawable.birthday1};

        }else if(viewtitle.equals("Love")){
            quoteimage = new int[]{R.drawable.birthday1};

        }else if(viewtitle.equals("Relationship")){
            quoteimage = new int[]{R.drawable.birthday1};

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
