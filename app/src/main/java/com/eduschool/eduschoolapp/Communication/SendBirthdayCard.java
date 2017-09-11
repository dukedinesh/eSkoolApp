package com.eduschool.eduschoolapp.Communication;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.BirthStuListPOJO.BirthStuList;
import com.eduschool.eduschoolapp.BirthStuListPOJO.BirthStuListBean;
import com.eduschool.eduschoolapp.BirthTechrListPOJO.BirthTechrList;
import com.eduschool.eduschoolapp.BirthTechrListPOJO.BirthTechrListBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SendBdyTeacherPOJO.SendBdyTeacherBean;
import com.eduschool.eduschoolapp.SendBirthPOJO.SendBirthBean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.WishingCards.Adapter;
import com.eduschool.eduschoolapp.WishingCards.AdapterBean;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SendBirthdayCard extends AppCompatActivity {
    Toolbar toolbar;
    Spinner type;
    List<String> dummyList;
    ProgressBar progress;
    TextView noBdyText;
    LinearLayout layout;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    List<BirthTechrList> list;
    List<BirthStuList> list1;
    AdapterC adapterC;
    Adapter adapter;
    List<AdapterBean> s;
    List<AdapterBean> ss;
    ImageView img1, img2, img3, img4, img5;
    List<String> studentId;
    Button send;
    String attach,Usertype;
    RadioGroup radioGroup;


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishing_card_frgmnt_teacher);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Send Birthday Card");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        layout = (LinearLayout) findViewById(R.id.layout);
        send = (Button) findViewById(R.id.send);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        progress = (ProgressBar) findViewById(R.id.progress);
        noBdyText = (TextView) findViewById(R.id.noBdyText);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        manager = new GridLayoutManager(SendBirthdayCard.this, 1);

        Picasso.with(SendBirthdayCard.this).load("http://technobrix.in/newtbx/eduschool/TBXadmin/images/card1.jpg").into(img1);
        Picasso.with(SendBirthdayCard.this).load("http://technobrix.in/newtbx/eduschool/TBXadmin/images/card2.jpg").into(img2);
        Picasso.with(SendBirthdayCard.this).load("http://technobrix.in/newtbx/eduschool/TBXadmin/images/card3.jpg").into(img3);
        Picasso.with(SendBirthdayCard.this).load("http://technobrix.in/newtbx/eduschool/TBXadmin/images/card4.jpg").into(img4);
        Picasso.with(SendBirthdayCard.this).load("http://technobrix.in/newtbx/eduschool/TBXadmin/images/card5.jpg").into(img5);

        type = (Spinner) findViewById(R.id.type);
        dummyList = new ArrayList<>();
        dummyList.add("Select");
        dummyList.add("Student");
        dummyList.add("Teacher");


        list = new ArrayList<>();
        list1 = new ArrayList<>();
        s = new ArrayList<>();
        ss = new ArrayList<>();
        studentId = new ArrayList<>();


        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(SendBirthdayCard.this,
                android.R.layout.simple_list_item_1, dummyList);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adp1);


        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 1) {

                    Usertype="Parent";
                    final User b = (User) getApplicationContext();
                    final Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final AllAPIs cr = retrofit.create(AllAPIs.class);

                    progress.setVisibility(View.VISIBLE);

                    layout.setVisibility(View.VISIBLE);
                    Call<BirthStuListBean> call = cr.stu_bithday(b.school_id);

                    call.enqueue(new Callback<BirthStuListBean>() {
                        @Override
                        public void onResponse(Call<BirthStuListBean> call, Response<BirthStuListBean> response) {

                            Log.d("dsd", "sdc");
                            if (response.body().getBirthStuList().size() < 0) {
                                noBdyText.setVisibility(View.VISIBLE);
                            }

                            adapter = new Adapter(SendBirthdayCard.this, list1);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(manager);

                            adapter.setGridData(response.body().getBirthStuList(), ss);
                            adapter.notifyDataSetChanged();
                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<BirthStuListBean> call, Throwable throwable) {

                            progress.setVisibility(View.GONE);

                        }
                    });

                } else if (position == 2) {

                    Usertype="Teacher";
                    layout.setVisibility(View.VISIBLE);
                    final User b = (User) getApplicationContext();
                    final Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final AllAPIs cr = retrofit.create(AllAPIs.class);

                    progress.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.VISIBLE);
                    Call<BirthTechrListBean> call = cr.birth_teacher(b.school_id);
                    call.enqueue(new Callback<BirthTechrListBean>() {
                        @Override
                        public void onResponse(Call<BirthTechrListBean> call, Response<BirthTechrListBean> response) {

                            if (response.body().getBirthTechrList().size() < 0) {
                                noBdyText.setVisibility(View.VISIBLE);
                            }

                            adapterC = new AdapterC(SendBirthdayCard.this, list);
                            recyclerView.setAdapter(adapterC);
                            recyclerView.setLayoutManager(manager);

                            adapterC.setGridData(response.body().getBirthTechrList(), s);
                            adapterC.notifyDataSetChanged();
                            progress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<BirthTechrListBean> call, Throwable throwable) {

                            progress.setVisibility(View.GONE);

                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idx = radioGroup.getCheckedRadioButtonId();
                {
                    if (idx == R.id.radio1) {

                        attach = "card1.jpg";

                    } else if (idx == R.id.radio2) {

                        attach = "card2.jpg";
                    } else if (idx == R.id.radio3) {

                        attach = "card3.jpg";
                    } else if (idx == R.id.radio4) {
                        attach = "card4.jpg";
                    } else if (idx == R.id.radio5) {
                        attach = "card5.jpg";
                    }
                }

                Log.d("sbfdfb ", attach);


                JSONArray jsonArray = new JSONArray();

                List<AdapterBean> list1 = adapter.getCheckList();

                Log.d("sizezee", String.valueOf(list1.size()));

                for (int i = 0; i < list1.size(); i++) {
                    JSONObject object = new JSONObject();

                    try {

                        object.put("Id", list1.get(i).getId());
                        jsonArray.put(object);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                final JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("stu_id", jsonArray);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("dsds", jsonObject.toString());


                AlertDialog.Builder dialog = new AlertDialog.Builder(SendBirthdayCard.this);
                dialog.setCancelable(false);
                dialog.setMessage("Are you sure you want to send card ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        User b = (User) SendBirthdayCard.this.getApplicationContext();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        final AllAPIs cr = retrofit.create(AllAPIs.class);
                        progress.setVisibility(View.VISIBLE);

                        Call<SendBdyTeacherBean> call = cr.bdy_teacher(b.school_id, b.user_type, b.user_id, Usertype, attach, jsonObject.toString());


                        call.enqueue(new Callback<SendBdyTeacherBean>() {
                            @Override
                            public void onResponse(Call<SendBdyTeacherBean> call, Response<SendBdyTeacherBean> response) {


                                if (response.body().getStatus().equals("1")) {
                                    Toast.makeText(SendBirthdayCard.this, "Birthday Card Send Successfully.", Toast.LENGTH_SHORT).show();
                                    FragmentManager fm=getFragmentManager();
                                    fm.popBackStack();
                                } else {
                                    Toast.makeText(SendBirthdayCard.this, "Birthday Card did not send Successfully.", Toast.LENGTH_SHORT).show();

                                }

                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<SendBdyTeacherBean> call, Throwable throwable) {
                                Log.d("imagg", String.valueOf(throwable));
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
        });


    }
}
