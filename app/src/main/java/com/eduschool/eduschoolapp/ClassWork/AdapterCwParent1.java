package com.eduschool.eduschoolapp.ClassWork;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClasssworkList;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClasssworkListBean;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClassworkList;
import com.eduschool.eduschoolapp.HomeWork.AdapterParent1;
import com.eduschool.eduschoolapp.HomeWork.HomeWrkFrgmntThree;
import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeworkList_;
import com.eduschool.eduschoolapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/16/2017.
 */

public class AdapterCwParent1 extends RecyclerView.Adapter<AdapterCwParent1.myviewholder> {

    Context context;
    List<ClassworkList> list = new ArrayList<>();


    public AdapterCwParent1(Context context, List<ClassworkList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<ClassworkList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterCwParent1.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.parent_cw_model1, parent, false);

        return new AdapterCwParent1.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterCwParent1.myviewholder holder, int position) {

        ClassworkList item = list.get(position);

        holder.subject.setText(item.getSubjectName());
        holder.chapter.setText("Chapter : "+item.getChapterName());
        holder.status.setText(item.getClassworkStatus());

        String s=item.getPostedDate();
        String[] separated = s.split("-");
        holder.date.setText(separated[0]);
        holder.month.setText(separated[1]);
        holder.year.setText(" "+separated[2]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView subject, due_date, chapter,status,date,month,year;


        public myviewholder(View itemView) {
            super(itemView);

            subject = (TextView) itemView.findViewById(R.id.subject);
            due_date = (TextView) itemView.findViewById(R.id.due_date);
            chapter = (TextView) itemView.findViewById(R.id.chapter);
            status = (TextView) itemView.findViewById(R.id.status);
            date = (TextView) itemView.findViewById(R.id.date);
            month = (TextView) itemView.findViewById(R.id.month);
            year = (TextView) itemView.findViewById(R.id.year);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String pos = list.get(getAdapterPosition()).getClassworkId();

                    android.support.v4.app.FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                    ClassWrkFrgmntThree frag1 = new ClassWrkFrgmntThree();
                    Bundle bundle = new Bundle();
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