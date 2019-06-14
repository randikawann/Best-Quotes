package com.xiteb.mortivationalquotes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiteb.mortivationalquotes.R;
import com.xiteb.mortivationalquotes.model.Quote;
import com.xiteb.mortivationalquotes.model.User;

import java.util.List;

public class OtherQuotesAdapter extends RecyclerView.Adapter<OtherQuotesAdapter.OtherQuoteViewHolder>{

    Context context;
    List<User> otherquotetitle;

    public OtherQuotesAdapter(Context context, List<User> otherquotetitle){
        this.context = context;
        this.otherquotetitle = otherquotetitle;
    }


    @NonNull
    @Override
    public OtherQuoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflate =LayoutInflater.from(context);
        view = mInflate.inflate(R.layout.othercardlisttitle, viewGroup, false);
        return new OtherQuotesAdapter.OtherQuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherQuoteViewHolder otherQuoteViewHolder, int i) {

        otherQuoteViewHolder.tvotherquotetitle.setText(otherquotetitle.get(i).getUsername());
        Log.i("1234","Get user name : "+otherquotetitle.get(i).getUsername());


    }

    @Override
    public int getItemCount() {
        return otherquotetitle.size();
    }

    class OtherQuoteViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private ImageView imgotherquotetitle;
        private TextView tvotherquotetitle;


        OtherQuoteViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            imgotherquotetitle = itemView.findViewById(R.id.imgotherquotetitle);
            tvotherquotetitle = itemView.findViewById(R.id.tvotherquotetitle);

        }
    }

}
