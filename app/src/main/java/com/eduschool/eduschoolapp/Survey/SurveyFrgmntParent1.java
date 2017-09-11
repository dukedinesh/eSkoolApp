package com.eduschool.eduschoolapp.Survey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SurveyListParentPOJO.ServeyDatum;
import com.eduschool.eduschoolapp.SurveyListParentPOJO.SurveyListBeanParent;
import com.eduschool.eduschoolapp.SurveyListParentPOJO.SurveyListteacher;
import com.eduschool.eduschoolapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/15/2017.
 */

public class SurveyFrgmntParent1 extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterSurveyParent adapter;
    ProgressBar progress;
    List<SurveyListteacher> albumList;
    List<ServeyDatum> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.survey_frgmnt1, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        progress = (ProgressBar) view.findViewById(R.id.progress);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        albumList = new ArrayList<>();
        list = new ArrayList<>();
        adapter = new AdapterSurveyParent(getContext(), list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);

        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<SurveyListBeanParent> call = cr.survey_list1(b.school_id, "Parent", b.user_id, b.user_class, b.user_section);

        call.enqueue(new Callback<SurveyListBeanParent>() {
            @Override
            public void onResponse(Call<SurveyListBeanParent> call, Response<SurveyListBeanParent> response) {


                list.clear();
                for (int i = 0; i < response.body().getSurveyListteacher().size(); i++) {

                    for (int j = 0; j < response.body().getSurveyListteacher().get(i).getServeyData().size(); j++) {

                        list.add(response.body().getSurveyListteacher().get(i).getServeyData().get(j));


                    }
                }

                adapter.setGridData(list);
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<SurveyListBeanParent> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Survey");
        User u = (User) getContext().getApplicationContext();

        u.back = true;

        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<SurveyListBeanParent> call = cr.survey_list1(b.school_id, "Parent", b.user_id, b.user_class, b.user_section);

        call.enqueue(new Callback<SurveyListBeanParent>() {
            @Override
            public void onResponse(Call<SurveyListBeanParent> call, Response<SurveyListBeanParent> response) {
                list.clear();
                for (int i = 0; i < response.body().getSurveyListteacher().size(); i++) {

                    for (int j = 0; j < response.body().getSurveyListteacher().get(i).getServeyData().size(); j++) {

                        list.add(response.body().getSurveyListteacher().get(i).getServeyData().get(j));

                    }
                }

                adapter.setGridData(list);
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<SurveyListBeanParent> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


    }
}

