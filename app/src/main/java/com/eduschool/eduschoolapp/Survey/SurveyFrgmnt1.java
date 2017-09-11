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
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassWrkListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SurveyListPOJO.SurveyListBean;
import com.eduschool.eduschoolapp.SurveyListPOJO.SurveyListteacher;
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

public class SurveyFrgmnt1 extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterSurvey adapter;
    private List<SurveyListteacher> albumList;
    ProgressBar progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.survey_frgmnt1, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        progress=(ProgressBar)view.findViewById(R.id.progress);



        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        albumList = new ArrayList<>();
        adapter = new AdapterSurvey(getContext(), albumList);

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

        Call<SurveyListBean> call = cr.survey_list(b.school_id,"Teacher", b.user_id);

        call.enqueue(new Callback<SurveyListBean>() {
            @Override
            public void onResponse(Call<SurveyListBean> call, Response<SurveyListBean> response) {


                adapter.setGridData(response.body().getSurveyListteacher());
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<SurveyListBean> call, Throwable throwable) {

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

        Call<SurveyListBean> call = cr.survey_list(b.school_id,"Teacher", b.user_id);

        call.enqueue(new Callback<SurveyListBean>() {
            @Override
            public void onResponse(Call<SurveyListBean> call, Response<SurveyListBean> response) {


                adapter.setGridData(response.body().getSurveyListteacher());
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<SurveyListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


    }
}
