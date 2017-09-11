package com.eduschool.eduschoolapp.OnlineTest;


import android.content.Intent;
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
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.OnlineTestListPOJO.OnlineTestListBean;
import com.eduschool.eduschoolapp.OnlineTestListPOJO.OnlinetestList;
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

public class OnlineTestSummery extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    ProgressBar progress;
    private AdapterOnlineTestList adapter;
    private List<OnlinetestList> list;


    public OnlineTestSummery() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_online_test_summery, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
       /* take_test = (TextView) view.findViewById(R.id.take_test);
        take_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OnlineTestActivity.class);
                startActivity(intent);
            }
        });*/

        progress = (ProgressBar) view.findViewById(R.id.progress);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        list = new ArrayList<>();
        adapter = new AdapterOnlineTestList(getContext(), list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);

        final User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<OnlineTestListBean> call = cr.online_test_bean(b.school_id,b.user_class,b.user_section,b.user_id);


        call.enqueue(new Callback<OnlineTestListBean>() {
            @Override
            public void onResponse(Call<OnlineTestListBean> call, Response<OnlineTestListBean> response) {


                list = response.body().getOnlinetestList();
                adapter.setGridData(list);
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<OnlineTestListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });




        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Online Test");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}

