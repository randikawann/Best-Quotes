package com.xiteb.mortivationalquotes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiteb.mortivationalquotes.R;

import java.util.List;

public class ImageGaleryAdapter extends RecyclerView.Adapter<ImageGaleryAdapter.ViewHolder>  {

    private Context context;
    private LayoutInflater inflater;
    private List<Integer> galerypicker;
    private ColorPickerAdapter.OnColorPickerClickListener onColorPickerClickListener;

    View view2;

    public ImageGaleryAdapter(@NonNull Context context, @NonNull List<Integer> galerypicker) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.galerypicker = galerypicker;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.galleryscrollitem, viewGroup, false);
        view2 = inflater.inflate(R.layout.activity_main, viewGroup, false);
        return new ImageGaleryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.imggaleryitem.setImageResource(galerypicker.get(i));

//        final ImageView backimage = view2.findViewById(R.id.photo_edit_iv);


        viewHolder.imggaleryitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeimage(galerypicker.get(i));


//                Log.i("1234", backimage.getDrawable().toString());
            }
        });

    }

    private void changeimage(Integer integer) {
//        ImageView imageView = view2.find

    }

    @Override
    public int getItemCount() {
        return galerypicker.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imggaleryitem;

        public ViewHolder(View itemView) {
            super(itemView);



            imggaleryitem = itemView.findViewById(R.id.imggaleryitem);
        }
    }

}
