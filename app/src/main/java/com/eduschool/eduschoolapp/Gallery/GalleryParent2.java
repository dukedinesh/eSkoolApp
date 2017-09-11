package com.eduschool.eduschoolapp.Gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumList;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumListBean;
import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryImage;
import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryList;
import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryListBean;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.New;
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
 * Created by User on 5/12/2017.
 */

public class GalleryParent2 extends Fragment {
    Toolbar toolbar;
    AdapterAlbumParent3 adapter;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    ProgressBar progress;
    List<GalleryImage> list;
    String albumId;
    TextView date, name, album;


    public GalleryParent2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gallery_parent2, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        albumId = getArguments().getString("message");
       // Toast.makeText(getActivity(), albumId, Toast.LENGTH_SHORT).show();


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        date = (TextView) view.findViewById(R.id.date);
        album = (TextView) view.findViewById(R.id.album);
        name = (TextView) view.findViewById(R.id.name);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 3);

        list = new ArrayList<>();
        adapter = new AdapterAlbumParent3(getActivity(), list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

       /* image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), New.class);
                startActivity(intent);
            }
        });*/


        User b = (User) getActivity().getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<GalleryListBean> call = cr.gallery_img(b.school_id, albumId, b.user_id);


        call.enqueue(new Callback<GalleryListBean>() {
            @Override
            public void onResponse(Call<GalleryListBean> call, Response<GalleryListBean> response) {


                date.setText(response.body().getGalleryList().get(0).getCreateDate());
                album.setText(response.body().getGalleryList().get(0).getAlbumName());
                name.setText(response.body().getGalleryList().get(0).getAlbumName());
                adapter.setGridData(response.body().getGalleryList().get(0).getGalleryImage());
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);
                Log.d("sdvs","sdvsdv");

                //Log.d("ddddd", String.valueOf(response.body().getAlbumList().size()));
            }

            @Override
            public void onFailure(Call<GalleryListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Gallery");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}
