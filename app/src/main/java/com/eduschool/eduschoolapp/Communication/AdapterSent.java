package com.eduschool.eduschoolapp.Communication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.RequestListPOJO.RequestList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinesh  on 09-09-2017.
 */

public class AdapterSent extends RecyclerView.Adapter<AdapterSent.myviewholder> {


    Context context;
    List<com.eduschool.eduschoolapp.SendCommunicationTechrPOJO.RequestList> list = new ArrayList<>();


    public AdapterSent(Context context, List<com.eduschool.eduschoolapp.SendCommunicationTechrPOJO.RequestList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<com.eduschool.eduschoolapp.SendCommunicationTechrPOJO.RequestList> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public AdapterSent.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.teacher_request_model, parent, false);

        return new AdapterSent.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterSent.myviewholder holder, final int position) {

        final com.eduschool.eduschoolapp.SendCommunicationTechrPOJO.RequestList item = list.get(position);


        holder.type.setText(item.getType());
        String[] sep;
        String s = item.getPostDate();
        sep = s.split("-");
        holder.day.setText(sep[0]);
        holder.monthYear.setText(sep[1] + " " + sep[2]);

        holder.date.setText(item.getFromDate() + " - " + item.getToDate());

        holder.note.setText(item.getDetail());


        String ss = item.getFromDate();
        String ss1 = item.getToDate();
        String[] seprated;
        String[] separated;
        seprated = ss1.split("-");
        separated = ss.split("-");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView type, postDate, date, note, day, monthYear;


        public myviewholder(View itemView) {
            super(itemView);


            type = (TextView) itemView.findViewById(R.id.type);
            postDate = (TextView) itemView.findViewById(R.id.postDate);
            day = (TextView) itemView.findViewById(R.id.day);
            monthYear = (TextView) itemView.findViewById(R.id.monthYear);
            note = (TextView) itemView.findViewById(R.id.note);
            date = (TextView) itemView.findViewById(R.id.date);


        }


    }
}

