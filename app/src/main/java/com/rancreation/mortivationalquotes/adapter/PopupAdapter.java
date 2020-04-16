package com.rancreation.mortivationalquotes.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rancreation.mortivationalquotes.R;

public class PopupAdapter extends ArrayAdapter<String> {


    private String[] quotesname;
    private Activity context;

    public PopupAdapter(Activity context, String[] quotesname) {
        super(context, R.layout.popuplayout, quotesname);

        this.context = context;
        this.quotesname = quotesname;

    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        View r = convertView;
        PopupAdapter.ViewHolder viewHolder = null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.popupitem,null,true);
            viewHolder = new PopupAdapter.ViewHolder(r);
            r.setTag(viewHolder);
        }

        else{
            viewHolder = (PopupAdapter.ViewHolder) r.getTag();


        }

        viewHolder.popuptext.setText(quotesname[position]);

        return r;
    }

    class ViewHolder{

        TextView popuptext;

        ViewHolder(View v){
            popuptext = v.findViewById(R.id.popuptext);

        }

    }
}
