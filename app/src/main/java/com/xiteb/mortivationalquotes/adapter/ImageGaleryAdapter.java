package com.xiteb.mortivationalquotes.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiteb.mortivationalquotes.R;
import com.xiteb.mortivationalquotes.interfaces.RecyclerImageClick;

import java.util.List;

public class ImageGaleryAdapter extends RecyclerView.Adapter<ImageGaleryAdapter.ViewHolder>  {

    RecyclerImageClick listener;
//    private Context context;
    private LayoutInflater inflater;
    private List<Integer> galerypicker;
    private ColorPickerAdapter.OnColorPickerClickListener onColorPickerClickListener;

    View view2;


    public ImageGaleryAdapter(@NonNull RecyclerImageClick listener, @NonNull List<Integer> galerypicker) {
        this.listener=listener;
        this.galerypicker = galerypicker;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View rootView = inflater.inflate(R.layout.galleryscrollitemcard, viewGroup, false);
        return new ViewHolder(rootView);

    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.imggaleryitem.setImageResource(galerypicker.get(i));




        viewHolder.imggaleryitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i != 0){
                    listener.myClickListener(galerypicker.get(i));
                }else{
                    listener.colorchange();
                }

            }
        });


        /*
        holder.itemView.setonClickListener(new View.OnClickListener(){
            Glide.with(view.getContext()).load(string_url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .crossFade()
                    .into(img_back);
                    */
    }


    @Override
    public int getItemCount() {
        return galerypicker.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imggaleryitem;
        ImageView backgroundimage;

        public ViewHolder(View itemView) {
            super(itemView);

            backgroundimage = itemView.findViewById(R.id.photo_edit_iv);
            imggaleryitem = itemView.findViewById(R.id.imggaleryitem);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(context, "Position :"+getPosition(), Toast.LENGTH_SHORT).show();
//            backgroundimage.setImageResource(galerypicker.get(getPosition()));
//            Log.i("1234","Position is : "+getPosition());

        }
    }

}
