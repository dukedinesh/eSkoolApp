package com.eduschool.eduschoolapp.SyllabusManagement;

import android.graphics.Color;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWork.AdapterCwParent;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectList;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.SyllabusPOJO.SyllabusList;
import com.eduschool.eduschoolapp.SyllabusPOJO.SyllabusListBean;
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

public class ParentAcademic extends Fragment {
    Toolbar toolbar;
    ProgressBar progress;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    public List<SubjectList> listSubject;
    public List<SyllabusList> list;
    AdapterParent adapter;
    public List<String> subjectlist, subjectId;
    Spinner subject;
    String subjectID;


    public ParentAcademic() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_academic, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        subject = (Spinner) view.findViewById(R.id.subject);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);

        listSubject = new ArrayList<>();
        list = new ArrayList<>();

        adapter = new AdapterParent(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        subjectlist = new ArrayList<>();
        listSubject = new ArrayList<>();
        subjectId = new ArrayList<>();


        final User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);


        Call<SubjectListBean> call1 = cr.subjectList(b.school_id, b.user_class, b.user_section);

        progress.setVisibility(View.VISIBLE);

        call1.enqueue(new Callback<SubjectListBean>() {

            @Override
            public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {

                listSubject = response.body().getSubjectList();
                subjectlist.clear();
                subjectId.clear();


                for (int i = 0; i < response.body().getSubjectList().size(); i++) {

                    subjectlist.add(response.body().getSubjectList().get(i).getSubjectName());
                    subjectId.add(response.body().getSubjectList().get(i).getSubjectId());
                }

                ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, subjectlist);

                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subject.setAdapter(adp1);
                adp1.notifyDataSetChanged();

                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);

            }
        });


        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                subjectID = subjectId.get(i);

                Log.d("iddd",subjectID);

               Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);


                Call<SyllabusListBean> call1 = cr.syllabus_mng(b.school_id,subjectID, b.user_class, b.user_section);

                progress.setVisibility(View.VISIBLE);

                call1.enqueue(new Callback<SyllabusListBean>() {

                    @Override
                    public void onResponse(Call<SyllabusListBean> call, Response<SyllabusListBean> response) {

                        adapter.setGridData(response.body().getSyllabusList());
                        adapter.notifyDataSetChanged();
                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<SyllabusListBean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("My Progress");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}