package com.eduschool.eduschoolapp.HomeWork;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeworkList;
import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeworkList_;
import com.eduschool.eduschoolapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/14/2017.
 */

public class AdapterParent1 extends RecyclerView.Adapter<AdapterParent1.myviewholder> {

    Context context;
    List<HomeworkList_> list = new ArrayList<>();


    public AdapterParent1(Context context, List<HomeworkList_> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<HomeworkList_> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterParent1.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.parent_hw_model1, parent, false);

        return new AdapterParent1.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterParent1.myviewholder holder, int position) {

        HomeworkList_ item = list.get(position);

        holder.subject.setText(item.getSubjectName());
        holder.chapter.setText("Chapter : "+item.getChapterName());
        holder.due_date.setText(item.getDueDate());

        String s=item.getPostedDate();
        String[] separated = s.split("-");
        holder.day.setText(separated[0]);
        holder.month.setText(separated[1]);
        holder.year.setText(" "+separated[2]);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView subject, due_date, chapter,day,month,year;


        public myviewholder(View itemView) {
            super(itemView);

            subject = (TextView) itemView.findViewById(R.id.subject);
            due_date = (TextView) itemView.findViewById(R.id.due_date);

            day = (TextView) itemView.findViewById(R.id.day);
            month = (TextView) itemView.findViewById(R.id.month);
            year = (TextView) itemView.findViewById(R.id.year);
            chapter = (TextView) itemView.findViewById(R.id.chapter);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String pos = list.get(getAdapterPosition()).getHomeworkId();

                    android.support.v4.app.FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                    HomeWrkFrgmntThree frag1 = new HomeWrkFrgmntThree();
                    Bundle bundle = new Bundle();
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

        }
    }
}