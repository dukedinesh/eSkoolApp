package com.eduschool.eduschoolapp.HomeWork;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.HomeWrkDetailsPOJO.HomeWrkDetailsBean;
import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeWorkListBean;
import com.eduschool.eduschoolapp.HomeWrkUpdPOJO.HomeworkUpdateBean;
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

public class HomeWrkFrgmntThree extends Fragment {
    Toolbar toolbar;
    Button btn_complted;
    ProgressBar progress;
    TextView subject, date, classSection, title, note, dueDate;

    public HomeWrkFrgmntThree() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_wrk_details, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        final String strtext = getArguments().getString("message");
        subject = (TextView) view.findViewById(R.id.subject);
        date = (TextView) view.findViewById(R.id.date);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        classSection = (TextView) view.findViewById(R.id.classSection);
        title = (TextView) view.findViewById(R.id.title);
        note = (TextView) view.findViewById(R.id.note);
        dueDate = (TextView) view.findViewById(R.id.due_date);


        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<HomeWrkDetailsBean> call = cr.hw_details(b.school_id, b.user_id, strtext);


        call.enqueue(new Callback<HomeWrkDetailsBean>() {
            @Override
            public void onResponse(Call<HomeWrkDetailsBean> call, Response<HomeWrkDetailsBean> response) {

                subject.setText(response.body().getHomeworkDetail().get(0).getSubjectName());
                classSection.setText(response.body().getHomeworkDetail().get(0).getClassName() + " " + response.body().getHomeworkDetail().get(0).getSectionName());
                title.setText(response.body().getHomeworkDetail().get(0).getHomeworkTitle());
                note.setText(response.body().getHomeworkDetail().get(0).getAdditionalNote());
                dueDate.setText("("+response.body().getHomeworkDetail().get(0).getDueDate()+")");
                date.setText(response.body().getHomeworkDetail().get(0).getPostedDate());

                if (response.body().getHomeworkDetail().get(0).getHomeworkStatus().equals("Complete")){
                    btn_complted.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                    btn_complted.setTextColor(ContextCompat.getColor(getContext(), R.color.txtColor));
                    btn_complted.setEnabled(false);
                }
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<HomeWrkDetailsBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        btn_complted = (Button) view.findViewById(R.id.btn_complted);
        btn_complted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure want to Submit?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        User b = (User) getActivity().getApplicationContext();


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);
                        progress.setVisibility(View.VISIBLE);

                        Call<HomeworkUpdateBean> call = cr.hw_update(b.user_id, strtext);


                        call.enqueue(new Callback<HomeworkUpdateBean>() {
                            @Override
                            public void onResponse(Call<HomeworkUpdateBean> call, Response<HomeworkUpdateBean> response) {


                                btn_complted.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                                btn_complted.setTextColor(ContextCompat.getColor(getContext(), R.color.txtColor));
                                btn_complted.setEnabled(false);

                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<HomeworkUpdateBean> call, Throwable throwable) {

                                progress.setVisibility(View.GONE);

                            }
                        });

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home Work Details");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}
