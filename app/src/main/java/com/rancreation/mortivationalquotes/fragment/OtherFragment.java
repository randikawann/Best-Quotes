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
import com.rancreation.mortivationalquotes.adapter.OtherQuotesAdapter;
import com.rancreation.mortivationalquotes.controller.Common;
import com.rancreation.mortivationalquotes.model.User;
import com.rancreation.mortivationalquotes.remote.QuoteService;

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

        String otherquotestitles[] = {"Lonely","Angry", "Anniversary", "Birthday", "Dating", "Friendship", "Good Morning", "Good Night", "Love"
                , "Relationship"};

        int otherquotesicon[] = { R.drawable.other1, R.drawable.other2, R.drawable.other3 , R.drawable.other4, R.drawable.other5, R.drawable.other6,
                R.drawable.other7, R.drawable.other8, R.drawable.other9, R.drawable.other10};



        recuclerotherfragment = v.findViewById(R.id.recuclerotherfragment);
        recuclerotherfragment.setHasFixedSize(true);
        otherQuotesAdapter = new OtherQuotesAdapter(getContext(), otherquotestitles, otherquotesicon);
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
