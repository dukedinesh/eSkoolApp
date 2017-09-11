package com.eduschool.eduschoolapp.ClassWork;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassworkList;
import com.eduschool.eduschoolapp.HomeWork.AdapterHwList;
import com.eduschool.eduschoolapp.HomeWork.TeacherHwFrgmntTwo;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomeworkList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 7/31/2017.
 */

public class AdapterClsWrkList extends RecyclerView.Adapter<AdapterClsWrkList.myviewholder> {



    Context context;
    List<ClassworkList> list = new ArrayList<>();

    public AdapterClsWrkList(Context context, List<ClassworkList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<ClassworkList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterClsWrkList.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.hw_list_model, parent, false);

        return new AdapterClsWrkList.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterClsWrkList.myviewholder holder, int position) {

        ClassworkList item = list.get(position);


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

    public class myviewholder extends RecyclerView.ViewHolder {


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

                    String pos = list.get(getAdapterPosition()).getClassworkId();

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
            });

        }


    }
}
