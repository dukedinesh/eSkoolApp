package com.eduschool.eduschoolapp.Survey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.eduschool.eduschoolapp.ClassWork.TeacherClsWrk2;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassworkList;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SurveyListPOJO.SurveyListteacher;

/**
 * Created by user on 6/15/2017.
 */

public class AdapterSurvey extends RecyclerView.Adapter<AdapterSurvey.myviewholder> {

    Context context;
    private List<SurveyListteacher> list;

    public AdapterSurvey(Context context, List<SurveyListteacher> albumList) {
        this.context = context;
        this.list = albumList;

    }

     public void setGridData(List<SurveyListteacher> list)
      {
          this.list = list;
          notifyDataSetChanged();
      }

    @Override
    public AdapterSurvey.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.survey_frgmnt_model, parent, false);

        return new myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterSurvey.myviewholder holder, int position) {
        SurveyListteacher item = list.get(position);
        holder.qus.setText(item.getQuestion());
        holder.date.setText(item.getPostDate());
        holder.status.setText(item.getQuestionStatus());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView date,status,qus;

        public myviewholder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            status = (TextView) itemView.findViewById(R.id.status);
            qus = (TextView) itemView.findViewById(R.id.qus);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String Id = list.get(getAdapterPosition()).getSurveyId();
                    String qusId = list.get(getAdapterPosition()).getQuestionId();
                    String status= list.get(getAdapterPosition()).getQuestionStatus();

                    Intent intent = new Intent(context, Take_Survey.class);
                    intent.putExtra("QusId", qusId);
                    intent.putExtra("SurveyId", Id);
                    intent.putExtra("Status", status);
                    context.startActivity(intent);




                }
            });
        }

        }


    }

