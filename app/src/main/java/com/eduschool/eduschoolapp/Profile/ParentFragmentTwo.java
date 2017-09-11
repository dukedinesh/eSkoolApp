package com.eduschool.eduschoolapp.Profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ParentContactPOJO.ParentContactBean;
import com.eduschool.eduschoolapp.ParentInfoPOJO.ParentInfoBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/5/2017.
 */

public class ParentFragmentTwo extends Fragment {

    TextView className,section,gender,rollNo,adms,category,bdy,bloodgrp;
    public ParentFragmentTwo() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.parent_profile_frgmnt2, container, false);

        className=(TextView)v.findViewById(R.id.className);
        section=(TextView)v.findViewById(R.id.section);
        gender=(TextView)v.findViewById(R.id.gender);
        rollNo=(TextView)v.findViewById(R.id.rollNo);
        adms=(TextView)v.findViewById(R.id.admissonNo);
        category=(TextView)v.findViewById(R.id.category);
        bdy=(TextView)v.findViewById(R.id.bdy);
        bloodgrp=(TextView)v.findViewById(R.id.bloodGrp);

        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<ParentInfoBean> call = cr.parent_info(b.school_id, b.user_id);


        call.enqueue(new Callback<ParentInfoBean>() {
            @Override
            public void onResponse(Call<ParentInfoBean> call, Response<ParentInfoBean> response) {

                className.setText(response.body().getClass_());
                section.setText(response.body().getSection());
                gender.setText(response.body().getGender());
                rollNo.setText(response.body().getRollNo());
                adms.setText(response.body().getAdmissionNo());
                category.setText(response.body().getCategory());
                bdy.setText(response.body().getBirthDate());
                bloodgrp.setText(response.body().getBloodGroup());


            }

            @Override
            public void onFailure(Call<ParentInfoBean> call, Throwable throwable) {

            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
