package com.eduschool.eduschoolapp.SyllabusManagement;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SyllbsMngTeacherPOJO.ExamType;
import com.eduschool.eduschoolapp.SyllbsMngTeacherPOJO.SyllabusList;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinesh  on 31/08/2017.
 */

public class AdapterTeacher extends RecyclerView.Adapter<AdapterTeacher.myviewholder> {


    Context context;
    List<ExamType> list = new ArrayList<>();

    public AdapterTeacher(Context context, List<ExamType> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<ExamType> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterTeacher.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.syllabus_model, parent, false);

        return new AdapterTeacher.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterTeacher.myviewholder holder, int position) {
        ExamType item = list.get(position);


        //loader.displayImage(item.get);
        holder.exam.setText(item.getTitle());

        holder.layout.removeAllViews();
        for (int i=0;i<item.getChapterList().size();i++){

            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.syllabus_view, null);

            TextView chapter=(TextView)itemView.findViewById(R.id.chapter);
            TextView no=(TextView)itemView.findViewById(R.id.no);
            TextView status=(TextView)itemView.findViewById(R.id.status);

            chapter.setText(item.getChapterList().get(i).getChapterName());
            no.setText(String.valueOf(i + 1));

            if (item.getChapterList().get(i).getChapterStatus().equals("Pending")) {

                status.setBackgroundColor(Color.parseColor("#ffcc0000"));
                status.setText("Pending");
            } else if (item.getChapterList().get(i).getChapterStatus().equals("Completed")) {
                status.setBackgroundColor(Color.parseColor("#ff669900"));
                status.setText("Completed");
            } else if (item.getChapterList().get(i).getChapterStatus().equals("Ongoing")) {
                status.setBackgroundColor(Color.parseColor("#F0920A"));
                status.setText("Ongoing");
            }

            holder.layout.addView(itemView);


        }

        }

        @Override
        public int getItemCount () {
            return list.size();
        }

        public class myviewholder extends RecyclerView.ViewHolder {

            TextView exam;
            LinearLayout layout;


            public myviewholder(View itemView) {
                super(itemView);
                layout = (LinearLayout) itemView.findViewById(R.id.layout);
                exam = (TextView) itemView.findViewById(R.id.exam);


            }


        }
    }



