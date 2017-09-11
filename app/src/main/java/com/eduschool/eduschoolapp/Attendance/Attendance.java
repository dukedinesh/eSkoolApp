package com.eduschool.eduschoolapp.Attendance;


import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.AttendanceSummaryPOJO.AttendanceSummary;
import com.eduschool.eduschoolapp.AttendanceSummaryPOJO.AttendanceSummaryBean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Attendance extends Fragment {
    Button mark_attendance, edit_attendance, view_attendance;
    Toolbar toolbar;
    ProgressBar progress;
    CardView cardView;
    String Sdate;
    DrawerLayout drawer;
    TextView absent,present,total,leave;


    public Attendance() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_attendance, container, false);
        drawer = (DrawerLayout) ((TeacherHome) getContext()).findViewById(R.id.drawer_asiana);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        mark_attendance = (Button) view.findViewById(R.id.mark_attendance);
        view_attendance = (Button) view.findViewById(R.id.view_attendance);
        edit_attendance = (Button) view.findViewById(R.id.edit_attendance);
        absent=(TextView)view.findViewById(R.id.absent);
        present=(TextView)view.findViewById(R.id.present);
        leave=(TextView)view.findViewById(R.id.leave);
        total=(TextView)view.findViewById(R.id.total);
        cardView = (CardView) view.findViewById(R.id.cardView);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        mark_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MrkAttndnceFrgmnt2 frag1 = new MrkAttndnceFrgmnt2();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        edit_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                UpdateAttendncFrgmnt2 frag1 = new UpdateAttendncFrgmnt2();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        view_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                AttendanceFrgmnt2 frag1 = new AttendanceFrgmnt2();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        final DateFormat month = new SimpleDateFormat("dd-MMM-yyyy");
        final Date month1 = new Date();

        Sdate = month.format(month1);

        cardView.setVisibility(View.GONE);
        final User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);
        Call<AttendanceSummaryBean> call3 = cr.attendnc_summry(b.school_id, b.user_id, Sdate);

        progress.setVisibility(View.VISIBLE);


        call3.enqueue(new Callback<AttendanceSummaryBean>() {

            @Override
            public void onResponse(Call<AttendanceSummaryBean> call3, Response<AttendanceSummaryBean> response) {


                if (response.body().getAttendanceSummary().size()>0){
                    cardView.setVisibility(View.VISIBLE);
                    absent.setText(String.valueOf(response.body().getAttendanceSummary().get(0).getTotalAbsentStu()));
                    present.setText(String.valueOf(response.body().getAttendanceSummary().get(0).getTotalPresentStu()));
                    total.setText(String.valueOf(response.body().getAttendanceSummary().get(0).getTotalStudent()));
                    leave.setText(String.valueOf(response.body().getAttendanceSummary().get(0).getTotalLeaveStu()));
                }
                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<AttendanceSummaryBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);

            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        toolbar.setTitle("Attendance");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}