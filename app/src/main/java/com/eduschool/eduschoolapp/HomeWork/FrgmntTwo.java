package com.eduschool.eduschoolapp.HomeWork;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
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
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassList;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.LoginPOJO.Loginbean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionList;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.Splash;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectList;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.Survey.AdapterSurvey;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomeworkList;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomewrkListbean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 5/20/2017.
 */

public class FrgmntTwo extends Fragment {

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterHwList adapter;
    List<HomeworkList> list;
    GridLayoutManager manager;
    List<ClassList> list1;
    List<String> classlist, sectionlist, subjectlist;
    List<String> classId, sectionid, subjectId;
    List<SectionList> listSection;
    List<SubjectList> listSubject;
    boolean isFirst;
    boolean isSearch = false;
    ProgressBar progress;
    String cId, sId, ssId;
    DrawerLayout drawer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.teacher_hw_frgmnt2, container, false);
        drawer = (DrawerLayout) ((TeacherHome) getContext()).findViewById(R.id.drawer_asiana);

        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);

        list = new ArrayList<>();

        //searchList = new ArrayList<>();


        adapter = new AdapterHwList(getContext(), list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        list1 = new ArrayList<>();
        classlist = new ArrayList<>();
        classId = new ArrayList<>();

        listSection = new ArrayList<>();
        sectionlist = new ArrayList<>();
        sectionid = new ArrayList<>();

        listSubject = new ArrayList<>();
        subjectlist = new ArrayList<>();
        subjectId = new ArrayList<>();


        if (isSearch = false) {

            User b = (User) getActivity().getApplicationContext();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<HomewrkListbean> call = cr.homwwrk_list(b.school_id, b.user_id);

            call.enqueue(new Callback<HomewrkListbean>() {
                @Override
                public void onResponse(Call<HomewrkListbean> call, Response<HomewrkListbean> response) {

                    adapter.setGridData(response.body().getHomeworkList());
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<HomewrkListbean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });

        }



      /*  card=(CardView)view.findViewById(R.id.card);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherHwFrgmntTwo frag1 = new TeacherHwFrgmntTwo();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
*/


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.hw_dialog);
                final Button submit = (Button) dialog.findViewById(R.id.submit);
                final Spinner className = (Spinner) dialog.findViewById(R.id.className);
                final Spinner sectionName = (Spinner) dialog.findViewById(R.id.sectionName);
                final Spinner subjectName = (Spinner) dialog.findViewById(R.id.subjectName);
                final ProgressBar progress = (ProgressBar) dialog.findViewById(R.id.progress);


                final User b = (User) getActivity().getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final AllAPIs cr = retrofit.create(AllAPIs.class);

                Call<ClassListbean> call = cr.classList(b.school_id);
                progress.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<ClassListbean>() {
                    @Override
                    public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {


                        list1 = response.body().getClassList();

                        classlist.clear();
                        classId.clear();

                        for (int i = 0; i < response.body().getClassList().size(); i++) {

                            classlist.add(response.body().getClassList().get(i).getClassName());

                            classId.add(response.body().getClassList().get(i).getClassId());
                        }

                        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, classlist);
                        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        className.setAdapter(adp1);


                        ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, sectionlist);

                        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sectionName.setAdapter(adp);

                        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, subjectlist);

                        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjectName.setAdapter(adp2);


                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                        progress.setVisibility(View.GONE);

                    }
                });


                className.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        cId = classId.get(i);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);

                        Call<SectionListbean> call2 = cr.sectionList(b.school_id, classId.get(i));

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


                                Log.d("Cid", String.valueOf(cId));

                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                                progress.setVisibility(View.GONE);

                            }
                        });



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                sectionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                        sId = sectionid.get(i);
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        final AllAPIs cr = retrofit.create(AllAPIs.class);

                        Call<SectionListbean> call2 = cr.sectionList(b.school_id, classId.get(i));

                        progress.setVisibility(View.VISIBLE);


                        call2.enqueue(new Callback<SectionListbean>() {

                            @Override
                            public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.baseURL)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                AllAPIs cr = retrofit.create(AllAPIs.class);

                                Call<SubjectListBean> call1 = cr.subjectList(b.school_id, classId.get(i),sectionid.get(i));

                                progress.setVisibility(View.VISIBLE);

                                call1.enqueue(new Callback<SubjectListBean>() {

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

                                progress.setVisibility(View.GONE);


                            }

                            @Override
                            public void onFailure(Call<SectionListbean> call, Throwable throwable) {
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
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        ssId = subjectId.get(i);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (isFirst == true) {
                            Toast.makeText(getActivity(), "Select Class, Section and Subject. ", Toast.LENGTH_SHORT).show();
                        } else {


                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);

                            progress.setVisibility(View.VISIBLE);
                            Log.d("iddd", String.valueOf(sId));

                            Call<HomewrkListbean> call = cr.homwwrk_list(b.school_id, b.user_id, cId, sId, ssId);

                            call.enqueue(new Callback<HomewrkListbean>() {
                                @Override
                                public void onResponse(Call<HomewrkListbean> call, Response<HomewrkListbean> response) {


                                    dialog.dismiss();
                                    progress.setVisibility(View.GONE);

                                    adapter.setGridData(response.body().getHomeworkList());
                                    adapter.notifyDataSetChanged();

                                    isSearch = true;
                                    onResume();

                                }

                                @Override
                                public void onFailure(Call<HomewrkListbean> call, Throwable throwable) {
                                    progress.setVisibility(View.GONE);

                                }
                            });

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



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if (isSearch == false) {

            User b = (User) getActivity().getApplicationContext();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<HomewrkListbean> call = cr.homwwrk_list(b.school_id, b.user_id);

            call.enqueue(new Callback<HomewrkListbean>() {
                @Override
                public void onResponse(Call<HomewrkListbean> call, Response<HomewrkListbean> response) {


                    adapter.setGridData(response.body().getHomeworkList());
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<HomewrkListbean> call, Throwable throwable) {
                    progress.setVisibility(View.GONE);

                }
            });


        } else {
            Log.d("elseSearchh", String.valueOf(isSearch));

            final User b = (User) getActivity().getApplicationContext();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);

            progress.setVisibility(View.VISIBLE);

            Call<HomewrkListbean> call = cr.homwwrk_list(b.school_id, b.user_id, cId, sId, ssId);

            call.enqueue(new Callback<HomewrkListbean>() {
                @Override
                public void onResponse(Call<HomewrkListbean> call, Response<HomewrkListbean> response) {

                    progress.setVisibility(View.GONE);

                    adapter.setGridData(response.body().getHomeworkList());
                    adapter.notifyDataSetChanged();

                    Log.d("sdcscs", "sssssss");


                }

                @Override
                public void onFailure(Call<HomewrkListbean> call, Throwable throwable) {
                    progress.setVisibility(View.GONE);

                }
            });


        }
    }


}