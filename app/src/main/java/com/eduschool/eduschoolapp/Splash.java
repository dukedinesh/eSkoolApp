package com.eduschool.eduschoolapp;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.LoginPOJO.Loginbean;
import com.eduschool.eduschoolapp.LoginPages.LoginPage;
import com.eduschool.eduschoolapp.Profile.TeacherProfile;
import com.eduschool.eduschoolapp.WelcomeSlider.WelcomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Splash extends AppCompatActivity {
    SharedPreferences pref;
    ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progress = (ProgressBar) findViewById(R.id.progress);

        pref = getSharedPreferences("mypref", MODE_PRIVATE);


        if (Objects.equals(pref.getString("type", ""), "Teacher")) {
            Timer t = new Timer();

            isNetworkConnectionAvailable();
            t.schedule(new TimerTask() {

                @Override
                public void run() {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            progress.setVisibility(View.VISIBLE);

                            User b = (User) getApplicationContext();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);

                            Call<Loginbean> call = cr.login(pref.getString("username", ""), pref.getString("pass", ""));

                            call.enqueue(new Callback<Loginbean>() {
                                @Override
                                public void onResponse(Call<Loginbean> call, Response<Loginbean> response) {
                                    User b = (User) getApplicationContext();

                                    if (Objects.equals(response.body().getStatus(), "0")) {
                                        Toast.makeText(Splash.this, "Invalid details", Toast.LENGTH_SHORT).show();
                                        progress.setVisibility(View.GONE);
                                    } else if (Objects.equals(response.body().getStatus(), "1")) {
                                        b.school_id = response.body().getSchoolId();
                                        b.user_id = response.body().getId();
                                        b.user_name = response.body().getTeacherName();
                                        b.user_type = response.body().getUserType();
                                        b.user_class = response.body().getClassId();
                                        b.user_section = response.body().getSectionId();
                                        b.class_teacher = response.body().getClassTeacher();
                                        b.class_Name = response.body().getClassName();
                                        b.section_Name = response.body().getSectionName();

                                        progress.setVisibility(View.GONE);

                                        Intent intent = new Intent(Splash.this, TeacherHome.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }

                                }

                                @Override
                                public void onFailure(Call<Loginbean> call, Throwable throwable) {

                                    progress.setVisibility(View.GONE);

                                }
                            });

                        }
                    });


                }

            }, 2000);


        } else if (Objects.equals(pref.getString("type", ""), "Parent")) {

            isNetworkConnectionAvailable();
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            User b = (User) getApplicationContext();
                            progress.setVisibility(View.VISIBLE);

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);

                            Call<Loginbean> call = cr.login(pref.getString("username", ""), pref.getString("pass", ""));

                            call.enqueue(new Callback<Loginbean>() {
                                @Override
                                public void onResponse(Call<Loginbean> call, Response<Loginbean> response) {
                                    User b = (User) getApplicationContext();

                                    if (Objects.equals(response.body().getStatus(), "0")) {
                                        Toast.makeText(Splash.this, "Invalid details", Toast.LENGTH_SHORT).show();
                                        progress.setVisibility(View.GONE);
                                    } else if (Objects.equals(response.body().getStatus(), "1")) {

                                        b.school_id = response.body().getSchoolId();
                                        b.user_id = response.body().getId();
                                        b.user_type = response.body().getUserType();
                                        b.user_name = response.body().getStudentName();
                                        b.user_class = response.body().getClassId();
                                        b.user_section = response.body().getSectionId();
                                        b.class_Name = response.body().getClassName();
                                        b.section_Name = response.body().getSectionName();


                                        progress.setVisibility(View.GONE);


                                        Intent intent = new Intent(Splash.this, ParentHome.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }

                                }

                                @Override
                                public void onFailure(Call<Loginbean> call, Throwable throwable) {

                                    progress.setVisibility(View.GONE);

                                }
                            });

                        }
                    });
                }
            }, 2000);


        } else {
            Timer t = new Timer();

            t.schedule(new TimerTask() {
                @Override
                public void run() {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent = new Intent(Splash.this, WelcomeActivity.class);
                            startActivity(intent);
                            finish();


                        }
                    });

                }
            }, 2000);
        }

    }

    public void checkNetworkConnection() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(Splash.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if (isConnected) {
            Log.d("Network", "Connected");
            return true;
        } else {
            checkNetworkConnection();
            Log.d("Network", "Not Connected");
            return false;
        }
    }


}


