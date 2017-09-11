package com.eduschool.eduschoolapp.Survey;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SurveyAnsPOJO.SurveyAnsBean;
import com.eduschool.eduschoolapp.SurveyQusPOJO.SurveyQusBean;
import com.eduschool.eduschoolapp.UpdateSurveyPOJO.UpdateSurveyBean;
import com.eduschool.eduschoolapp.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class TakeSurveyParent extends AppCompatActivity {
    Toolbar toolbar;
    Button submit;
    ProgressBar progress;
    RadioGroup grp;

    boolean radioCheck = false;
    String Id, QusId, status;
    String ansId, i;
    RadioButton btn;
    List<String> list;
    TextView qus, ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey_parent);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Survey");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progress = (ProgressBar) findViewById(R.id.progress);
        grp = (RadioGroup) findViewById(R.id.radio);
        submit = (Button) findViewById(R.id.submit);

        qus = (TextView) findViewById(R.id.qus);
        ans = (TextView) findViewById(R.id.ans);

        Id = getIntent().getStringExtra("SurveyId");
        QusId = getIntent().getStringExtra("QusId");
        status = getIntent().getStringExtra("Status");


        list = new ArrayList();
        if (status.equals("Complete")) {
            submit.setVisibility(View.GONE);
            ans.setVisibility(View.VISIBLE);

            User b = (User) getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);


            Call<SurveyQusBean> call = cr.survey_qus(Id, QusId);

            call.enqueue(new Callback<SurveyQusBean>() {
                @Override
                public void onResponse(Call<SurveyQusBean> call, Response<SurveyQusBean> response) {

                    qus.setText("Qus : " + response.body().getSurveyQuestion().get(0).getQuestion());

                    for (int i = 0; i < response.body().getSurveyQuestion().get(0).getQuestionOption().size(); i++) {
                        btn = new RadioButton(TakeSurveyParent.this);
                        btn.setText(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionValue());
                        btn.setId(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionId());

                        grp.addView(btn);

                    }

                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<SurveyQusBean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });


            Log.d("idd",b.user_type);
            Log.d("idd",b.user_id);
            Log.d("idd",Id);
            Log.d("idd",QusId);
            Call<SurveyAnsBean> call1 = cr.survey_ans(b.user_type, b.user_id, Id, QusId);

            call1.enqueue(new Callback<SurveyAnsBean>() {
                @Override
                public void onResponse(Call<SurveyAnsBean> call1, Response<SurveyAnsBean> response) {

                    i = response.body().getSurveyAnswer().get(0).getYourAnswer().toString();

                    ans.setText("Your Ans : "+i);

                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<SurveyAnsBean> call1, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });


        } else {


            User b = (User) getApplicationContext();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<SurveyQusBean> call = cr.survey_qus(Id, QusId);

            call.enqueue(new Callback<SurveyQusBean>() {
                @Override
                public void onResponse(Call<SurveyQusBean> call, Response<SurveyQusBean> response) {

                    qus.setText("Qus : " + response.body().getSurveyQuestion().get(0).getQuestion());

                    for (int i = 0; i < response.body().getSurveyQuestion().get(0).getQuestionOption().size(); i++) {
                        RadioButton btn = new RadioButton(TakeSurveyParent.this);
                        btn.setText(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionValue());
                        btn.setId(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionId());

                        grp.addView(btn);

                        grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                            @Override
                            public void onCheckedChanged(RadioGroup arg0, int arg1) {

                                int selectedId = grp.getCheckedRadioButtonId();
                                Log.i("ID", String.valueOf(selectedId));
                                ansId = Integer.toString(selectedId);

                            }
                        });

                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                radioCheck = true;

                            }
                        });

                    }


                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<SurveyQusBean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioCheck == false) {
                    Toast.makeText(TakeSurveyParent.this, "Please Select an option", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(TakeSurveyParent.this);
                    dialog.setCancelable(false);
                    dialog.setMessage("Are you sure you want to submit ?");
                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //Action for "Delete".

                            User b = (User) getApplicationContext();


                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);
                            progress.setVisibility(View.VISIBLE);

                            Call<UpdateSurveyBean> call = cr.update_qus(b.school_id, "Parent", b.user_id, Id, QusId, ansId);

                            call.enqueue(new Callback<UpdateSurveyBean>() {
                                @Override
                                public void onResponse(Call<UpdateSurveyBean> call, Response<UpdateSurveyBean> response) {

                               /* qus.setText("Qus : "+response.body().getSurveyQuestion().get(0).getQuestion());

                                for(int i = 0 ; i < response.body().getSurveyQuestion().get(0).getQuestionOption().size() ; i++)
                                {
                                    RadioButton btn = new RadioButton(Take_Survey.this);
                                    btn.setText(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionValue());
                                    btn.setId(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionId());
                                    grp.addView(btn);

                                }
*/

                                    if (response.body().getSurveyListteacher().equals("1")) {
                                        Toast.makeText(TakeSurveyParent.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(TakeSurveyParent.this, "Not Submitted Successfully!", Toast.LENGTH_SHORT).show();
                                    }

                                    progress.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<UpdateSurveyBean> call, Throwable throwable) {

                                    progress.setVisibility(View.GONE);

                                }
                            });


                            dialog.dismiss();
                            finish();
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

    @Override
    protected void onResume() {
        super.onResume();


    }
}

