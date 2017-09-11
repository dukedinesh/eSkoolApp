package com.eduschool.eduschoolapp.Gallery;

import android.content.Context;
import android.os.Bundle;
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


public class AdapterAlbum2 extends RecyclerView.Adapter<AdapterAlbum2.myviewholder> {

    Context context;
    List<CreatedDate> list1 = new ArrayList<>();


    public AdapterAlbum2(Context context, List<CreatedDate> list) {
        this.context = context;
        this.list1 = list;
    }

    public void setGridData(List<CreatedDate> list) {
        this.list1 = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterAlbum2.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.album_list_model, parent, false);

        return new AdapterAlbum2.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterAlbum2.myviewholder holder, int position) {

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
                    Album_frgmnt2 frag1 = new Album_frgmnt2();
                    Bundle bundle = new Bundle();
                    bundle.putString("message", pos1);
                    bundle.putString("message1", name);
                    frag1.setArguments(bundle);
                    ft.replace(R.id.replace, frag1);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });

        }


        @Override
        public void onClick(View view) {


        }
    }
}
