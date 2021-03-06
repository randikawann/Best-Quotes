package com.rancreation.mortivationalquotes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rancreation.mortivationalquotes.activity.OtherViewActivity;
import com.rancreation.mortivationalquotes.R;

public class OtherQuotesAdapter extends RecyclerView.Adapter<OtherQuotesAdapter.OtherQuoteViewHolder>{

    Context context;
//    List<User> otherquotetitle;
    String[] otherquotestitles;
    int[] otherquotesicon;


//    public OtherQuotesAdapter(Context context, List<User> otherquotetitle){
//        this.context = context;
//        this.otherquotetitle = otherquotetitle;
//    }

//    public OtherQuotesAdapter(Context context, String[] otherquotestitles) {
//        this.context = context;
//        this.otherquotestitles = otherquotestitles;
//    }

    public OtherQuotesAdapter(Context context, String[] otherquotestitles, int[] otherquotesicon) {
        this.context = context;
        this.otherquotestitles = otherquotestitles;
        this.otherquotesicon = otherquotesicon;
    }


    @NonNull
    @Override
    public OtherQuoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflate =LayoutInflater.from(context);
//        view = mInflate.inflate(R.layout.othercardlisttitle, viewGroup, false);
        view = mInflate.inflate(R.layout.othercardviewtitle, viewGroup, false);
        return new OtherQuotesAdapter.OtherQuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherQuoteViewHolder otherQuoteViewHolder, final int i) {

//        otherQuoteViewHolder.tvotherquotetitle.setText(otherquotestitles.get(i).getUsername());
        otherQuoteViewHolder.tvotherquotetitle.setText(otherquotestitles[i]);
        otherQuoteViewHolder.imgotherquotetitle.setImageResource(otherquotesicon[i]);
//        Log.i("1234","Get user name : "+otherquotetitle.get(i).getUsername());

        otherQuoteViewHolder.othertitlelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context, OtherViewActivity.class).putExtra("viewtitle",otherquotetitle.get(i).getUsername()));
                context.startActivity(new Intent(context, OtherViewActivity.class).putExtra("viewtitle",otherquotestitles[i]));
            }
        });



    }

    @Override
    public int getItemCount() {
        return otherquotestitles.length;
    }

    class OtherQuoteViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private ImageView imgotherquotetitle;
        private TextView tvotherquotetitle;
        private LinearLayout othertitlelayout;


        OtherQuoteViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            imgotherquotetitle = itemView.findViewById(R.id.imgotherquotetitle);
            tvotherquotetitle = itemView.findViewById(R.id.tvotherquotetitle);
            othertitlelayout = itemView.findViewById(R.id.othertitlelayout);

        }
    }

}
