package com.eduschool.eduschoolapp.ClassWork;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClasssworkList;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClasssworkListBean;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClassworkList;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.HomeWork.AdapterParent1;
import com.eduschool.eduschoolapp.HomeWork.HomeWorkFrgmntTwo;
import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeWorkListBean;
import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeworkList_;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/8/2017.
 */

public class ClassWorkFrgmntTwo extends Fragment {
    Toolbar toolbar;
    ProgressBar progress;
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    String date;
    String strtext;
    List<ClassworkList> list;
    AdapterCwParent1 adapter;
    boolean isSearch=false;

    TextView name, classSection;
    public ClassWorkFrgmntTwo() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.class_wrk_frgmnt_two, container, false);
        strtext = getArguments().getString("message");
        name = (TextView) view.findViewById(R.id.name);
        classSection = (TextView) view.findViewById(R.id.classSection);

        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm= ((ParentHome)getContext()).getSupportFragmentManager();
                fm.popBackStack();
            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);


        list = new ArrayList<>();

        adapter = new AdapterCwParent1(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getActivity().getFragmentManager(), "DatePicker");


            }
        });

      /*  card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ClassWrkFrgmntThree frag1 = new ClassWrkFrgmntThree();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });*/

            User b = (User) getActivity().getApplicationContext();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<ClasssworkListBean> call = cr.claass_wrk(b.school_id, b.user_class, b.user_section, b.user_id, strtext);

            Log.d("vsdvs", String.valueOf(b.user_class));
            Log.d("vsdvs", String.valueOf(b.user_section));


            call.enqueue(new Callback<ClasssworkListBean>() {
                @Override
                public void onResponse(Call<ClasssworkListBean> call, Response<ClasssworkListBean> response) {

                    name.setText(response.body().getClasssworkList().get(0).getStudentName());
                    classSection.setText(response.body().getClasssworkList().get(0).getClassName() + " " + response.body().getClasssworkList().get(0).getSectionName());

                    adapter.setGridData(response.body().getClasssworkList().get(0).getClassworkList());
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<ClasssworkListBean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });


        return view;

    }


    @SuppressLint("ValidFragment")
    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            @SuppressLint("WrongConstant") int yy = calendar.get(Calendar.YEAR);
            @SuppressLint("WrongConstant") int mm = calendar.get(Calendar.MONTH);
            @SuppressLint("WrongConstant") int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }


        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = sdf.format(c.getTime());
            date = formattedDate;

            User b = (User) getActivity().getApplicationContext();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Log.d("sdffdf",date);
            Call<ClasssworkListBean> call = cr.claass_wrk(b.school_id, b.user_class, b.user_section, b.user_id, strtext,date);

            call.enqueue(new Callback<ClasssworkListBean>() {
                @Override
                public void onResponse(Call<ClasssworkListBean> call, Response<ClasssworkListBean> response) {

                    name.setText(response.body().getClasssworkList().get(0).getStudentName());
                    classSection.setText(response.body().getClasssworkList().get(0).getClassName() + " " + response.body().getClasssworkList().get(0).getSectionName());
                    adapter.setGridData(response.body().getClasssworkList().get(0).getClassworkList());
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);


                }

                @Override
                public void onFailure(Call<ClasssworkListBean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });


        }

    }


    @Override
    public void onResume() {

        super.onResume();
        toolbar.setTitle("Class Work");

        User u = (User) getContext().getApplicationContext();

        u.back = false;




    }
}