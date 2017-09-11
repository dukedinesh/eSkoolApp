package com.eduschool.eduschoolapp.ExamAndResults;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.ExmNresultPOJO.ExamTypeList;
import com.eduschool.eduschoolapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinesh  on 02/09/2017.
 */

public class AdapterTeacher extends RecyclerView.Adapter<AdapterTeacher.myviewholder> {



    Context context;
    List<ExamTypeList> list = new ArrayList<>();

    public AdapterTeacher(Context context, List<ExamTypeList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<ExamTypeList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterTeacher.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.exm_model, parent, false);

        return new AdapterTeacher.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterTeacher.myviewholder holder, int position) {

        ExamTypeList item = list.get(position);

        holder.title.setText(item.getName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView title;



        public myviewholder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   String pos = list.get(getAdapterPosition()).getId();

                    android.support.v4.app.FragmentManager fm=((AppCompatActivity)context).getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    TeacherFrgmnt2 frag1 = new TeacherFrgmnt2();
                    Bundle bundle=new Bundle();
                    bundle.putString("message", pos);
                    frag1.setArguments(bundle);
                    ft.replace(R.id.replace, frag1);
                    ft.addToBackStack(null);
                    ft.commit();


                }
            });

        }


    }
}

