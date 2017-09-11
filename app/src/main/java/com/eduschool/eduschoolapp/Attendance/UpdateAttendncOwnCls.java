package com.eduschool.eduschoolapp.Attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceDatum;
import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceListBean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 8/22/2017.
 */

public class UpdateAttendncOwnCls extends Fragment {
    Toolbar toolbar;
    ProgressBar progress;
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    AdapterViewAttndnc adapter;
    public List<String> studentlist,studentId;
    public List<AttendanceDatum> list;
    String Sdate,month,year,day,attendance_id;
    TextView Tday,classSection,Tmonth;
    Button submit;
    List<String>s;
    String ss;
    String[] separated;
    FloatingActionButton button;
    public UpdateAttendncOwnCls() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.update_own_cls_attndnc, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        recyclerView = (RecyclerView)view. findViewById(R.id.recycler);
        progress = (ProgressBar)view. findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);
        submit=(Button)view.findViewById(R.id.submit);
        Tday=(TextView)view.findViewById(R.id.day);
        classSection=(TextView)view.findViewById(R.id.classSection);
        Tmonth=(TextView)view.findViewById(R.id.month);
        button=(FloatingActionButton)view.findViewById(R.id.fab);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);
        Sdate = getArguments().getString("message");




        final DateFormat month =  new SimpleDateFormat("MMM");
        final Date month1 = new Date();

        final DateFormat year =  new SimpleDateFormat("yyyy");
        final Date year1 = new Date();


        final DateFormat day =  new SimpleDateFormat("dd");
        final Date day1 = new Date();

        Log.d("sds",month.format(month1));
        Log.d("sds",year.format(year1));
        Log.d("sds",day.format(day1));

        ss=Sdate;
        separated = ss.split("-");
        Tday.setText(separated[0]);
        Tmonth.setText(separated[1] + " " + separated[2]);



        list = new ArrayList<>();
        s=new ArrayList<>();

        adapter = new AdapterViewAttndnc(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        studentlist = new ArrayList<>();
        list = new ArrayList<>();
        studentId = new ArrayList<>();





        User b = (User) getActivity().getApplicationContext();
        classSection.setText(b.class_Name+" "+b.section_Name);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<AttendanceListBean> call = cr.attnce_list(b.school_id,Sdate, b.user_class, b.user_section);


        call.enqueue(new Callback<AttendanceListBean>() {
            @Override
            public void onResponse(Call<AttendanceListBean> call, Response<AttendanceListBean> response) {


                attendance_id=response.body().getAttendanceList().get(0).getId();
                adapter.setGridData(response.body().getAttendanceList().get(0).getAttendanceData());
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<AttendanceListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(getActivity(),UpdateOwnCls.class);
                intent.putExtra("Id",attendance_id);
                startActivity(intent);

            }
        });



        return view;

    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Attendance");
        User u = (User) getContext().getApplicationContext();

        u.back = false;

        User b = (User) getActivity().getApplicationContext();
        classSection.setText(b.class_Name+" "+b.section_Name);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<AttendanceListBean> call = cr.attnce_list(b.school_id,Sdate, b.user_class, b.user_section);


        call.enqueue(new Callback<AttendanceListBean>() {
            @Override
            public void onResponse(Call<AttendanceListBean> call, Response<AttendanceListBean> response) {


                attendance_id=response.body().getAttendanceList().get(0).getId();
                adapter.setGridData(response.body().getAttendanceList().get(0).getAttendanceData());
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<AttendanceListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });
    }
}


