package com.eduschool.eduschoolapp.RaiseRequest;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ExamSchedulListPOJO.ExamSchedulList;
import com.eduschool.eduschoolapp.ExamSchedulListPOJO.ExamSchedulListBean;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumListBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.RaiseRequestPOJO.RaiseRequestBean;
import com.eduschool.eduschoolapp.RaiseRequstPOJO.RaiseReqstBean;
import com.eduschool.eduschoolapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RaiseRequestActivity extends AppCompatActivity {
    TextView start, end;
    private String[] arraySpinner;
    Toolbar toolbar;
    Button send;
    int position2, position1,requestPostion,queryPostion,exmPostion;
    String reason;
    ProgressBar progress;
    String date, toDate,queryString,exmString;
    int i;
    List<ExamSchedulList> list;
    List<String>Slist,sId;
    LinearLayout layout1, layout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_request);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Raise Request");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        start = (TextView) findViewById(R.id.start);
        end = (TextView) findViewById(R.id.end);
        send = (Button) findViewById(R.id.send);
        progress = (ProgressBar) findViewById(R.id.progress);
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        list=new ArrayList<>();
        Slist=new ArrayList<>();
        sId=new ArrayList<>();

        this.arraySpinner = new String[]{
                "Select", "Leave Request", "Exam & Result"
        };

        final String[] str = {"Select Reason", "Sick", "Out of Station"};
        final String[] str1 = {"Select", "When will i get the result?", "Query regarding marks","When will we get report card?"};

        final Spinner s = (Spinner) findViewById(R.id.request_type);
        final Spinner query = (Spinner) findViewById(R.id.query);
        final Spinner exm_typ = (Spinner) findViewById(R.id.exm_type);
        Spinner s1 = (Spinner) findViewById(R.id.reaason);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, str);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adp1);

        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, str1);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        query.setAdapter(adp2);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "datepicker");
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new SelectDateFragment1();
                newFragment.show(getFragmentManager(), "datepicker");
            }
        });

        User b = (User) getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<ExamSchedulListBean> call = cr.exm_list_parent(b.school_id, b.user_class, b.user_section,b.user_id);

        call.enqueue(new Callback<ExamSchedulListBean>() {
            @Override
            public void onResponse(Call<ExamSchedulListBean> call, Response<ExamSchedulListBean> response) {

                list=response.body().getExamSchedulList();

                for (int i=0;i<list.size();i++){

                    Slist.add(response.body().getExamSchedulList().get(i).getName());
                    sId.add(response.body().getExamSchedulList().get(i).getId());
                }

                ArrayAdapter<String> adp3 = new ArrayAdapter<String>(RaiseRequestActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, Slist);
                adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                exm_typ.setAdapter(adp3);

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ExamSchedulListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });



        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                position1 = position;
                reason = str[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                position2 = position;

                if (position == 1) {
                    i = 1;
                    layout2.setVisibility(View.GONE);
                    layout1.setVisibility(View.VISIBLE);
                } else if (position == 2) {
                    i = 2;
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        query.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                queryPostion=position;
                queryString=str1[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        exm_typ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                exmPostion=position;
                exmString=sId.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i == 1) {
                    if (position1 == 0 || position2 == 0 || start.getText().equals("Start Date") || end.getText().equals("End Date")) {

                        Toast.makeText(RaiseRequestActivity.this, "Please select All fields", Toast.LENGTH_SHORT).show();

                    } else {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(RaiseRequestActivity.this);
                        dialog.setCancelable(false);
                        dialog.setMessage("Are you sure you want to send this request?");
                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {


                                User b = (User) getApplicationContext();
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.baseURL)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                AllAPIs cr = retrofit.create(AllAPIs.class);
                                progress.setVisibility(View.VISIBLE);

                                Call<RaiseRequestBean> call = cr.raise_requst(b.school_id, b.user_id, b.user_type, b.user_class, b.user_section, date, toDate, reason,"Leave Request");

                                call.enqueue(new Callback<RaiseRequestBean>() {
                                    @Override
                                    public void onResponse(Call<RaiseRequestBean> call, Response<RaiseRequestBean> response) {

                                        if (response.body().getStatus().equals("1")) {

                                            Toast.makeText(RaiseRequestActivity.this, "Requset has been send successfully", Toast.LENGTH_SHORT).show();

                                            finish();
                                        } else {
                                            Toast.makeText(RaiseRequestActivity.this, "Requset sending failed!", Toast.LENGTH_SHORT).show();

                                            finish();
                                        }

                                        progress.setVisibility(View.GONE);

                                    }

                                    @Override
                                    public void onFailure(Call<RaiseRequestBean> call, Throwable throwable) {

                                        progress.setVisibility(View.GONE);

                                    }
                                });


                                dialog.dismiss();

                            }
                        })
                                .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Action for "Cancel".
                                    }
                                });


                        final AlertDialog alert = dialog.create();
                        alert.show();
                    }
                } else if (i == 2) {

                    if (queryPostion == 0 || position2 == 0 ) {

                        Toast.makeText(RaiseRequestActivity.this, "Please select All fields", Toast.LENGTH_SHORT).show();

                    } else {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(RaiseRequestActivity.this);
                        dialog.setCancelable(false);
                        dialog.setMessage("Are you sure you want to send this request?");
                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {


                                User b = (User) getApplicationContext();
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.baseURL)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                AllAPIs cr = retrofit.create(AllAPIs.class);
                                progress.setVisibility(View.VISIBLE);

                                Call<RaiseReqstBean> call = cr.raise_request(b.school_id, b.user_id, b.user_type, b.user_class, b.user_section, queryString, exmString,"Exam & Result");

                                call.enqueue(new Callback<RaiseReqstBean>() {
                                    @Override
                                    public void onResponse(Call<RaiseReqstBean> call, Response<RaiseReqstBean> response) {

                                        if (response.body().getStatus().equals("1")) {

                                            Toast.makeText(RaiseRequestActivity.this, "Requset has been send successfully", Toast.LENGTH_SHORT).show();

                                            finish();
                                        } else {
                                            Toast.makeText(RaiseRequestActivity.this, "Requset sending failed!", Toast.LENGTH_SHORT).show();

                                            finish();
                                        }

                                        progress.setVisibility(View.GONE);

                                    }

                                    @Override
                                    public void onFailure(Call<RaiseReqstBean> call, Throwable throwable) {

                                        progress.setVisibility(View.GONE);

                                    }
                                });


                                dialog.dismiss();

                            }
                        })
                                .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Action for "Cancel".
                                    }
                                });


                        final AlertDialog alert = dialog.create();
                        alert.show();

                    }
                }

            }

        });


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
            start.setText(date);


        }

    }

    @SuppressLint("ValidFragment")
    public class SelectDateFragment1 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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
            toDate = formattedDate;
            end.setText(toDate);


        }

    }
}
