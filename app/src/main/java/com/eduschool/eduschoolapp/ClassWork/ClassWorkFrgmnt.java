package com.eduschool.eduschoolapp.ClassWork;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWrkParentPOJO.ClasssubjectListBean;
import com.eduschool.eduschoolapp.ClassWrkParentPOJO.SubjectList;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.HomeWork.AdapterParent;

import com.eduschool.eduschoolapp.HomewrkParentPOJO.ParentSubjectListBean;
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
 * Created by User on 5/8/2017.
 */

public class ClassWorkFrgmnt extends Fragment {


    Toolbar toolbar;
    ProgressBar progress;
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    List<SubjectList> list;
    AdapterCwParent adapter;
    DrawerLayout drawer;

    TextView name, classSection;

    public ClassWorkFrgmnt() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_cw_home, container, false);
        drawer = (DrawerLayout) ((ParentHome) getContext()).findViewById(R.id.drawer_asiana);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);
        name = (TextView) view.findViewById(R.id.name);
        classSection = (TextView) view.findViewById(R.id.classection);

        list = new ArrayList<>();

        adapter = new AdapterCwParent(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<ClasssubjectListBean> call = cr.class_subject(b.school_id, b.user_class, b.user_section, b.user_id);




        call.enqueue(new Callback<ClasssubjectListBean>() {
            @Override
            public void onResponse(Call<ClasssubjectListBean> call, Response<ClasssubjectListBean> response) {

                name.setText(response.body().getClasssubjectList().get(0).getStudentName());
                classSection.setText(response.body().getClasssubjectList().get(0).getClassName()+" "+response.body().getClasssubjectList().get(0).getSectionName());

                Log.d("dd",response.body().getClasssubjectList().get(0).getClassName());
                adapter.setGridData(response.body().getClasssubjectList().get(0).getSubjectList());
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ClasssubjectListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });





        return view;

    }

    @Override
    public void onResume() {

        super.onResume();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setTitle("Class Work");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}