package com.eduschool.eduschoolapp.ClassWork;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterList;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterListbean;
import com.eduschool.eduschoolapp.ClssWrkPOJO.ClasswrkBean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentList;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.UpdateCwPOJO.UpdateCwBean;
import com.eduschool.eduschoolapp.UpdateHwPOJO.UpdateHwBean;
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
 * Created by user on 5/19/2017.
 */

public class TeacherClsWrk3 extends Fragment {
    Toolbar toolbar;
    Button update;
    ProgressBar progress;
    String Id;
    Spinner status, chapter;
    EditText note;
    String cId, ssId, sId, hId, sChapter, sNote, sStatus;
    List<String> chapterlist, studentlist, chapterId, studentId;
    public List<StudentList> listStudent;
    public List<ChapterList> listChapter;
    TextView subject, classSection, date;
    List<String> listStatus;

    public TeacherClsWrk3() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_cls_wrk3, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        String strtext = getArguments().getString("message");

        progress = (ProgressBar) view.findViewById(R.id.progress);

        subject = (TextView) view.findViewById(R.id.subject);
        classSection = (TextView) view.findViewById(R.id.className);
        date = (TextView) view.findViewById(R.id.date);
        status = (Spinner) view.findViewById(R.id.status);
        chapter = (Spinner) view.findViewById(R.id.chapter);
        note = (EditText) view.findViewById(R.id.note);
        update = (Button) view.findViewById(R.id.update);

        chapterlist = new ArrayList<>();
        listChapter = new ArrayList<>();
        chapterId = new ArrayList<>();
        studentlist = new ArrayList<>();
        studentId = new ArrayList<>();
        listStudent = new ArrayList<>();


        listStatus = new ArrayList<String>();

        final User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<ClasswrkBean> call = cr.clss_wrk(b.school_id, strtext);

        call.enqueue(new Callback<ClasswrkBean>() {
            @Override
            public void onResponse(Call<ClasswrkBean> call, Response<ClasswrkBean> response) {

                subject.setText(response.body().getClassworkData().get(0).getSubject());
                classSection.setText(response.body().getClassworkData().get(0).getClass_() + " " + response.body().getClassworkData().get(0).getSection());
                date.setText(response.body().getClassworkData().get(0).getCreateDate());
                note.setText(response.body().getClassworkData().get(0).getNotes());
                Id = response.body().getClassworkData().get(0).getClassworkId();


                chapterlist.add(response.body().getClassworkData().get(0).getChapter());

                chapterId.add(response.body().getClassworkData().get(0).getChapterId());


                ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, chapterlist);

                adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adp2.notifyDataSetChanged();
                chapter.setAdapter(adp2);

                listStatus.add(response.body().getClassworkData().get(0).getClassStatus());

                ArrayAdapter<String> adp3 = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, listStatus);

                adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adp3.notifyDataSetChanged();
                status.setAdapter(adp3);

                cId = response.body().getClassworkData().get(0).getClassId();
                ssId = response.body().getClassworkData().get(0).getSubjectId();
                sId = response.body().getClassworkData().get(0).getSectionId();
                hId = response.body().getClassworkData().get(0).getClassworkId();

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ClasswrkBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });

        chapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                Call<ChapterListbean> call = cr.chapterList(b.school_id, cId, ssId);

                progress.setVisibility(View.VISIBLE);


                call.enqueue(new Callback<ChapterListbean>() {

                    @Override
                    public void onResponse(Call<ChapterListbean> call, Response<ChapterListbean> response) {

                        sChapter = chapterId.get(i);

                        listChapter = response.body().getChapterList();
                        chapterlist.clear();
                        chapterId.clear();
                        for (int i = 0; i < response.body().getChapterList().size(); i++) {

                            chapterlist.add(response.body().getChapterList().get(i).getChapterName());

                            chapterId.add(response.body().getChapterList().get(i).getChapterId());

                        }

                        progress.setVisibility(View.GONE);

                        Call<StudentListbean> call3 = cr.student_list(b.school_id, cId, sId);

                        progress.setVisibility(View.VISIBLE);

                        call3.enqueue(new Callback<StudentListbean>() {

                            @Override
                            public void onResponse(Call<StudentListbean> call3, Response<StudentListbean> response) {


                                listStudent = response.body().getStudentList();
                                studentlist.clear();
                                studentId.clear();
                                for (int i = 0; i < response.body().getStudentList().size(); i++) {

                                    studentlist.add(response.body().getStudentList().get(i).getStudentName());

                                    studentId.add(response.body().getStudentList().get(i).getStudentId());
                                }

                                Log.d("studenttt", String.valueOf(studentId.size()));
                                progress.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<StudentListbean> call, Throwable throwable) {
                                progress.setVisibility(View.GONE);

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<ChapterListbean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sStatus = listStatus.get(i);
                listStatus.clear();
                listStatus.add("Reading");
                listStatus.add("Home Work Assigned");
                listStatus.add("Doubt Clearing");
                listStatus.add("Completed");

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(false);
                dialog.setMessage("Are you sure you want to Update Class work ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        sNote = note.getText().toString().trim();


                        Call<UpdateCwBean> call = cr.update_cw(hId, b.school_id, b.user_id, cId, sId, ssId, sChapter, sNote, sStatus, studentId, "Image");
                        Log.d("dddfer", sChapter);

                        progress.setVisibility(View.VISIBLE);


                        call.enqueue(new Callback<UpdateCwBean>() {

                            @Override
                            public void onResponse(Call<UpdateCwBean> call, Response<UpdateCwBean> response) {

                                if (response.body().getStatus().equals("1")) {
                                    Toast.makeText(getContext(), "Class Work Updated Successfully", Toast.LENGTH_LONG).show();

                                    getFragmentManager().popBackStack();
                                } else {
                                    Toast.makeText(getContext(), "Class Work did Not updated successfully!", Toast.LENGTH_LONG).show();
                                }

                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<UpdateCwBean> call, Throwable throwable) {
                                progress.setVisibility(View.GONE);

                            }
                        });

                        dialog.dismiss();
                    }
                })
                        .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });


                final AlertDialog alert = dialog.create();
                alert.show();

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
