package com.eduschool.eduschoolapp.Attendance;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentList;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.UpdateAttndncPOJO.UpdateAttendncBean;
import com.eduschool.eduschoolapp.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 8/22/2017.
 */

public class UpdateDiffCls extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progress;
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    AdapterMrkAttndnc adapter;
    public List<String> studentlist,studentId;
    public List<StudentList> list;
    String Sdate,month,year,day,Class_name,Section_name,Class_id,Section_id;
    TextView Tday,classSection,Tmonth;
    Button submit;
    List<String>s;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Update Attendance");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        progress = (ProgressBar) findViewById(R.id.progress);
        manager = new GridLayoutManager(UpdateDiffCls.this, 1);
        submit=(Button)findViewById(R.id.submit);
        Tday=(TextView)findViewById(R.id.day);
        classSection=(TextView)findViewById(R.id.classSection);
        Tmonth=(TextView)findViewById(R.id.month);



        Intent intent=getIntent();
        Sdate=intent.getStringExtra("Id");
        Class_name=intent.getStringExtra("Class");
        Section_name=intent.getStringExtra("Section");
        Class_id=intent.getStringExtra("Class_id");
        Section_id=intent.getStringExtra("Section_id");



        final DateFormat month =  new SimpleDateFormat("MMM");
        final Date month1 = new Date();

        final DateFormat year =  new SimpleDateFormat("yyyy");
        final Date year1 = new Date();


        final DateFormat day =  new SimpleDateFormat("dd");
        final Date day1 = new Date();

        Log.d("sds",month.format(month1));
        Log.d("sds",year.format(year1));
        Log.d("sds",day.format(day1));

        Tday.setText(day.format(day1));
        Tmonth.setText(month.format(month1)+" "+year.format(year1));



        list = new ArrayList<>();
        s=new ArrayList<>();

        adapter = new AdapterMrkAttndnc(UpdateDiffCls.this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        studentlist = new ArrayList<>();
        list = new ArrayList<>();
        studentId = new ArrayList<>();

        final User b = (User)getApplicationContext();
        classSection.setText(Class_name+" "+Section_name);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);
        Call<StudentListbean> call3 = cr.student_list(b.user_id,Class_id, Section_id);

        progress.setVisibility(View.VISIBLE);

        call3.enqueue(new Callback<StudentListbean>() {

            @Override
            public void onResponse(Call<StudentListbean> call3, Response<StudentListbean> response) {



                list = response.body().getStudentList();
                studentlist.clear();
                studentId.clear();
                for (int i = 0; i < response.body().getStudentList().size(); i++) {

                    s.add("1");
                    studentlist.add(response.body().getStudentList().get(i).getStudentName());
                    studentId.add(response.body().getStudentList().get(i).getStudentId());

                }

                adapter.setGridData(response.body().getStudentList(),s);
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);



            }

            @Override
            public void onFailure(Call<StudentListbean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);

            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONArray jsonArray = new JSONArray();
                JSONArray jsonArray2 = new JSONArray();
                List<String>list1= adapter.getCheckList();

                for (int i=0;i<studentId.size();i++){
                    JSONObject object =new JSONObject();
                    JSONObject object2 =new JSONObject();
                    try {
                        object.put("Id",studentId.get(i));
                        object2.put("code" , list1.get(i));
                        jsonArray.put(object2);
                        jsonArray2.put(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                JSONObject jsonObject=new JSONObject();
                JSONObject jsonObject1=new JSONObject();
                try {
                    jsonObject.put("stu_id",jsonArray2);
                    jsonObject1.put("attend",jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("dsds",jsonObject.toString());
                Log.d("dsds",jsonObject1.toString());



                progress.setVisibility(View.VISIBLE);
                Call<UpdateAttendncBean> call3 = cr.update_attndnc(Sdate,jsonObject.toString(),jsonObject1.toString());

                progress.setVisibility(View.VISIBLE);


                call3.enqueue(new Callback<UpdateAttendncBean>() {

                    @Override
                    public void onResponse(Call<UpdateAttendncBean> call3, Response<UpdateAttendncBean> response) {

                        if (response.body().getStatus().equals("1")){
                            Toast.makeText(UpdateDiffCls.this,"Attendance has been updated Successfully ",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(UpdateDiffCls.this,"Attendance does not updated!",Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        progress.setVisibility(View.GONE);



                    }

                    @Override
                    public void onFailure(Call<UpdateAttendncBean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }
                });


            }
        });


    }
}
