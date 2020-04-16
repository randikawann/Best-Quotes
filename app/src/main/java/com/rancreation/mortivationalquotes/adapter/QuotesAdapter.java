package com.rancreation.mortivationalquotes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rancreation.mortivationalquotes.activity.ImageVIewActivity;
import com.rancreation.mortivationalquotes.R;
import com.rancreation.mortivationalquotes.model.Quote;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>{

    Context context;
    List<Quote> quotelist;
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

    public QuotesAdapter(Context context, List<Quote> quotelist){
        this.context = context;
        this.quotelist = quotelist;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater mInflate =LayoutInflater.from(context);
//        view = mInflate.inflate(R.layout.quotecardlistitem, viewGroup, false);
        view = mInflate.inflate(R.layout.quotecardviewitem, viewGroup, false);
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
//                goimageviewactivity.putExtra("imgeurl", quotelist.get(i).getUrl());
//                goimageviewactivity.getIntExtra("imgurlint",quoteimage[i]);
                goimageviewactivity.putExtra("imgurlint2",quoteimage[i]);
//                goimageviewactivity.putExtra("titletext", quotelist.get(i).getTitle());
                context.startActivity(goimageviewactivity);
            }
        });

//        Glide.with(context)
//                .load(quotelist.get(i).getThumbnailUrl())
//                .centerCrop()
////                .placeholder(R.drawable.loading_spinner)
//                .into(quoteViewHolder.imgquotthumb);

//        quoteViewHolder.imgquotthumb.setBackground(context.getDrawable(quoteimage[i]));
        quoteViewHolder.imgquotthumb.setImageResource(quoteimage[i]);



    }

    @Override
    public int getItemCount() {

//        return quotelist.size();
        return quoteimage.length;
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
