package com.eduschool.eduschoolapp.Attendance;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.ClassWork.AdapterClsWrkList;
import com.eduschool.eduschoolapp.ClassWork.TeacherClsWrk2;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassworkList;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentList;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by user on 8/19/2017.
 */

public class AdapterMrkAttndnc extends RecyclerView.Adapter<AdapterMrkAttndnc.myviewholder> {



    Context context;
    List<StudentList> list = new ArrayList<>();
    boolean aBoolean = false, pBoolean = true, lBoolean = false;
    List<String>p=new ArrayList<>();

    public AdapterMrkAttndnc(Context context, List<StudentList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<StudentList> list,List<String>p) {
        this.list = list;
        this.p=p;
        notifyDataSetChanged();
    }
    public List<String> getCheckList(){


        return p;
    }

    @Override
    public AdapterMrkAttndnc.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.mark_attndnc_model, parent, false);

        return new AdapterMrkAttndnc.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterMrkAttndnc.myviewholder holder, final int position) {

        StudentList item = list.get(position);


        holder.name.setText(item.getStudentName());


            holder.no.setText(String.valueOf(position+1));


        holder.a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                p.add(position,"0");
                pBoolean = false;
                holder.a.setBackground(context.getResources().getDrawable(R.drawable.absentcheckbox));
                holder.a.setTextColor(Color.WHITE);
                holder.p.setTextColor(Color.BLACK);
                holder.l.setTextColor(Color.BLACK);
                holder.p.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                holder.l.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
            }
        });


        holder.p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.add(position,"1");
                holder.p.setBackground(context.getResources().getDrawable(R.drawable.checkbox));
                holder.p.setTextColor(Color.WHITE);
                holder.a.setTextColor(Color.BLACK);
                holder.l.setTextColor(Color.BLACK);
                holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                holder.l.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
            }
        });

        holder.l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.add(position,"2");
                holder.l.setBackground(context.getResources().getDrawable(R.drawable.leavecheckbox));
                holder.l.setTextColor(Color.WHITE);
                holder.p.setTextColor(Color.BLACK);
                holder.a.setTextColor(Color.BLACK);
                holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                holder.p.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView name, p, a,l,no;



        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            p=(TextView) itemView.findViewById(R.id.p);
            a = (TextView) itemView.findViewById(R.id.a);
            l = (TextView) itemView.findViewById(R.id.l);
            no = (TextView) itemView.findViewById(R.id.no);

          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String pos = list.get(getAdapterPosition()).getStudentId();

                    android.support.v4.app.FragmentManager fm=((AppCompatActivity)context).getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    TeacherClsWrk2 frag1 = new TeacherClsWrk2();
                    Bundle bundle=new Bundle();
                    bundle.putString("message", pos);
                    frag1.setArguments(bundle);
                    ft.replace(R.id.replace, frag1);
                    ft.addToBackStack(null);
                    ft.commit();


                }
            });*/

        }


    }
}

