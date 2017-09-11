package com.eduschool.eduschoolapp.Survey;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SurveyListParentPOJO.ServeyDatum;
import com.eduschool.eduschoolapp.SurveyListParentPOJO.SurveyListteacher;

import java.util.List;

/**
 * Created by user on 8/18/2017.
 */

public class AdapterSurveyParent extends RecyclerView.Adapter<AdapterSurveyParent.myviewholder> {

    Context context;
    private List<ServeyDatum> list;

    public AdapterSurveyParent(Context context, List<ServeyDatum> albumList) {
        this.context = context;
        this.list = albumList;

    }

    public void setGridData(List<ServeyDatum> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterSurveyParent.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.survey_frgmnt_model, parent, false);

        return new AdapterSurveyParent.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterSurveyParent.myviewholder holder, int position) {

        ServeyDatum item = list.get(position);

        holder.qus.setText(item.getQuestion());
        holder.date.setText(item.getPostDate());
        holder.status.setText(item.getQuestionStatus());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView date, status, qus;

        public myviewholder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            status = (TextView) itemView.findViewById(R.id.status);
            qus = (TextView) itemView.findViewById(R.id.qus);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String Id = list.get(getAdapterPosition()).getSurveyid();
                    String qusId = list.get(getAdapterPosition()).getQuestionId();
                    String status= list.get(getAdapterPosition()).getQuestionStatus();

                    Intent intent = new Intent(context, TakeSurveyParent.class);
                    intent.putExtra("QusId", qusId);
                    intent.putExtra("SurveyId", Id);
                    intent.putExtra("Status", status);
                    context.startActivity(intent);


                }
            });
        }

    }


}


