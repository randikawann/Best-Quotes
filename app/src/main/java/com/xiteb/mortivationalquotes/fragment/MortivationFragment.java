package com.xiteb.mortivationalquotes.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiteb.mortivationalquotes.R;
import com.xiteb.mortivationalquotes.adapter.QuotesAdapter;
import com.xiteb.mortivationalquotes.controller.Common;
import com.xiteb.mortivationalquotes.model.Quote;
import com.xiteb.mortivationalquotes.remote.QuoteService;

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
        List<Quote> quotelist = null;
        int quoteimage[] = new int[0];
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
