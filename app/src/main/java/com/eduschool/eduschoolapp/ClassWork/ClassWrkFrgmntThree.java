package com.eduschool.eduschoolapp.ClassWork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClasssworkListBean;
import com.eduschool.eduschoolapp.ClswrkDetailsPOJO.ClasssworkDetailBean;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/8/2017.
 */

public class ClassWrkFrgmntThree extends Fragment {
    Toolbar toolbar;
    ProgressBar progress;
    String strtext;
    TextView subject, date, classSection, title, status, note;

    public ClassWrkFrgmntThree() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.class_wrk_details, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = ((ParentHome) getContext()).getSupportFragmentManager();
                fm.popBackStack();
            }
        });
        progress = (ProgressBar) view.findViewById(R.id.progress);
        subject = (TextView) view.findViewById(R.id.subject);
        strtext = getArguments().getString("message");
        classSection = (TextView) view.findViewById(R.id.classSection);
        date = (TextView) view.findViewById(R.id.date);
        title = (TextView) view.findViewById(R.id.title);
        status = (TextView) view.findViewById(R.id.status);
        note = (TextView) view.findViewById(R.id.note);


        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<ClasssworkDetailBean> call = cr.clss_wrk(b.school_id, b.user_id, strtext);

        Log.d("vsdvs", String.valueOf(b.user_class));
        Log.d("vsdvs", String.valueOf(b.user_section));


        call.enqueue(new Callback<ClasssworkDetailBean>() {
            @Override
            public void onResponse(Call<ClasssworkDetailBean> call, Response<ClasssworkDetailBean> response) {

                subject.setText(response.body().getClasssworkDetail().get(0).getSubjectName());
                classSection.setText(response.body().getClasssworkDetail().get(0).getClassName() + " " + response.body().getClasssworkDetail().get(0).getSectionName());

                status.setText(response.body().getClasssworkDetail().get(0).getClassworkStatus());
                title.setText(response.body().getClasssworkDetail().get(0).getClassworkTitle());
                note.setText(response.body().getClasssworkDetail().get(0).getAdditionalNote());
                date.setText(response.body().getClasssworkDetail().get(0).getPostedDate());
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ClasssworkDetailBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Class Work Details");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}
