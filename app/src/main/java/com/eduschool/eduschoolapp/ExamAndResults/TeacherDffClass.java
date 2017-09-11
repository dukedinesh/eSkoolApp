package com.eduschool.eduschoolapp.ExamAndResults;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassResultPOJO.ClassResultBean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.ResultPOJO.ResultListBean;
import com.eduschool.eduschoolapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Dinesh  on 04/09/2017.
 */

public class TeacherDffClass extends Fragment {
    Toolbar toolbar;
    TextView tvresult;
    String id;
    ProgressBar progress;
    String Sdate,class_name,section_name,class_id,section_id;
    TextView totalStudent,passStudent,failStudent;
    private BottomSheetBehavior mBottomSheetBehavior1;
    public TeacherDffClass() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_own_cls, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm= ((TeacherHome)getContext()).getSupportFragmentManager();
                fm.popBackStack();
            }
        });
        progress=(ProgressBar)view.findViewById(R.id.progress);
        totalStudent=(TextView)view.findViewById(R.id.totalStudent);
        passStudent=(TextView)view.findViewById(R.id.passStudent);
        failStudent=(TextView)view.findViewById(R.id.failStudent);

        Sdate = getArguments().getString("Date");
        class_name = getArguments().getString("Class");
        section_name = getArguments().getString("Section");
        section_id = getArguments().getString("SectionId");
        class_id = getArguments().getString("ClassId");
        id = getArguments().getString("message");



        Log.d("class",class_name);
        Log.d("class",class_id);
        Log.d("class",section_id);
        Log.d("class",section_name);


        final View bottomSheet1 = view.findViewById(R.id.bottom_sheet1);
        mBottomSheetBehavior1 = BottomSheetBehavior.from(bottomSheet1);
        //mBottomSheetBehavior1.setHideable(true);
        mBottomSheetBehavior1.setPeekHeight(100);
        mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_HIDDEN);

        tvresult = (TextView) view.findViewById(R.id.tvresult);
        tvresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBottomSheetBehavior1.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);

                }
                else {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
            }
        });

        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<ResultListBean> call = cr.result(b.school_id,b.user_class,b.user_section,id);

        call.enqueue(new Callback<ResultListBean>() {
            @Override
            public void onResponse(Call<ResultListBean> call, Response<ResultListBean> response) {


                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ResultListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });



        progress.setVisibility(View.VISIBLE);

        Call<ClassResultBean> call1 = cr.class_result(b.school_id,class_id,section_id,id);

        call1.enqueue(new Callback<ClassResultBean>() {
            @Override
            public void onResponse(Call<ClassResultBean> cal1, Response<ClassResultBean> response) {

                try {
                    totalStudent.setText(response.body().getClassResult().get(0).getTotalStudent().toString());
                    passStudent.setText(response.body().getClassResult().get(0).getTotalPassStudent().toString());
                    failStudent.setText(response.body().getClassResult().get(0).getTotalFailStudent().toString());
                }catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }



                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ClassResultBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });





        return view;

    }
    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Result");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}


