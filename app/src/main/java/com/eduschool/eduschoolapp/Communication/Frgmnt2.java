package com.eduschool.eduschoolapp.Communication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 6/26/2017.
 */

public class Frgmnt2 extends Fragment {
    Toolbar toolbar;
    TextView birthday,compose_msg;

    public Frgmnt2() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.communication_frgmnt2, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm= ((TeacherHome)getContext()).getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        birthday=(TextView)v.findViewById(R.id.birthday);
        compose_msg=(TextView)v.findViewById(R.id.compose_msg);
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),SendBirthdayCard.class);
                startActivity(intent);

            }
        });

        compose_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),ComposeMessage.class);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Parent Request");

        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}