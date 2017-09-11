package com.eduschool.eduschoolapp.StudentSummary;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.Library.Album;
import com.eduschool.eduschoolapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/27/2017.
 */

public class ParentAttendanceFrgmnt extends Fragment {
    private RecyclerView recyclerView;
    private List<Album> albumList;

    private AdapterCalendar adapter;
    public ParentAttendanceFrgmnt() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_view_attendance, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        albumList = new ArrayList<>();
        adapter=new AdapterCalendar(getContext(),albumList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);

        return view;

    }



}
