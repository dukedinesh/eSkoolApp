package com.eduschool.eduschoolapp.HomeWork;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.HomewrkPOJO.HomewrkBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
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

public class TeacherHwFrgmntTwo extends Fragment {
    Toolbar toolbar;
    Button edit;
    ProgressBar progress;
    TextView subjectName, classSection, createDate, dueDate, title, completed, incomplete, note;
    LinearLayout complited, incomplited;
    List<String> list, complete;

    public TeacherHwFrgmntTwo() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_hw_frgmnt_two, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm= ((TeacherHome)getContext()).getSupportFragmentManager();
                fm.popBackStack();
            }
        });


        final String strtext = getArguments().getString("message");
        //Toast.makeText(getActivity(),String.valueOf(strtext),Toast.LENGTH_SHORT).show();

        complited = (LinearLayout) view.findViewById(R.id.complitedLayout);
        incomplited = (LinearLayout) view.findViewById(R.id.incompletelayout);
        subjectName = (TextView) view.findViewById(R.id.subjectName);
        classSection = (TextView) view.findViewById(R.id.classSection);
        createDate = (TextView) view.findViewById(R.id.createDate);
        dueDate = (TextView) view.findViewById(R.id.dueDate);
        title = (TextView) view.findViewById(R.id.title);
        note = (TextView) view.findViewById(R.id.note);
        completed = (TextView) view.findViewById(R.id.completed);
        incomplete = (TextView) view.findViewById(R.id.incomplete);
        edit = (Button) view.findViewById(R.id.edit);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        list = new ArrayList<>();
        complete = new ArrayList<>();


        final User b = (User) getActivity().getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);
        Call<HomewrkBean> call2 = cr.homewrk(b.school_id, strtext);
        progress.setVisibility(View.VISIBLE);


        call2.enqueue(new Callback<HomewrkBean>() {

            @Override
            public void onResponse(Call<HomewrkBean> call, Response<HomewrkBean> response) {


                subjectName.setText(response.body().getHomeworkData().get(0).getSubject());
                classSection.setText(response.body().getHomeworkData().get(0).getClass_() + " " + response.body().getHomeworkData().get(0).getSection());
                createDate.setText(response.body().getHomeworkData().get(0).getCreateDate());
                title.setText(response.body().getHomeworkData().get(0).getTitle());
                dueDate.setText("(" + response.body().getHomeworkData().get(0).getDueDate() + ")");
                note.setText(response.body().getHomeworkData().get(0).getNotes());
                completed.setText(response.body().getHomeworkData().get(0).getTotalCompletehomworkStudent().toString());
                incomplete.setText(response.body().getHomeworkData().get(0).getTotalPendinghomworkStudent().toString());

                Log.d("sdc","sdxs");

                progress.setVisibility(View.GONE);

                for (int i = 0; i < response.body().getHomeworkData().get(0).getTotalPendinghomworkStudent(); i++) {


                    list.add(response.body().getHomeworkData().get(0).getPendinghomworkStudent().get(i).getStuName());


                }

                for (int i = 0; i < response.body().getHomeworkData().get(0).getTotalCompletehomworkStudent(); i++) {

                    complete.add(response.body().getHomeworkData().get(0).getCompletehomworkStudent().get(i).getStuName());

                }



            }

            @Override
            public void onFailure(Call<HomewrkBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);

            }

        });


        incomplited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Homework Incomplete:-");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.completed_list);

                ListView listView = (ListView) dialog.findViewById(R.id.listView);
                TextView back = (TextView) dialog.findViewById(R.id.back);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, list);

                listView.setAdapter(adapter);

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }


        });


        complited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Homework Completed:-");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.completed_list);

                ListView listView = (ListView) dialog.findViewById(R.id.listView);
                TextView back = (TextView) dialog.findViewById(R.id.back);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, complete);

                // Assign adapter to ListView
                listView.setAdapter(adapter);

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }


        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherHwFrgmntThree frag1 = new TeacherHwFrgmntThree();
                Bundle bundle=new Bundle();
                bundle.putString("message", strtext);
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
        toolbar.setTitle("Home Work");
        User u = (User) getContext().getApplicationContext();
        u.back = false;




    }
}

