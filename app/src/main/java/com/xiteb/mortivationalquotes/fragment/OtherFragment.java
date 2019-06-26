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
import com.xiteb.mortivationalquotes.adapter.OtherQuotesAdapter;
import com.xiteb.mortivationalquotes.adapter.QuotesAdapter;
import com.xiteb.mortivationalquotes.controller.Common;
import com.xiteb.mortivationalquotes.model.User;
import com.xiteb.mortivationalquotes.remote.QuoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OtherFragment extends Fragment {

    View v;
    public QuoteService mService;

    RecyclerView recuclerotherfragment;
    OtherQuotesAdapter otherQuotesAdapter;




    public OtherFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_other, container, false);

        mService = Common.getadsService();

//        getOtherTitle();

        String otherquotestitles[] = {"Alone", "Angry", "Anniversary", "Birthday", "Dating", "Friendship", "Good Morning", "Good Night", "Love"
                , "Relationship"};

        recuclerotherfragment = v.findViewById(R.id.recuclerotherfragment);
        recuclerotherfragment.setHasFixedSize(true);
        otherQuotesAdapter = new OtherQuotesAdapter(getContext(), otherquotestitles);
        recuclerotherfragment.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recuclerotherfragment.setAdapter(otherQuotesAdapter);


        // Inflate the layout for this fragment
        return v;
    }

    private void getOtherTitle() {
        mService.getotherqoutestitle().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                Log.i("1234","other quotes "+response.body());
                otherquotestitle(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i("1234","error "+t.toString());

            }
        });
    }

    private void otherquotestitle(List<User> otherquotetitle) {

//        recuclerotherfragment = v.findViewById(R.id.recuclerotherfragment);
//        recuclerotherfragment.setHasFixedSize(true);
//        otherQuotesAdapter = new OtherQuotesAdapter(getContext(), otherquotetitle);
//        recuclerotherfragment.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        recuclerotherfragment.setAdapter(otherQuotesAdapter);


    }

}
