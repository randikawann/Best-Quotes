package com.rancreation.mortivationalquotes.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rancreation.mortivationalquotes.R;
import com.rancreation.mortivationalquotes.adapter.QuotesAdapter;
import com.rancreation.mortivationalquotes.controller.Common;
import com.rancreation.mortivationalquotes.model.Quote;
import com.rancreation.mortivationalquotes.remote.QuoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MortivationFragment extends Fragment {

    View v;

    public QuoteService mService;

    private RecyclerView recuclermortifragment;
    private QuotesAdapter quotesAdapter;

    public MortivationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_mortivation, container, false);


        mService = Common.getadsService();

//        getmortivationQuotes();

        //this is for test pupors
//        List<Quote> quotelist = null;
            int quoteimage[] = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
            R.drawable.pic9,
            R.drawable.pic10,
            R.drawable.pic11,
            R.drawable.pic12,
            R.drawable.pic13,
            R.drawable.pic14,
            R.drawable.pic15,
            R.drawable.pic16,
            R.drawable.pic17,
            R.drawable.pic18,
            R.drawable.pic19,
            R.drawable.pic20,
            R.drawable.pic21,
            R.drawable.pic22,
            R.drawable.pic23

    };
//        generateDataList(quotelist);

        generateDataList(quoteimage);

        return v;
    }

    private void getmortivationQuotes() {
        mService.getmortiqoutes().enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
//                Log.i("1234","response "+response.body());
//                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                Log.i("1234","error "+t.toString());

            }
        });
    }

//    private void generateDataList(List<Quote> quotelist) {
//
//        recuclermortifragment = v.findViewById(R.id.recuclermortifragment);
////        recuclermortifragment.setHasFixedSize(true);
//        quotesAdapter = new QuotesAdapter(getContext(), quotelist);
//        recuclermortifragment.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        recuclermortifragment.setAdapter(quotesAdapter);
//
//    }

    private void generateDataList(int[] quotelist) {

        recuclermortifragment = v.findViewById(R.id.recuclermortifragment);
//        recuclermortifragment.setHasFixedSize(true);
        quotesAdapter = new QuotesAdapter(getContext(), quotelist);
        recuclermortifragment.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recuclermortifragment.setAdapter(quotesAdapter);

    }
}
