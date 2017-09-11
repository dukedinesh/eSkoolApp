package com.eduschool.eduschoolapp.Gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryImage;
import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryList;
import com.eduschool.eduschoolapp.New;
import com.eduschool.eduschoolapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/16/2017.
 */

public class AdapterAlbumParent3 extends RecyclerView.Adapter<AdapterAlbumParent3.myviewholder> {

    Context context;
    List<GalleryImage> list = new ArrayList<>();

    public AdapterAlbumParent3(Context context, List<GalleryImage> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<GalleryImage> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterAlbumParent3.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.gallery_parent_model2, parent, false);

        return new AdapterAlbumParent3.myviewholder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterAlbumParent3.myviewholder holder, int position) {

        GalleryImage item = list.get(position);

        /*ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(item.getImae(),holder.img);*/

        Picasso.with(context).load(item.getImae()).into(holder.img);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView img;

        public myviewholder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name = list.get(getAdapterPosition()).getId();


                    Intent intent = new Intent(context, New.class);
                    intent.putExtra("position", name);
                }
            });


        }


        @Override
        public void onClick(View view) {


        }
    }
}

