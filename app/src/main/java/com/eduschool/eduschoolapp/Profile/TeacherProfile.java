package com.eduschool.eduschoolapp.Profile;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ChangePssPOJO.PasswrdChngebean;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.LoginPOJO.Loginbean;
import com.eduschool.eduschoolapp.LoginPages.LoginPage;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by User on 5/5/2017.
 */

public class TeacherProfile extends Fragment {
    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button change_password;
    SharedPreferences pref;
    SharedPreferences.Editor edit;
    TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.teacher_profile, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);


        change_password = (Button) v.findViewById(R.id.change_password);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        change_password = (Button) v.findViewById(R.id.change_password);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        name=(TextView)v.findViewById(R.id.name);
        final User b = (User) getActivity().getApplicationContext();

        name.setText(b.user_name);

        tabLayout.addTab(tabLayout.newTab().setText("Personal Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Emergency Contact"));
        tabLayout.addTab(tabLayout.newTab().setText("Work Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Request Leave"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pref = getContext().getSharedPreferences("mypref", MODE_PRIVATE);
        edit = pref.edit();

        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setupWithViewPager(viewPager);


        change_password.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.teacher_profile_popup);

                final EditText oldpass, newpass, confrmpass;
                final ProgressBar progress;
                Button submit;

                oldpass = (EditText) dialog.findViewById(R.id.oldpass);
                newpass = (EditText) dialog.findViewById(R.id.newpass);
                confrmpass = (EditText) dialog.findViewById(R.id.confrmpass);
                progress = (ProgressBar) dialog.findViewById(R.id.progress);
                submit = (Button) dialog.findViewById(R.id.submit);


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        final String Soldpass, Snewpass, SconfrmPass;
                        Soldpass = oldpass.getText().toString().trim();
                        Snewpass = newpass.getText().toString().trim();
                        SconfrmPass = confrmpass.getText().toString().trim();

                        //Toast.makeText(getContext(), b.school_id, Toast.LENGTH_SHORT).show();

                        if (Soldpass.length() > 0) {

                            if (Snewpass.length() > 0) {
                                if (SconfrmPass.length() > 0) {

                                    progress.setVisibility(View.VISIBLE);


                                    if (!Snewpass.equals(SconfrmPass)) {

                                        Toast.makeText(getContext(), "New Password and Confirm Password are not similar", Toast.LENGTH_SHORT).show();
                                        progress.setVisibility(View.GONE);
                                    } else {


                                        Retrofit retrofit = new Retrofit.Builder()
                                                .baseUrl(b.baseURL)
                                                .addConverterFactory(ScalarsConverterFactory.create())
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();

                                        AllAPIs cr = retrofit.create(AllAPIs.class);


                                        Call<PasswrdChngebean> call1 = cr.chngrPass(b.school_id, b.user_type, Snewpass, Soldpass, b.user_id);


                                        call1.enqueue(new Callback<PasswrdChngebean>() {
                                            @Override
                                            public void onResponse(Call<PasswrdChngebean> call, Response<PasswrdChngebean> response) {


                                                if (response.body().getPasswordChange().get(0).getStatus().equals("1")) {

                                                    edit.putString("pass", SconfrmPass);
                                                    edit.commit();

                                                    Toast.makeText(getContext(), "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                                                    newpass.setText("");
                                                    oldpass.setText("");
                                                    confrmpass.setText("");
                                                    progress.setVisibility(View.GONE);
                                                } else {
                                                    progress.setVisibility(View.GONE);
                                                    Toast.makeText(getContext(), "Incorrect details. Try Again !", Toast.LENGTH_SHORT).show();

                                                }

                                            }

                                            @Override
                                            public void onFailure(Call<PasswrdChngebean> call, Throwable throwable) {
                                                progress.setVisibility(View.GONE);
                                            }
                                        });


                                    }

                                } else {
                                    confrmpass.setError("Invalid Password");
                                }
                            } else {
                                newpass.setError("Invalid Password");
                            }
                        } else {
                            oldpass.setError("Invalid Password");
                        }
                    }
                });


                dialog.show();
            }
        });


        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("My Profile");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private String[] tabTitles = new String[]{"Work Info", "Personal Info", "Emergency Contact", "Request Leave"};

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    TeacherFragmentOne tab1 = new TeacherFragmentOne();
                    return tab1;
                case 1:
                    TeacherFrgmntTwo tab2 = new TeacherFrgmntTwo();
                    return tab2;
                case 2:
                    TeacherFragmentThree tab3 = new TeacherFragmentThree();
                    return tab3;
                case 3:
                    TeacherFrmgntFour tab4 = new TeacherFrmgntFour();
                    return tab4;

                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}

