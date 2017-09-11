package com.eduschool.eduschoolapp.ClassWork;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassWrkListbean;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassworkList;
import com.eduschool.eduschoolapp.HomeWork.AdapterHwList;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionList;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectList;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomewrkListbean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 4/12/2017.
 */

public class TwoFragment extends Fragment {
    CardView card;
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    List<ClassworkList> list;
    List<ClassList> list1;
    List<String> classlist, sectionlist, subjectlist;
    List<String> classId, sectionid, subjectId;
    List<SectionList> listSection;
    List<SubjectList> listSubject;
    boolean isFirst;
    AdapterClsWrkList adapter;
    boolean isSearch = false;
    ProgressBar progress;
    String cId, sId, ssId;


    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);
        card = (CardView) view.findViewById(R.id.card);

       /* card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherClsWrk2 frag1 = new TeacherClsWrk2();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View To) {
                android.app.FragmentManager fm = getActivity().getFragmentManager();
                com.eduschool.eduschoolapp.ClassWork.FilterDailog ratingBarFragment = new com.eduschool.eduschoolapp.ClassWork.FilterDailog();
                ratingBarFragment.show(fm, "dialog");

            }
        });


        return view;
    }


}*/

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);

        list = new ArrayList<>();

        //searchList = new ArrayList<>();


        adapter = new AdapterClsWrkList(getContext(), list);

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

            Call<ClassWrkListbean> call = cr.classwrk_list(b.school_id, b.user_id);

            call.enqueue(new Callback<ClassWrkListbean>() {
                @Override
                public void onResponse(Call<ClassWrkListbean> call, Response<ClassWrkListbean> response) {


                    adapter.setGridData(response.body().getClassworkList());
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<ClassWrkListbean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });

        }


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

                            Call<ClassWrkListbean> call = cr.classwrk_list(b.school_id, b.user_id, cId, sId, ssId);

                            call.enqueue(new Callback<ClassWrkListbean>() {
                                @Override
                                public void onResponse(Call<ClassWrkListbean> call, Response<ClassWrkListbean> response) {


                                    dialog.dismiss();
                                    progress.setVisibility(View.GONE);
                                    adapter.setGridData(response.body().getClassworkList());
                                    adapter.notifyDataSetChanged();
                                    isSearch = true;
                                    onResume();

                                }

                                @Override
                                public void onFailure(Call<ClassWrkListbean> call, Throwable throwable) {
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


        if (isSearch == false) {

            User b = (User) getActivity().getApplicationContext();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<ClassWrkListbean> call = cr.classwrk_list(b.school_id, b.user_id);

            call.enqueue(new Callback<ClassWrkListbean>() {
                @Override
                public void onResponse(Call<ClassWrkListbean> call, Response<ClassWrkListbean> response) {


                    adapter.setGridData(response.body().getClassworkList());
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<ClassWrkListbean> call, Throwable throwable) {
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
            Log.d("iddd", String.valueOf(sId));

            Call<ClassWrkListbean> call = cr.classwrk_list(b.school_id, b.user_id, cId, sId, ssId);

            call.enqueue(new Callback<ClassWrkListbean>() {
                @Override
                public void onResponse(Call<ClassWrkListbean> call, Response<ClassWrkListbean> response) {

                    progress.setVisibility(View.GONE);
                    adapter.setGridData(response.body().getClassworkList());
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<ClassWrkListbean> call, Throwable throwable) {
                    progress.setVisibility(View.GONE);

                }
            });


        }
    }


}