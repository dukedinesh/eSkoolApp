package com.eduschool.eduschoolapp.Profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWrkParentPOJO.ClasssubjectListBean;
import com.eduschool.eduschoolapp.ParentContactPOJO.ParentContactBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/4/2017.
 */

public class ParentFragmentOne extends Fragment {

    TextView name,email,phone,tempAdd,perAdd;
    ProgressBar progress;
    public ParentFragmentOne() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.parent_profile_frgmnt1, container, false);

        name=(TextView)v.findViewById(R.id.contact_name);
        email=(TextView)v.findViewById(R.id.email);
        phone=(TextView)v.findViewById(R.id.phone);
        tempAdd=(TextView)v.findViewById(R.id.tmpAdd);
        perAdd=(TextView)v.findViewById(R.id.perAdd);
        progress=(ProgressBar) v.findViewById(R.id.progress);


        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<ParentContactBean> call = cr.parent_contact(b.school_id, b.user_id);


        call.enqueue(new Callback<ParentContactBean>() {
            @Override
            public void onResponse(Call<ParentContactBean> call, Response<ParentContactBean> response) {

                name.setText(response.body().getContactName());
                email.setText(response.body().getContactEmail());
                phone.setText(response.body().getContactPhone());
                tempAdd.setText(response.body().getContactTempaddress());
                perAdd.setText(response.body().getContactPermaaddress());


            }

            @Override
            public void onFailure(Call<ParentContactBean> call, Throwable throwable) {



            }
        });




        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}

