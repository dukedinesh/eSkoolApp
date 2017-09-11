package com.eduschool.eduschoolapp.RaiseRequest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eduschool.eduschoolapp.ClassWork.AdapterClsWrkList;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassworkList;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SendParentRequstPOJO.RequestList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinesh  on 07/09/2017.
 */

public class AdapterTwo extends RecyclerView.Adapter<AdapterTwo.myviewholder> {


    Context context;
    String ss;
    String[] separated;
    List<RequestList> list = new ArrayList<>();

    public AdapterTwo(Context context, List<RequestList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<RequestList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterTwo.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.parent_request_model, parent, false);

        return new AdapterTwo.myviewholder(itemView);

    }


    @Override
    public void onBindViewHolder(final AdapterTwo.myviewholder holder, int position) {

        RequestList item = list.get(position);

        holder.request_type.setText(item.getType());

        if (item.getType().equals("Leave Request")) {
            holder.reason.setText(item.getDetail());
        } else if (item.getType().equals("Exam & Result")) {
            holder.reasonTv.setText("Query : ");
            holder.reason.setText(item.getDetail());
        }
        else if (item.getType().equals("Birthday Card")){

            holder.layout1.setVisibility(View.GONE);
            holder.layout.setVisibility(View.VISIBLE);

        }
        ss = item.getPostDate();
        separated = ss.split("-");

        holder.day.setText(separated[0]);
        holder.monthYear.setText(separated[1] + " " + separated[2]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView request_type, reason, day, monthYear, reasonTv;
        CardView card;

        LinearLayout layout,layout1;

        public myviewholder(View itemView) {
            super(itemView);

            request_type = (TextView) itemView.findViewById(R.id.request_type);
            reason = (TextView) itemView.findViewById(R.id.reason);
            day = (TextView) itemView.findViewById(R.id.day);
            reasonTv = (TextView) itemView.findViewById(R.id.reasonTv);
            monthYear = (TextView) itemView.findViewById(R.id.monthYear);
            card = (CardView) itemView.findViewById(R.id.card);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            layout1 = (LinearLayout) itemView.findViewById(R.id.layout1);

        }


    }
}

