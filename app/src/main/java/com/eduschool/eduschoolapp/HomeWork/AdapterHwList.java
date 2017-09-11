package com.eduschool.eduschoolapp.HomeWork;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomeworkList;

import java.util.ArrayList;
import java.util.List;


public class AdapterHwList extends RecyclerView.Adapter<AdapterHwList.myviewholder> {

    Context context;
    List<HomeworkList> list = new ArrayList<>();

    public AdapterHwList(Context context, List<HomeworkList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<HomeworkList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterHwList.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.hw_list_model, parent, false);

        return new myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterHwList.myviewholder holder, int position) {

        HomeworkList item = list.get(position);


        //loader.displayImage(item.get);
        holder.title.setText(item.getTitle());
        holder.className.setText(item.getClass_());
        holder.section.setText(item.getSection());
        holder.subject.setText("Subject : "+item.getSubject());





    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView title, section, subject;
        TextView className;
        CardView card;


        public myviewholder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            section=(TextView) itemView.findViewById(R.id.section);
            subject = (TextView) itemView.findViewById(R.id.subject);
            className = (TextView) itemView.findViewById(R.id.className);
            card = (CardView) itemView.findViewById(R.id.card);

           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String pos = list.get(getAdapterPosition()).getHomeworkId();

                    android.support.v4.app.FragmentManager fm=((AppCompatActivity)context).getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                    TeacherHwFrgmntTwo frag1 = new TeacherHwFrgmntTwo();
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