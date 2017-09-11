package com.eduschool.eduschoolapp.RaiseRequest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWork.AdapterClsWrkList;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassWrkListbean;
import com.eduschool.eduschoolapp.HomeWork.AdapterHwList;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SendParentRequstPOJO.RequestList;
import com.eduschool.eduschoolapp.SendParentRequstPOJO.RequestListBean;
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
 * Created by User on 5/10/2017.
 */

public class FrgmntTwo extends Fragment{
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    AdapterTwo adapter;
    ProgressBar progress;
    List<RequestList> list;
    public FrgmntTwo() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.raise_request_frgnt2, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);

        list = new ArrayList<>();

        adapter = new AdapterTwo(getContext(), list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        final User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<RequestListBean> call = cr.request_list(b.school_id, b.user_class,b.user_section,b.user_id,b.user_type);

        call.enqueue(new Callback<RequestListBean>() {
            @Override
            public void onResponse(Call<RequestListBean> call, Response<RequestListBean> response) {

                Log.d("asjcbjs",String.valueOf(b.user_id));
                Log.d("asjcbjs",String.valueOf(b.user_class));
                Log.d("asjcbjs",String.valueOf(b.user_section));
                Log.d("asjcbjs",String.valueOf(b.user_id));
                Log.d("asjcbjs",String.valueOf(b.user_type));
                list=response.body().getRequestList();
                Log.d("sizzz",String.valueOf(list.size()));

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