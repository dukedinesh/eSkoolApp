package com.eduschool.eduschoolapp.SyllabusManagement;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.eduschool.eduschoolapp.ClassListPOJO.ClassList;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionList;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectList;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.SyllabusTeacherPOJO.SyllabusList;
import com.eduschool.eduschoolapp.SyllabusTeacherPOJO.SyllabusListBean;
import com.eduschool.eduschoolapp.SyllbsMngTeacherPOJO.ExamType;
import com.eduschool.eduschoolapp.SyllbsMngTeacherPOJO.SyllabusListBean1;
import com.eduschool.eduschoolapp.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.eduschool.eduschoolapp.R.id.subject;

/**
 * Created by user on 5/16/2017.
 */

public class TeacherAcademic extends Fragment {
    TextView change, classSection, subject;
    Toolbar toolbar;
    ProgressBar progress1;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    public List<SubjectList> listSubject;
    public List<ExamType> list;
    AdapterTeacher adapter;
    boolean isFirst;
    String ClassName, classId1, SectionName, sectionId1, subjectId1;

    public List<String> subjectlist, subjectId;

    List<ClassList> listClass;

    List<String> classlist, sectionlist;
    List<SectionList> listSection;
    List<String> classId, sectionid;

    public TeacherAcademic() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.teacher_academic, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        change = (TextView) view.findViewById(R.id.change);
        classSection = (TextView) view.findViewById(R.id.classSection);
        subject = (TextView) view.findViewById(R.id.subject);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress1 = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);

        listSubject = new ArrayList<>();
        list = new ArrayList<>();

        listClass = new ArrayList<>();
        classlist = new ArrayList<>();
        classId = new ArrayList<>();

        listSection = new ArrayList<>();
        sectionlist = new ArrayList<>();
        sectionid = new ArrayList<>();

        adapter = new AdapterTeacher(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        subjectlist = new ArrayList<>();
        listSubject = new ArrayList<>();
        subjectId = new ArrayList<>();

        final User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("sdfcd", "qqqqqq");
                final Dialog dialog1 = new Dialog(getActivity());
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.setContentView(R.layout.academic_popup);
                dialog1.show();


                final Button submit1 = (Button) dialog1.findViewById(R.id.submit);
                final Spinner className = (Spinner) dialog1.findViewById(R.id.className);
                final Spinner sectionName = (Spinner) dialog1.findViewById(R.id.sectionName);
                final Spinner subjectName = (Spinner) dialog1.findViewById(R.id.subject);
                final ProgressBar progress = (ProgressBar) dialog1.findViewById(R.id.progress);

                Call<ClassListbean> call1 = cr.classList(b.school_id);
                progress.setVisibility(View.VISIBLE);

                call1.enqueue(new Callback<ClassListbean>() {
                    @Override
                    public void onResponse(Call<ClassListbean> call1, Response<ClassListbean> response) {

                        listClass = response.body().getClassList();

                        classlist.clear();
                        classId.clear();
                        classlist.add("Select Class");
                        sectionlist.add("Select Section");

                        for (int i = 0; i < listClass.size(); i++) {

                            classlist.add(listClass.get(i).getClassName());
                            classId.add(listClass.get(i).getClassId());

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


                                    for (int i = 0; i < listSection.size(); i++) {

                                        sectionlist.add(response.body().getSectionList().get(i).getSectionName());

                                        sectionid.add(response.body().getSectionList().get(i).getSectionId());

                                    }

                                    Log.d("sdsdsss", String.valueOf(sectionlist.size()));

                                    ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                            android.R.layout.simple_list_item_1, sectionlist);

                                    adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    sectionName.setAdapter(adp);

                                    classId1 = classId.get(i - 1);

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

                        Log.d("ooooja", sectionId1);
                        Log.d("oooja", classId1);

                        Call<SubjectListBean> call2 = cr.subjectList(b.school_id, classId1, sectionId1);

                        progress.setVisibility(View.VISIBLE);

                        call2.enqueue(new Callback<SubjectListBean>() {

                            @Override
                            public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {

                                listSubject = response.body().getSubjectList();
                                subjectlist.clear();
                                subjectId.clear();

                                for (int i = 0; i < response.body().getSubjectList().size(); i++) {

                                    subjectlist.add(response.body().getSubjectList().get(i).getSubjectName());

                                    subjectId.add(response.body().getSubjectList().get(i).getSubjectId());

                                }

                                ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                        android.R.layout.simple_list_item_1, subjectlist);

                                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                subjectName.setAdapter(adp);


                                progress.setVisibility(View.GONE);


                            }

                            @Override
                            public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                                progress.setVisibility(View.GONE);

                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                subjectName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        subjectId1 = subjectId.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                submit1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isFirst == true) {
                            Toast.makeText(getActivity(), "Select Class And Section. ", Toast.LENGTH_SHORT).show();
                        } else {


                            Call<SyllabusListBean1> call1 = cr.syllbus_teacher(b.school_id, subjectId1, classId1, sectionId1);
                            Log.d("aaaaaa", subjectId1);
                            Log.d("aaaaaa", classId1);
                            Log.d("aaaaaa", sectionId1);
                            progress.setVisibility(View.VISIBLE);

                            call1.enqueue(new Callback<SyllabusListBean1>() {

                                @Override
                                public void onResponse(Call<SyllabusListBean1> call, Response<SyllabusListBean1> response) {

                                    subject.setText(response.body().getSyllabusList().get(0).getSubjectName());
                                    classSection.setText(response.body().getSyllabusList().get(0).getClassName() + " " + response.body().getSyllabusList().get(0).getSectionName());

                                    adapter.setGridData(response.body().getSyllabusList().get(0).getExamType());
                                    adapter.notifyDataSetChanged();
                                    dialog1.dismiss();
                                    progress1.setVisibility(View.GONE);


                                }

                                @Override
                                public void onFailure(Call<SyllabusListBean1> call, Throwable throwable) {
                                    progress.setVisibility(View.GONE);

                                }
                            });

                        }
                    }
                });


            }


        });


        Call<SyllabusListBean1> call1 = cr.syllbus_teacher(b.school_id, b.user_id);

        progress1.setVisibility(View.VISIBLE);

        call1.enqueue(new Callback<SyllabusListBean1>() {

            @Override
            public void onResponse(Call<SyllabusListBean1> call, final Response<SyllabusListBean1> response) {

                if (response.body().getSyllabusList().get(0).getStatus().equals("2")) {
                    Toast.makeText(getActivity(), "Select Your Class,Section & Subject", Toast.LENGTH_SHORT).show();

                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setCancelable(true);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.academic_popup);
                    dialog.setCancelable(false);
                    final Button submit = (Button) dialog.findViewById(R.id.submit);
                    final Spinner className = (Spinner) dialog.findViewById(R.id.className);
                    final Spinner sectionName = (Spinner) dialog.findViewById(R.id.sectionName);
                    final Spinner subjectName = (Spinner) dialog.findViewById(R.id.subject);
                    final ProgressBar progress = (ProgressBar) dialog.findViewById(R.id.progress);

                    dialog.show();


                    final User b = (User) getActivity().getApplicationContext();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final AllAPIs cr = retrofit.create(AllAPIs.class);

                    final Call<ClassListbean> call1 = cr.classList(b.school_id);
                    progress.setVisibility(View.VISIBLE);

                    call1.enqueue(new Callback<ClassListbean>() {
                        @Override
                        public void onResponse(Call<ClassListbean> call1, Response<ClassListbean> response) {

                            listClass = response.body().getClassList();

                            classlist.clear();
                            classId.clear();
                            classlist.add("Select Class");
                            sectionlist.add("Select Section");

                            for (int i = 0; i < listClass.size(); i++) {

                                classlist.add(listClass.get(i).getClassName());
                                classId.add(listClass.get(i).getClassId());

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


                                        for (int i = 0; i < listSection.size(); i++) {

                                            sectionlist.add(response.body().getSectionList().get(i).getSectionName());

                                            sectionid.add(response.body().getSectionList().get(i).getSectionId());

                                        }

                                        Log.d("sdsdsss", String.valueOf(sectionlist.size()));

                                        ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                                android.R.layout.simple_list_item_1, sectionlist);

                                        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                        sectionName.setAdapter(adp);

                                        classId1 = classId.get(i - 1);

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

                            Log.d("ooooja", sectionId1);
                            Log.d("oooja", classId1);

                            Call<SubjectListBean> call2 = cr.subjectList(b.school_id, classId1, sectionId1);

                            progress.setVisibility(View.VISIBLE);

                            call2.enqueue(new Callback<SubjectListBean>() {

                                @Override
                                public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {

                                    listSubject = response.body().getSubjectList();
                                    subjectlist.clear();
                                    subjectId.clear();

                                    for (int i = 0; i < response.body().getSubjectList().size(); i++) {

                                        subjectlist.add(response.body().getSubjectList().get(i).getSubjectName());

                                        subjectId.add(response.body().getSubjectList().get(i).getSubjectId());

                                    }

                                    ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                            android.R.layout.simple_list_item_1, subjectlist);

                                    adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    subjectName.setAdapter(adp);


                                    progress.setVisibility(View.GONE);


                                }

                                @Override
                                public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                                    progress.setVisibility(View.GONE);

                                }
                            });

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    subjectName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            subjectId1 = subjectId.get(position);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isFirst == true) {
                                Toast.makeText(getActivity(), "Select Class And Section. ", Toast.LENGTH_SHORT).show();
                            } else {


                                Call<SyllabusListBean1> call1 = cr.syllbus_teacher(b.school_id, subjectId1, classId1, sectionId1);
                                Log.d("aaaaaa", subjectId1);
                                Log.d("aaaaaa", classId1);
                                Log.d("aaaaaa", sectionId1);
                                progress.setVisibility(View.VISIBLE);

                                call1.enqueue(new Callback<SyllabusListBean1>() {

                                    @Override
                                    public void onResponse(Call<SyllabusListBean1> call, Response<SyllabusListBean1> response) {

                                        subject.setText(response.body().getSyllabusList().get(0).getSubjectName());
                                        classSection.setText(response.body().getSyllabusList().get(0).getClassName() + " " + response.body().getSyllabusList().get(0).getSectionName());

                                        adapter.setGridData(response.body().getSyllabusList().get(0).getExamType());
                                        adapter.notifyDataSetChanged();
                                        dialog.dismiss();
                                        progress1.setVisibility(View.GONE);


                                    }

                                    @Override
                                    public void onFailure(Call<SyllabusListBean1> call, Throwable throwable) {
                                        progress.setVisibility(View.GONE);

                                    }
                                });

                            }
                        }
                    });

                } else if (response.body().getSyllabusList().get(0).getStatus().equals("1")) {

                    Call<SyllabusListBean1> call1 = cr.syllbus_teacher(b.school_id, b.user_id);

                    progress1.setVisibility(View.VISIBLE);

                    call1.enqueue(new Callback<SyllabusListBean1>() {

                        @Override
                        public void onResponse(Call<SyllabusListBean1> call, Response<SyllabusListBean1> response) {

                            subject.setText(response.body().getSyllabusList().get(0).getSubjectName());
                            classSection.setText(response.body().getSyllabusList().get(0).getClassName() + " " + response.body().getSyllabusList().get(0).getSectionName());

                            adapter.setGridData(response.body().getSyllabusList().get(0).getExamType());
                            adapter.notifyDataSetChanged();
                            progress1.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<SyllabusListBean1> call, Throwable throwable) {
                            progress1.setVisibility(View.GONE);

                        }
                    });
                } else if (response.body().getSyllabusList().get(0).getStatus().equals("0")) {

                    Toast.makeText(getActivity(), "Your Class Syllabus is not assigned, Select other Class.", Toast.LENGTH_LONG).show();

                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setCancelable(true);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.academic_popup);
                    dialog.setCancelable(false);
                    final Button submit = (Button) dialog.findViewById(R.id.submit);
                    final Spinner className = (Spinner) dialog.findViewById(R.id.className);
                    final Spinner sectionName = (Spinner) dialog.findViewById(R.id.sectionName);
                    final Spinner subjectName = (Spinner) dialog.findViewById(R.id.subject);
                    final ProgressBar progress = (ProgressBar) dialog.findViewById(R.id.progress);

                    dialog.show();


                    final User b = (User) getActivity().getApplicationContext();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final AllAPIs cr = retrofit.create(AllAPIs.class);

                    Call<ClassListbean> call1 = cr.classList(b.school_id);
                    progress.setVisibility(View.VISIBLE);

                    call1.enqueue(new Callback<ClassListbean>() {
                        @Override
                        public void onResponse(Call<ClassListbean> call1, Response<ClassListbean> response) {

                            listClass = response.body().getClassList();

                            classlist.clear();
                            classId.clear();
                            classlist.add("Select Class");
                            sectionlist.add("Select Section");

                            for (int i = 0; i < listClass.size(); i++) {

                                classlist.add(listClass.get(i).getClassName());
                                classId.add(listClass.get(i).getClassId());

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


                                        for (int i = 0; i < listSection.size(); i++) {

                                            sectionlist.add(response.body().getSectionList().get(i).getSectionName());

                                            sectionid.add(response.body().getSectionList().get(i).getSectionId());

                                        }

                                        Log.d("sdsdsss", String.valueOf(sectionlist.size()));

                                        ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                                android.R.layout.simple_list_item_1, sectionlist);

                                        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                        sectionName.setAdapter(adp);

                                        classId1 = classId.get(i - 1);

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

                            Log.d("ooooja", sectionId1);
                            Log.d("oooja", classId1);

                            Call<SubjectListBean> call2 = cr.subjectList(b.school_id, classId1, sectionId1);

                            progress.setVisibility(View.VISIBLE);

                            call2.enqueue(new Callback<SubjectListBean>() {

                                @Override
                                public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {

                                    listSubject = response.body().getSubjectList();
                                    subjectlist.clear();
                                    subjectId.clear();

                                    for (int i = 0; i < response.body().getSubjectList().size(); i++) {

                                        subjectlist.add(response.body().getSubjectList().get(i).getSubjectName());

                                        subjectId.add(response.body().getSubjectList().get(i).getSubjectId());

                                    }

                                    ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                            android.R.layout.simple_list_item_1, subjectlist);

                                    adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    subjectName.setAdapter(adp);


                                    progress.setVisibility(View.GONE);


                                }

                                @Override
                                public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                                    progress.setVisibility(View.GONE);

                                }
                            });

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    subjectName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            subjectId1 = subjectId.get(position);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isFirst == true) {
                                Toast.makeText(getActivity(), "Select Class And Section. ", Toast.LENGTH_SHORT).show();
                            } else {


                                Call<SyllabusListBean1> call1 = cr.syllbus_teacher(b.school_id, subjectId1, classId1, sectionId1);
                                Log.d("aaaaaa", subjectId1);
                                Log.d("aaaaaa", classId1);
                                Log.d("aaaaaa", sectionId1);
                                progress.setVisibility(View.VISIBLE);

                                call1.enqueue(new Callback<SyllabusListBean1>() {

                                    @Override
                                    public void onResponse(Call<SyllabusListBean1> call, Response<SyllabusListBean1> response) {

                                        subject.setText(response.body().getSyllabusList().get(0).getSubjectName());
                                        classSection.setText(response.body().getSyllabusList().get(0).getClassName() + " " + response.body().getSyllabusList().get(0).getSectionName());

                                        adapter.setGridData(response.body().getSyllabusList().get(0).getExamType());
                                        adapter.notifyDataSetChanged();
                                        dialog.dismiss();
                                        progress1.setVisibility(View.GONE);


                                    }

                                    @Override
                                    public void onFailure(Call<SyllabusListBean1> call, Throwable throwable) {
                                        progress.setVisibility(View.GONE);

                                    }
                                });

                            }
                        }
                    });


                    progress.setVisibility(View.GONE);
                }


            }

            @Override
            public void onFailure(Call<SyllabusListBean1> call, Throwable throwable) {
                progress1.setVisibility(View.GONE);

            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Syllabus Management");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}