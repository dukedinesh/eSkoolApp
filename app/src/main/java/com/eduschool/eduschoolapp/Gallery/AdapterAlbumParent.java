package com.eduschool.eduschoolapp.Gallery;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumList;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.CreatedDate;
import com.eduschool.eduschoolapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/16/2017.
 */

public class AdapterAlbumParent extends RecyclerView.Adapter<AdapterAlbumParent.myviewholder> {

    Context context;
    List<AlbumList> list = new ArrayList<>();

    public AdapterAlbumParent(Context context, List<AlbumList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<AlbumList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterAlbumParent.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.album_model, parent, false);

        return new AdapterAlbumParent.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterAlbumParent.myviewholder holder, int position) {

        AlbumList item = list.get(position);


        holder.date.setText(item.getAlbumCreateDate());
        //


        holder.adapter1.setGridData(item.getCreatedDate());
        //adapter1.notifyDataSetChanged();

        //

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView date;
        RecyclerView recyclerView;
        GridLayoutManager manager;
        AdapterAlbumPaent2 adapter1;
        List<CreatedDate> list1;


        public myviewholder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler);
            manager = new GridLayoutManager(context, 3);
            list1=new ArrayList<>();

            adapter1 = new AdapterAlbumPaent2(context, list1);

            recyclerView.setAdapter(adapter1);
            recyclerView.setLayoutManager(manager);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   // String name=list.get(getAdapterPosition()).getCreatedDate().get();



                }
            });





        }


        @Override
        public void onClick(View view) {


        }
    }
}
