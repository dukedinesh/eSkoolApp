package com.eduschool.eduschoolapp.ExamAndResults;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Attendance.ViewDffClsfrgmnt;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassList;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionList;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
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
 * Created by user on 5/18/2017.
 */

public class TeacherFrgmnt2 extends Fragment {
    TextView own_class, diff_class;
    Toolbar toolbar;
    String id;
    List<ClassList> list;
    List<String> classlist, sectionlist;
    List<SectionList> listSection;
    List<String> classId, sectionid;
    boolean isFirst;
    String ClassName, classId1, SectionName, sectionId1;

    public TeacherFrgmnt2() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_frgmnt2, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = ((TeacherHome) getContext()).getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        own_class = (TextView) view.findViewById(R.id.own_class);
        diff_class = (TextView) view.findViewById(R.id.diff_class);
        id = getArguments().getString("message");

        list = new ArrayList<>();
        classlist = new ArrayList<>();
        classId = new ArrayList<>();

        listSection = new ArrayList<>();
        sectionlist = new ArrayList<>();
        sectionid = new ArrayList<>();

        User b = (User) getActivity().getApplicationContext();

        own_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherOwnCls frag1 = new TeacherOwnCls();
                Bundle bundle = new Bundle();
                bundle.putString("message", id);
                frag1.setArguments(bundle);
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        diff_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.attendance_diff_class_dialog);
                Button submit = (Button) dialog.findViewById(R.id.submit);
                final Spinner className = (Spinner) dialog.findViewById(R.id.className);
                final Spinner sectionName = (Spinner) dialog.findViewById(R.id.sectionName);
                final ProgressBar progress = (ProgressBar) dialog.findViewById(R.id.progress);


                final User b = (User) getActivity().getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);

                Call<ClassListbean> call = cr.classList(b.school_id);
                progress.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<ClassListbean>() {
                    @Override
                    public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {


                        list = response.body().getClassList();

                        classlist.clear();
                        classId.clear();
                        classlist.add("Select Class");
                        sectionlist.add("Select Section");

                        for (int i = 0; i < list.size(); i++) {

                            classlist.add(list.get(i).getClassName());
                            classId.add(list.get(i).getClassId());


                        }

                        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, classlist);
                        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        className.setAdapter(adp1);


                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                        progress.setVisibility(View.GONE);

                    }
                });


                className.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                        isFirst = true;

                        ClassName = adapterView.getItemAtPosition(i).toString();

                        if (i > 0) {

                            isFirst = false;
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);


                            Call<SectionListbean> call2 = cr.sectionList(b.school_id, classId.get(i - 1));

                            progress.setVisibility(View.VISIBLE);


                            call2.enqueue(new Callback<SectionListbean>() {

                                @Override
                                public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                                    listSection = response.body().getSectionList();
                                    sectionlist.clear();
                                    sectionid.clear();


                                    for (int i = 0; i < response.body().getSectionList().size(); i++) {

                                        sectionlist.add(response.body().getSectionList().get(i).getSectionName());

                                        sectionid.add(response.body().getSectionList().get(i).getSectionId());

                                    }


                                    ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                            android.R.layout.simple_list_item_1, sectionlist);

                                    adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    sectionName.setAdapter(adp);

                                    classId1 = classId.get(i - 1);
                                    Log.d("sdsdsss", classId1);


                                    progress.setVisibility(View.GONE);


                                }

                                @Override
                                public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                                    progress.setVisibility(View.GONE);

                                }
                            });


                        } else {
                            isFirst = true;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                sectionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                        SectionName = adapterView.getItemAtPosition(i).toString();
                        sectionId1 = sectionid.get(i);


                        Log.d("pooja", sectionId1);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (isFirst == true) {
                            Toast.makeText(getActivity(), "Select Class And Section. ", Toast.LENGTH_SHORT).show();
                        } else {

                            dialog.dismiss();

                            android.support.v4.app.FragmentManager fm = getFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            TeacherDffClass frag1 = new TeacherDffClass();
                            Bundle bundle = new Bundle();
                            bundle.putString("Class", ClassName);
                            bundle.putString("Section", SectionName);
                            bundle.putString("SectionId", sectionId1);
                            bundle.putString("ClassId", classId1);
                            bundle.putString("message", id);

                            frag1.setArguments(bundle);
                            ft.replace(R.id.replace, frag1);
                            ft.addToBackStack(null);
                            ft.commit();


                        }

                    }
                });


                dialog.show();

            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Select Class");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}
