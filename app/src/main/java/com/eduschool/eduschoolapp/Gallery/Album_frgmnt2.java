package com.eduschool.eduschoolapp.Gallery;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.DeleteAlbumPOJO.DeleteAlbumBean;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumListBean;
import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryImage;
import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryListBean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.New;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.UpdateAlbumPOJO.UpdateAlbumBean;
import com.eduschool.eduschoolapp.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Album_frgmnt2 extends Fragment {
    Toolbar toolbar;
    String name;
    AdapterAlbumParent3 adapter;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    ProgressBar progress;
    List<GalleryImage> list;
    String albumId;
    TextView date, name1, album;

    public Album_frgmnt2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.albm_frgmnt2, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        setHasOptionsMenu(true);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        name = getArguments().getString("message1");
        albumId = getArguments().getString("message");
        //Toast.makeText(getContext(),albumId,Toast.LENGTH_SHORT).show();


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        date = (TextView) view.findViewById(R.id.date);
        album = (TextView) view.findViewById(R.id.album);
        name1 = (TextView) view.findViewById(R.id.name);
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
                name1.setText(response.body().getGalleryList().get(0).getAlbumName());
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.filter, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_filter) {



            /*Bundle bundle = new Bundle();
            bundle.putString("m", "m");
            FragmentManager fm = getFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            Update_Album_frgmnt frag1 = new Update_Album_frgmnt();
            ft.replace(R.id.replace, frag1);

            ft.addToBackStack(null);
            ft.commit();*/

            android.support.v4.app.FragmentManager fm = ((AppCompatActivity) getContext()).getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            Update_Album_frgmnt frag1 = new Update_Album_frgmnt();
            Bundle bundle = new Bundle();
            bundle.putString("message1", name);
            bundle.putString("message", albumId);
            frag1.setArguments(bundle);
            ft.replace(R.id.replace, frag1);
            ft.addToBackStack(null);
            ft.commit();


            return true;
        } else if (id == R.id.action_delete) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setCancelable(false);

            final ProgressDialog dialog1 = ProgressDialog.show(getActivity(), "",
                    "Deleting Album. Please wait...", true);

            dialog.setMessage("Are you sure you want to Delete this album ?");
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialog, int id) {

                    User b = (User) getActivity().getApplicationContext();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllAPIs cr = retrofit.create(AllAPIs.class);
                    Call<DeleteAlbumBean> call = cr.delete_album(b.school_id,albumId);

                    call.enqueue(new Callback<DeleteAlbumBean>() {
                        @Override
                        public void onResponse(Call<DeleteAlbumBean> call, Response<DeleteAlbumBean> response) {


                            if (response.body().getStatus().equals("1")){
                                Toast.makeText(getActivity(),"Album Deleted Successfully",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(),"Album did not Deleted Successfully !",Toast.LENGTH_SHORT).show();
                            }
                            //progress.setVisibility(View.GONE);
                            dialog1.dismiss();
                            dialog.dismiss();
                            getFragmentManager().popBackStack();

                        }

                        @Override
                        public void onFailure(Call<DeleteAlbumBean> call, Throwable throwable) {

                            // progress.setVisibility(View.GONE);

                        }
                    });



                }
            })
                    .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Action for "Cancel".
                            dialog1.dismiss();
                        }
                    });

            final AlertDialog alert = dialog.create();
            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle(name);
        User u = (User) getContext().getApplicationContext();
        name = getArguments().getString("message1");
        albumId = getArguments().getString("message");

        u.back = false;
    }
}
