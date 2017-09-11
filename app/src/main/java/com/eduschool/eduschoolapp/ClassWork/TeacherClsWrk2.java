package com.eduschool.eduschoolapp.ClassWork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassWrkListbean;
import com.eduschool.eduschoolapp.ClssWrkPOJO.ClasswrkBean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 5/19/2017.
 */

public class TeacherClsWrk2 extends Fragment {
    Toolbar toolbar;
    Button edit;
    ProgressBar progress;
    String Id;
    TextView subject, classSection, date, title, status,note;

    public TeacherClsWrk2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_cls_wrk2, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm= ((TeacherHome)getContext()).getSupportFragmentManager();
                fm.popBackStack();
            }
        });
        edit = (Button) view.findViewById(R.id.edit);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        String strtext = getArguments().getString("message");
        //Toast.makeText(getActivity(),String.valueOf(strtext),Toast.LENGTH_SHORT).show();

        subject = (TextView) view.findViewById(R.id.subject);
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

        Call<ClasswrkBean> call = cr.clss_wrk(b.school_id, strtext);

        call.enqueue(new Callback<ClasswrkBean>() {
            @Override
            public void onResponse(Call<ClasswrkBean> call, Response<ClasswrkBean> response) {

                subject.setText(response.body().getClassworkData().get(0).getSubject());
                classSection.setText(response.body().getClassworkData().get(0).getClass_() + " " + response.body().getClassworkData().get(0).getSection());
                date.setText(response.body().getClassworkData().get(0).getCreateDate());
                title.setText(response.body().getClassworkData().get(0).getTitle());
                status.setText(response.body().getClassworkData().get(0).getClassStatus());
                note.setText(response.body().getClassworkData().get(0).getNotes());
                Id=response.body().getClassworkData().get(0).getClassworkId();

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ClasswrkBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherClsWrk3 frag1 = new TeacherClsWrk3();
                Bundle bundle=new Bundle();
                bundle.putString("message", Id);
                frag1.setArguments(bundle);
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        return view;

    }


    @Override
    public void onResume() {

        super.onResume();
        toolbar.setTitle("Class Work");

        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}
