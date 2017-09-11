package com.eduschool.eduschoolapp.Communication;

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

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassWrkListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.RequestListPOJO.RequestList;
import com.eduschool.eduschoolapp.RequestListPOJO.RequestListBean;
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
 * Created by user on 5/25/2017.
 */

public class One extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    ProgressBar progress;
    List<RequestList> list;
    AdapterRecieve adapter;

    public One() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.communication_one, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);

        list = new ArrayList<>();



        adapter = new AdapterRecieve(getContext(), list);

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

        Call<RequestListBean> call = cr.request_list1(b.school_id, b.user_id,b.user_type);

        call.enqueue(new Callback<RequestListBean>() {
            @Override
            public void onResponse(Call<RequestListBean> call, Response<RequestListBean> response) {


                adapter.setGridData(response.body().getRequestList());
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<RequestListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        return view;
    }
}