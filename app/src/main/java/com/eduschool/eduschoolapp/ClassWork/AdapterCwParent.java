package com.eduschool.eduschoolapp.ClassWork;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eduschool.eduschoolapp.ClassWrkParentPOJO.SubjectList;
import com.eduschool.eduschoolapp.HomeWork.AdapterParent;
import com.eduschool.eduschoolapp.HomeWork.HomeWorkFrgmntTwo;

import com.eduschool.eduschoolapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/16/2017.
 */

public class AdapterCwParent extends RecyclerView.Adapter<AdapterCwParent.myviewholder> {

    Context context;
    List<SubjectList> list = new ArrayList<>();



    public AdapterCwParent(Context context, List<SubjectList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<SubjectList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterCwParent.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.parent_hw_model, parent, false);

        return new AdapterCwParent.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterCwParent.myviewholder holder, int position) {

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
                    ClassWorkFrgmntTwo frag1 = new ClassWorkFrgmntTwo();
                    Bundle bundle=new Bundle();
                    bundle.putString("message", pos);
                    frag1.setArguments(bundle);
                    ft.replace(R.id.replace, frag1);
                    ft.addToBackStack(null);
                    ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                    ft.commit();




                }
            });

        }


        @Override
        public void onClick(View view) {


        }
    }
}