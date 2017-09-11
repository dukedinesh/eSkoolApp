package com.eduschool.eduschoolapp.Gallery;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eduschool.eduschoolapp.GalleryAlbumPOJO.CreatedDate;
import com.eduschool.eduschoolapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/16/2017.
 */

public class AdapterAlbumPaent2 extends RecyclerView.Adapter<AdapterAlbumPaent2.myviewholder> {

    Context context;
    List<CreatedDate> list1 = new ArrayList<>();


    public AdapterAlbumPaent2(Context context, List<CreatedDate> list) {
        this.context = context;
        this.list1 = list;
    }

    public void setGridData(List<CreatedDate> list) {
        this.list1 = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterAlbumPaent2.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.album_list_model, parent, false);

        return new AdapterAlbumPaent2.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterAlbumPaent2.myviewholder holder, int position) {

        CreatedDate item = list1.get(position);

        //loader.displayImage(item.get);
        //holder.album.setImageResource(R.drawable.folder);
        String i = item.getAlbumId();
        Log.d("ddd", String.valueOf(i));


        holder.name.setText(item.getAlbumName());

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView album;
        TextView name;


        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            album = (ImageView) itemView.findViewById(R.id.album);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    final int pos=getAdapterPosition();
                    String name=list1.get(getAdapterPosition()).getAlbumName();

                    String pos1=list1.get(pos).getAlbumId();

                    android.support.v4.app.FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                    GalleryParent2 frag1 = new GalleryParent2();
                    Bundle bundle = new Bundle();
                    bundle.putString("message", pos1);
                    bundle.putString("message1", name);
                    frag1.setArguments(bundle);
                    ft.replace(R.id.replace, frag1);
                    ft.addToBackStack(null);
                    ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                    ft.commit();
                }
            });

        }


        @Override
        public void onClick(View view) {


        }
    }
}
