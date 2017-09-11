package com.eduschool.eduschoolapp.Gallery;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumList;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumListBean;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.CreatedDate;
import com.eduschool.eduschoolapp.HomeWork.AdapterHwList;
import com.eduschool.eduschoolapp.HomeWork.TeacherHwFrgmntTwo;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 8/5/2017.
 */

public class AdapterAlbum extends RecyclerView.Adapter<AdapterAlbum.myviewholder> {

    Context context;
    List<AlbumList> list = new ArrayList<>();

    public AdapterAlbum(Context context, List<AlbumList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<AlbumList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterAlbum.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.album_model, parent, false);

        return new AdapterAlbum.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterAlbum.myviewholder holder, int position) {

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
        AdapterAlbum2 adapter1;
        List<CreatedDate> list1;


        public myviewholder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler);
            manager = new GridLayoutManager(context, 3);
            list1=new ArrayList<>();

            adapter1 = new AdapterAlbum2(context, list1);

            recyclerView.setAdapter(adapter1);
            recyclerView.setLayoutManager(manager);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //String name=list.get(getAdapterPosition()).getAlbumName();



                }
            });





        }


        @Override
        public void onClick(View view) {


        }
    }
}
