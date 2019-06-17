package com.xiteb.mortivationalquotes;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiteb.mortivationalquotes.adapter.ImageGaleryAdapter;

import java.util.ArrayList;

public class FontFaceAdapter extends RecyclerView.Adapter<FontFaceAdapter.ViewHolder>{

    ArrayList<Typeface> fontfacepicker;
    FontFaceClick listener;

    public FontFaceAdapter(FontFaceClick listner, ArrayList<Typeface> fontfacepicker) {
        this.listener=listner;
        this.fontfacepicker = fontfacepicker;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View rootView = inflater.inflate(R.layout.fontfaceitem, viewGroup, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.samplefonttext.setTypeface(fontfacepicker.get(i));

        viewHolder.samplefonttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.fontchange(fontfacepicker.get(i));
            }
        });





    }

    @Override
    public int getItemCount() {
        return fontfacepicker.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView samplefonttext;

        public ViewHolder(View itemView) {
            super(itemView);

            samplefonttext = itemView.findViewById(R.id.samplefonttext);

        }
    }
}
