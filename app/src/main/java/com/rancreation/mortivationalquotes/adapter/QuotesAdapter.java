package com.rancreation.mortivationalquotes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rancreation.mortivationalquotes.activity.ImageVIewActivity;
import com.rancreation.mortivationalquotes.R;
import com.rancreation.mortivationalquotes.model.Quote;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>{

    Context context;
    List<Quote> quotelist;

    public QuotesAdapter(Context context, List<Quote> quotelist){
        this.context = context;
        this.quotelist = quotelist;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater mInflate =LayoutInflater.from(context);
        view = mInflate.inflate(R.layout.quotecardlistitem, viewGroup, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder quoteViewHolder, final int i) {

//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
//        builder.build().load(quotelist.get(i))
//                .placeholder((R.drawable.ic_launcher_background))
//                .error(R.drawable.ic_launcher_background)
//                .into(customViewHolder.imgitemads);

//        quoteViewHolder.imgquotthumb.setBackground(context.getDrawable(R.drawable.mainquoteback));

//        Log.i("1234", "title value :"+quotelist.get(i).getTitle());
        quoteViewHolder.imgquotthumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goimageviewactivity = new Intent(context, ImageVIewActivity.class);
                goimageviewactivity.putExtra("imgeurl", quotelist.get(i).getUrl());
                goimageviewactivity.putExtra("titletext", quotelist.get(i).getTitle());
                context.startActivity(goimageviewactivity);
            }
        });

        Glide.with(context)
                .load(quotelist.get(i).getThumbnailUrl())
                .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
                .into(quoteViewHolder.imgquotthumb);



    }

    @Override
    public int getItemCount() {
        return quotelist.size();
    }

    class QuoteViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private ImageView imgquotthumb;


        QuoteViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            imgquotthumb = itemView.findViewById(R.id.imgquotthumb);

        }
    }
}
