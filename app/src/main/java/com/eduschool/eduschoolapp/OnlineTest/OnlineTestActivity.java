package com.eduschool.eduschoolapp.OnlineTest;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


import com.eduschool.eduschoolapp.MyViewPager;
import com.eduschool.eduschoolapp.R;


public class OnlineTestActivity extends AppCompatActivity {
    TextView countDown;
    Toolbar toolbar;
    MyAdapter mAdapter;
    MyViewPager mPager;
    TextView count;
    Button start;
    final static Fragment[] layouts = new Fragment[]{QuestionFragment.newInstance(0), QuestionFragment_two.newInstance(1)};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_test);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Online Test Heading");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        android.app.FragmentManager fm = this.getFragmentManager();
        //TakeTestDailog ratingBarFragment = new TakeTestDailog();
        //ratingBarFragment.show(fm, "dailog");


        final Dialog dialog = new Dialog(OnlineTestActivity.this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.online_test_dailog);

        dialog.show();


        Button start = (Button)dialog.findViewById(R.id.start_test);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hoursToGo = 0;
                int minutesToGo = 0;
                int secondsToGo = 300;
                int millisToGo = secondsToGo * 1000 + minutesToGo * 1000 * 60 + hoursToGo * 1000 * 60 * 60;

                dialog.dismiss();
                new CountDownTimer(millisToGo, 1000) {

                    @Override
                    public void onTick(long millis) {
                        int seconds = (int) (millis / 1000) % 60;
                        int minutes = (int) ((millis / (1000 * 60)) % 60);
                        int hours = (int) ((millis / (1000 * 60 * 60)) % 24);
                        String text = String.format("Time Remaining:%02d:%02d:%02d ", hours, minutes, seconds);
                        countDown.setText(text);
                    }

                    @Override
                    public void onFinish() {
                        countDown.setText("Done!");
                    }
                }.start();


            }
        });
        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    finish();
                    dialog.dismiss();
                }
                return true;
            }
        });

        count = (TextView) findViewById(R.id.count);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = (MyViewPager) findViewById(R.id.viewPager);
        mPager.setAdapter(mAdapter);

        mPager.setSwipeable(false);

        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(2);
                count.setText("2/30");
            }
        });
        button = (Button) findViewById(R.id.prev);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(0);
                count.setText("1/30");
            }
        });


        countDown = (TextView) findViewById(R.id.countDown);







    }

    public static class MyAdapter extends FragmentStatePagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public Fragment getItem(int position) {

            return layouts[position];
        }
    }


}