package com.eduschool.eduschoolapp.Events;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassList;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Communication.ComposeMessage;
import com.eduschool.eduschoolapp.CreateEventTecherPOJO.CreateEventTechrBean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionList;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/15/2017.
 */

public class CreateEventFrgmnt extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progress;
    LinearLayout date1, date2, time;
    TextView tv_date;
    static TextView tv_time;
    TextView tv_date1;
    String date, datee;
    String cId, sId, noteString;
    Spinner type, classSpinner, sectionSpinner;
    List<String> dummyList;
    public List<String> classlist, sectionlist, subjectlist, chapterlist, studentlist, statuslist;
    public List<String> classId, sectionId, subjectId, chapterId, studentId;
    public List<SectionList> listSection;
    List<ClassList> list;
    Button submit;
    int pos;
    public String s;
    EditText note;


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Compose message");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progress = (ProgressBar) findViewById(R.id.progress);
        date1 = (LinearLayout) findViewById(R.id.date1);
        date2 = (LinearLayout) findViewById(R.id.date2);
        time = (LinearLayout) findViewById(R.id.time);
        tv_date1 = (TextView) findViewById(R.id.tv_date1);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_date = (TextView) findViewById(R.id.tv_date);
        type = (Spinner) findViewById(R.id.type);
        classSpinner = (Spinner) findViewById(R.id.className);
        sectionSpinner = (Spinner) findViewById(R.id.sectonName);
        submit = (Button) findViewById(R.id.submit);
        note = (EditText) findViewById(R.id.note);
        noteString = note.getText().toString().trim();
        dummyList = new ArrayList<>();
        dummyList.add("Select");
        dummyList.add("PTM");
        dummyList.add("Collect Report Card");


        list = new ArrayList<>();
        classlist = new ArrayList<>();
        classId = new ArrayList<>();

        listSection = new ArrayList<>();
        sectionlist = new ArrayList<>();
        sectionId = new ArrayList<>();


        final User b = (User) getApplicationContext();
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


                list = response.body().getClassList();

                classlist.clear();
                classId.clear();

                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i).getClassName() != null && list.get(i).getClassId() != null) {

                        classlist.add(list.get(i).getClassName());
                        classId.add(list.get(i).getClassId());
                    }

                }

                ArrayAdapter<String> adp1 = new ArrayAdapter<String>(CreateEventFrgmnt.this,
                        R.layout.spinner_item, classlist);
                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                classSpinner.setAdapter(adp1);


                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                cId = classId.get(position);
                Call<SectionListbean> call2 = cr.sectionList(b.school_id, classId.get(position));
                progress.setVisibility(View.VISIBLE);

                call2.enqueue(new Callback<SectionListbean>() {

                    @Override
                    public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                        listSection = response.body().getSectionList();
                        sectionId.clear();
                        sectionlist.clear();

                        for (int i = 0; i < response.body().getSectionList().size(); i++) {

                            sectionlist.add(response.body().getSectionList().get(i).getSectionName());

                            sectionId.add(response.body().getSectionList().get(i).getSectionId());
                        }


                        ArrayAdapter<String> adp = new ArrayAdapter<String>(CreateEventFrgmnt.this,
                                R.layout.spinner_item, sectionlist);

                        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        adp.notifyDataSetChanged();
                        sectionSpinner.setAdapter(adp);

                        progress.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }

                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sId = sectionId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(CreateEventFrgmnt.this,
                R.layout.spinner_item, dummyList);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adp1);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                pos = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        date1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new CreateEventFrgmnt.SelectDateFragment1();
                newFragment.show(CreateEventFrgmnt.this.getFragmentManager(), "DatePicker");

            }
        });


        date2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new CreateEventFrgmnt.SelectDateFragment();
                newFragment.show(CreateEventFrgmnt.this.getFragmentManager(), "DatePicker");

            }
        });


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new CreateEventFrgmnt.TimePickerFragment();
                newFragment.show(CreateEventFrgmnt.this.getFragmentManager(), "TimePicker");

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pos == 0 || tv_date.getText().equals("Start Date") || tv_date1.getText().equals("End Date") || tv_time.getText().equals("Time")) {

                    Toast.makeText(CreateEventFrgmnt.this, "Please select all fields!", Toast.LENGTH_SHORT).show();
                } else {


                    JSONArray jsonArray = new JSONArray();
                    JSONArray jsonArray1 = new JSONArray();

                    JSONObject object2 = new JSONObject();
                    JSONObject object = new JSONObject();

                    try {
                        object2.put("Id", cId);

                        jsonArray.put(object2);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    try {

                        object.put("Id", sId);

                        jsonArray1.put(object);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    final JSONObject jsonObject = new JSONObject();
                    final JSONObject jsonObject1 = new JSONObject();

                    try {

                        jsonObject.put("class_id", jsonArray);
                        jsonObject1.put("section_id", jsonArray1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("dsds", jsonObject.toString());
                    Log.d("dsds", jsonObject1.toString());


                    AlertDialog.Builder dialog = new AlertDialog.Builder(CreateEventFrgmnt.this);
                    dialog.setCancelable(false);
                    dialog.setMessage("Are you sure you want to Create this Event ?");
                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            s=tv_time.getText().toString();
                            String[] sep;
                            sep=s.split(":");
                            Log.d("time",sep[0]);
                            Log.d("time",sep[1]);

                            String[]seprate;
                            seprate=sep[1].split(" ");
                            Log.d("timeee",seprate[0]);



                            User b = (User) getApplicationContext();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);
                            progress.setVisibility(View.VISIBLE);

                            Call<CreateEventTechrBean> call = cr.crt_event(b.school_id, tv_date.getText().toString(), tv_date1.getText().toString(), tv_time.getText().toString(), sep[0], seprate[0], type.getSelectedItem().toString(), noteString, b.user_id, b.user_type, "Parent", jsonObject.toString(), jsonObject1.toString());

                            call.enqueue(new Callback<CreateEventTechrBean>() {
                                @Override
                                public void onResponse(Call<CreateEventTechrBean> call, Response<CreateEventTechrBean> response) {

                                    if (response.body().getStatus().equals("1")) {
                                        Toast.makeText(CreateEventFrgmnt.this, "Event has been Created Successfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(CreateEventFrgmnt.this, "Event creation Failed!", Toast.LENGTH_SHORT).show();

                                        finish();
                                    }

                                    progress.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<CreateEventTechrBean> call, Throwable throwable) {

                                    progress.setVisibility(View.GONE);

                                }
                            });


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
            tv_date1.setText(date);

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
            datee = formattedDate;
            tv_date.setText(datee);


        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            String AM_PM;
            if (hour < 12) {
                AM_PM = "AM";
            } else {
                AM_PM = "PM";
            }

            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        private String getTime(int hr, int min) {
            Time tme = new Time(hr, min, 0);//seconds by default set to zero
            Format formatter;
            formatter = new SimpleDateFormat("h:mm a");
            return formatter.format(tme);


        }

        //onTimeSet() callback method
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //Do something with the user chosen time
            //Get reference of host activity (XML Layout File) TextView widget

            //Set a message for user

            //Display the user changed time on TextView
            tv_time.setText(getTime(hourOfDay, minute));


        }

    }


}