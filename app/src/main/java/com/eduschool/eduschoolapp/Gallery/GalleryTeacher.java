package com.eduschool.eduschoolapp.Gallery;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumList;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumListBean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomewrkListbean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.provider.Settings.System.DATE_FORMAT;

/**
 * Created by user on 5/17/2017.
 */

public class GalleryTeacher extends Fragment implements DatePickerDialog.OnDateSetListener {

    Toolbar toolbar;
    Button add;
    AdapterAlbum adapter;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    ProgressBar progress;
    String date;
    List<AlbumList> list;
    boolean isSearch = false;

    public GalleryTeacher() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gallery_teacher1, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);

        list = new ArrayList<>();
        adapter = new AdapterAlbum(getActivity(), list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        //album1 = (ImageView) view.findViewById(R.id.album1);
        add = (Button) view.findViewById(R.id.add);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getActivity().getFragmentManager(), "DatePicker");



               /* Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.album_popup);
                DatePicker datePicker;

                datePicker = (DatePicker) dialog.findViewById(R.id.datePicker);

                dialog.show();
*/

            }
        });


        if (isSearch == false) {
            User b = (User) getActivity().getApplicationContext();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<AlbumListBean> call = cr.album_list(b.school_id);

            call.enqueue(new Callback<AlbumListBean>() {
                @Override
                public void onResponse(Call<AlbumListBean> call, Response<AlbumListBean> response) {


                    adapter.setGridData(response.body().getAlbumList());
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);

                    Log.d("ddddd", String.valueOf(response.body().getAlbumList().size()));
                }

                @Override
                public void onFailure(Call<AlbumListBean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(getActivity(), demo.class);
                startActivity(intent);*/

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                CreateAlbum frag1 = new CreateAlbum();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();


            }
        });

     /*   album1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                GalleryTeacher2 frag1 = new GalleryTeacher2();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });*/
        return view;

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = sdf.format(c.getTime());
        date = formattedDate;
    }

    @SuppressLint("ValidFragment")
    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            @SuppressLint("WrongConstant") int yy = calendar.get(Calendar.YEAR);
            @SuppressLint("WrongConstant") int mm = calendar.get(Calendar.MONTH);
            @SuppressLint("WrongConstant") int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }


        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = sdf.format(c.getTime());
            date = formattedDate;

            User b = (User) getActivity().getApplicationContext();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<AlbumListBean> call = cr.album_list(b.school_id, date);

            call.enqueue(new Callback<AlbumListBean>() {
                @Override
                public void onResponse(Call<AlbumListBean> call, Response<AlbumListBean> response) {


                    adapter.setGridData(response.body().getAlbumList());
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);

                    Log.d("ddddd", String.valueOf(response.body().getAlbumList().size()));

                }

                @Override
                public void onFailure(Call<AlbumListBean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });


            Log.d("svedc", date);

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Gallery");

        User u = (User) getContext().getApplicationContext();

        u.back = true;


        if (isSearch == false) {
            User b = (User) getActivity().getApplicationContext();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<AlbumListBean> call = cr.album_list(b.school_id);

            call.enqueue(new Callback<AlbumListBean>() {
                @Override
                public void onResponse(Call<AlbumListBean> call, Response<AlbumListBean> response) {


                    adapter.setGridData(response.body().getAlbumList());
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);

                    Log.d("ddddd", String.valueOf(response.body().getAlbumList().size()));
                }

                @Override
                public void onFailure(Call<AlbumListBean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });
        }

    }


}

