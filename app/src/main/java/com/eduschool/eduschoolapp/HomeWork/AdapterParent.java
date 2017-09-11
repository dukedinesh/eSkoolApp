package com.eduschool.eduschoolapp.HomeWork;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eduschool.eduschoolapp.HomewrkParentPOJO.ClasssubjectList;
import com.eduschool.eduschoolapp.HomewrkParentPOJO.SubjectList;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomeworkList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/14/2017.
 */

public class AdapterParent extends RecyclerView.Adapter<AdapterParent.myviewholder> {

    Context context;
    List<SubjectList> list = new ArrayList<>();



    public AdapterParent(Context context, List<SubjectList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<SubjectList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterParent.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.parent_hw_model, parent, false);

        return new AdapterParent.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterParent.myviewholder holder, int position) {

        SubjectList item = list.get(position);

        holder.subject.setText(item.getSubjectName());
        if (item.getNewclasswork().equals("0")){
            holder.layout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView subject;
        RelativeLayout layout;

        public myviewholder(View itemView) {
            super(itemView);

            subject = (TextView) itemView.findViewById(R.id.subject);
            layout=(RelativeLayout)itemView.findViewById(R.id.layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String pos = list.get(getAdapterPosition()).getSubjectId();

                    android.support.v4.app.FragmentManager fm=((AppCompatActivity)context).getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                    HomeWorkFrgmntTwo frag1 = new HomeWorkFrgmntTwo();
                    Bundle bundle=new Bundle();
                    bundle.putString("message", pos);
                    frag1.setArguments(bundle);
                    ft.replace(R.id.replace, frag1);
                    ft.addToBackStack(null);
                    ft.commit();




                }
            });

        }


        @Override
        public void onClick(View view) {
            int pos=getAdapterPosition();


/*
            Bundle bundle=new Bundle();
            bundle.putString("message", "From Activity");

            android.support.v4.app.FragmentManager fm=((AppCompatActivity)context).getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            TeacherHwFrgmntTwo frag1 = new TeacherHwFrgmntTwo();
            frag1.setArguments(bundle);
            ft.replace(R.id.replace, frag1);
            ft.addToBackStack(null);
            ft.commit();*/
        }
    }
}