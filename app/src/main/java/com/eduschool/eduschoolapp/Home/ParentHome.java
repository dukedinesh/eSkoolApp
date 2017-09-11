package com.eduschool.eduschoolapp.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.Calender.CalenderParent;
import com.eduschool.eduschoolapp.Library.ParentLibrary1;
import com.eduschool.eduschoolapp.LoginPOJO.Loginbean;
import com.eduschool.eduschoolapp.LoginPages.LoginPage;
import com.eduschool.eduschoolapp.LoginPages.MainLogin;
import com.eduschool.eduschoolapp.Notifications.ParentNotificationFrgmnt;
import com.eduschool.eduschoolapp.StayAhead.StayAheadHome;
import com.eduschool.eduschoolapp.StudentSummary.StudentSummaryParent;
import com.eduschool.eduschoolapp.SyllabusManagement.ParentAcademic;
import com.eduschool.eduschoolapp.Attendance.ParentAttendanceFrgmnt;
import com.eduschool.eduschoolapp.ClassWork.ClassWorkFrgmnt;
import com.eduschool.eduschoolapp.ExamAndResults.ExamFrgmntOne;
import com.eduschool.eduschoolapp.Fees.Fees;
import com.eduschool.eduschoolapp.Gallery.GalleryParent;
import com.eduschool.eduschoolapp.HomeWork.HomeWorkFrgmnt;
import com.eduschool.eduschoolapp.OnlineTest.OnlineTestSummery;
import com.eduschool.eduschoolapp.Profile.ParentProfile;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.RaiseRequest.RaiseReqquestHome;
import com.eduschool.eduschoolapp.Survey.SurveyFrgmntParent1;
import com.eduschool.eduschoolapp.TimeTable.TimeTableParent;
import com.eduschool.eduschoolapp.Transport.TransportParent;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.WishingCards.WishingCardsFrgmnt;

public class ParentHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawer;
    boolean isCollapsed = true;
    static Context context;
    LinearLayout hotelName;
    SharedPreferences pref;
    SharedPreferences.Editor edit;
    TextView academic,user_name, attendance, logout, home, notification, homewrk, classwrk, syllabus, exm, calendar, studentSummary, library, timeTable, gallery, sendCards, survey, stayAhead, onlineTest, raiseRequest, fees, transport, profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);
        context = ParentHome.this;


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        pref = getSharedPreferences("mypref" , MODE_PRIVATE);
        edit = pref.edit();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ParentHomeFrgmnt frag1 = new ParentHomeFrgmnt();
        ft.replace(R.id.replace, frag1);
        ft.commit();


        hotelName = (LinearLayout) findViewById(R.id.hotel_name_menu);

      /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);*//*
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        toggle.syncState();

      NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        drawer = (DrawerLayout) findViewById(R.id.drawer_asiana);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        academic = (TextView) findViewById(R.id.academic);
        attendance = (TextView) findViewById(R.id.attendance);
        logout = (TextView) findViewById(R.id.logout);
        home = (TextView) findViewById(R.id.home1);
        notification = (TextView) findViewById(R.id.notification1);
        homewrk = (TextView) findViewById(R.id.homewrk);
        classwrk = (TextView) findViewById(R.id.classwrk);
        syllabus = (TextView) findViewById(R.id.syllabus);
        exm = (TextView) findViewById(R.id.exm);
        studentSummary = (TextView) findViewById(R.id.studentSummary);
        library = (TextView) findViewById(R.id.library1);
        timeTable = (TextView) findViewById(R.id.timeTable);
        gallery = (TextView) findViewById(R.id.gallery1);
        sendCards = (TextView) findViewById(R.id.sendCards);
        survey = (TextView) findViewById(R.id.survey1);
        calendar = (TextView) findViewById(R.id.calendar);
        stayAhead = (TextView) findViewById(R.id.stayAhead);
        onlineTest = (TextView) findViewById(R.id.onlineTest);
        raiseRequest = (TextView) findViewById(R.id.raiseRequest);
        fees = (TextView) findViewById(R.id.fees);
        transport = (TextView) findViewById(R.id.transport);
        profile = (TextView) findViewById(R.id.profile);
        user_name = (TextView) findViewById(R.id.user_name);

        User b = (User) getApplicationContext();
        user_name.setText("Welcome " +b.user_name);

        academic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ParentHomeFrgmnt frag1 = new ParentHomeFrgmnt();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();


            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ParentHome.this);
                builder.setMessage("Are you sure you want to logout?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {


                        Intent intent = new Intent(ParentHome.this, LoginPage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                        edit.remove("username");
                        edit.remove("type");
                        edit.remove("pass");
                        edit.apply();
                        finish();
                        startActivity(intent);
                        dialog.dismiss();



                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ParentAttendanceFrgmnt frag1 = new ParentAttendanceFrgmnt();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                ft.commit();

                drawer.closeDrawer(GravityCompat.START);

            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ParentHomeFrgmnt frag1 = new ParentHomeFrgmnt();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ParentNotificationFrgmnt frag1 = new ParentNotificationFrgmnt();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        homewrk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                HomeWorkFrgmnt frag1 = new HomeWorkFrgmnt();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        classwrk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ClassWorkFrgmnt frag1 = new ClassWorkFrgmnt();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ParentAcademic frag1 = new ParentAcademic();
                ft.replace(R.id.replace, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        exm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ExamFrgmntOne frag1 = new ExamFrgmntOne();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        homewrk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                HomeWorkFrgmnt frag1 = new HomeWorkFrgmnt();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        studentSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplication(), StudentSummaryParent.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ParentLibrary1 frag1 = new ParentLibrary1();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TimeTableParent frag1 = new TimeTableParent();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                GalleryParent frag1 = new GalleryParent();
                ft.replace(R.id.replace, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        sendCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                WishingCardsFrgmnt frag1 = new WishingCardsFrgmnt();
                ft.replace(R.id.replace, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                SurveyFrgmntParent1 frag1 = new SurveyFrgmntParent1();
                ft.replace(R.id.replace, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                CalenderParent frag1 = new CalenderParent();
                ft.replace(R.id.replace, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        stayAhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                StayAheadHome frag1 = new StayAheadHome();
                ft.replace(R.id.replace, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        onlineTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                OnlineTestSummery frag1 = new OnlineTestSummery();
                ft.replace(R.id.replace, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        raiseRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                RaiseReqquestHome frag1 = new RaiseReqquestHome();
                ft.replace(R.id.replace, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fees frag1 = new Fees();
                ft.replace(R.id.replace, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TransportParent frag1 = new TransportParent();
                ft.replace(R.id.replace, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ParentProfile frag1 = new ParentProfile();
                ft.replace(R.id.replace, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        if (isCollapsed) {
            academic.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
            hotelName.setVisibility(View.GONE);
        } else {
            academic.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up, 0);
            hotelName.setVisibility(View.VISIBLE);
        }

        academic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollapsed) {
                    academic.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up, 0);
                    hotelName.setVisibility(View.VISIBLE);
                    isCollapsed = false;
                } else {
                    academic.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
                    hotelName.setVisibility(View.GONE);
                    isCollapsed = true;
                }


            }
        });
    }


    boolean doubleBackToExitPressedOnce = false;

    @SuppressLint("RestrictedApi")
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        boolean b;

        User u = (User) getApplicationContext();
        b = u.back;

        if (!b) {
            super.onBackPressed();
        } else {

            if (doubleBackToExitPressedOnce) {
                //super.onBackPressed();

                finish();

                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.attendance) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ParentAttendanceFrgmnt frag1 = new ParentAttendanceFrgmnt();
            ft.replace(R.id.replace, frag1);
            /*ft.addToBackStack(null);*/
            ft.commit();


        } else if (id == R.id.home_work) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            HomeWorkFrgmnt frag1 = new HomeWorkFrgmnt();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.class_work) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ClassWorkFrgmnt frag1 = new ClassWorkFrgmnt();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.syllabus_management) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ParentAcademic frag1 = new ParentAcademic();
            ft.replace(R.id.replace, frag1);
            // ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.notification) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ParentNotificationFrgmnt frag1 = new ParentNotificationFrgmnt();
            ft.replace(R.id.replace, frag1);
            // ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.wishing_cards) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            WishingCardsFrgmnt frag1 = new WishingCardsFrgmnt();
            ft.replace(R.id.replace, frag1);
            // ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.survey) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            SurveyFrgmntParent1 frag1 = new SurveyFrgmntParent1();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.student_summery) {

            Intent intent = new Intent(ParentHome.this, StudentSummaryParent.class);
            startActivity(intent);

        } else if (id == R.id.online_test) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            OnlineTestSummery frag1 = new OnlineTestSummery();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.calender) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            CalenderParent frag1 = new CalenderParent();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.exam_result) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ExamFrgmntOne frag1 = new ExamFrgmntOne();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.library) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ParentLibrary1 frag1 = new ParentLibrary1();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.stay_ahead) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            StayAheadHome frag1 = new StayAheadHome();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.raise_request) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            RaiseReqquestHome frag1 = new RaiseReqquestHome();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.time_table) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TimeTableParent frag1 = new TimeTableParent();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.gallery) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            GalleryParent frag1 = new GalleryParent();
            ft.replace(R.id.replace, frag1);
            // ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.profile) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ParentProfile frag1 = new ParentProfile();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.home) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ParentHomeFrgmnt frag1 = new ParentHomeFrgmnt();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.transport) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TransportParent frag1 = new TransportParent();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.fees) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fees frag1 = new Fees();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.log_out) {


            AlertDialog.Builder builder = new AlertDialog.Builder(ParentHome.this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to logout?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog

                    dialog.dismiss();
                    Intent intent = new Intent(ParentHome.this, LoginPage.class);
                    startActivity(intent);


                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home");
        User u = (User) getApplicationContext();

        u.back = true;
    }


}
