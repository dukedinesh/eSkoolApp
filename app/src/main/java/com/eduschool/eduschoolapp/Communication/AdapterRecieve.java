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
 * Created by Dinesh  on 08-09-2017.
 */

public class AdapterRecieve extends RecyclerView.Adapter<AdapterRecieve.myviewholder> {


    Context context;
    List<RequestList> list = new ArrayList<>();


    public AdapterRecieve(Context context, List<RequestList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<RequestList> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public AdapterRecieve.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.teacher_request_model1, parent, false);

        return new AdapterRecieve.myviewholder(itemView);    }


    @Override
    public void onBindViewHolder(final AdapterRecieve.myviewholder holder, final int position) {

        final RequestList item = list.get(position);


        holder.type.setText(item.getType());
        holder.reason.setText(item.getDetail());

        String[] sep;
        String s=item.getPostDate();
        sep=s.split("-");
        holder.day.setText(sep[0]);
        holder.monthYear.setText(sep[1]+" "+sep[2]);


        if (item.getFromType().equals("Parent")){
            holder.name.setText(item.getFromName()+" ( "+item.getClass_()+" "+item.getSection()+" )");
        }else {
            holder.name.setText(item.getFromName());
        }

        if (item.getType().equals("Leave Request")) {
            String ss = item.getFromDate();
            String ss1=item.getToDate();
            String []seprated;
            String[] separated;
            seprated=ss1.split("-");
            separated = ss.split("-");
            holder.fromToDate.setText(separated[0]+""+separated[1]+separated[2]+" - "+seprated[0]+seprated[1]+seprated[2]);

        }else if (item.getType().equals("Exam & Result")){
            holder.reason.setVisibility(View.GONE);
            holder.reason1.setVisibility(View.VISIBLE);
            holder.reason1.setText(item.getDetail());
            holder.fromToDate.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        CheckedTextView ctv;
        TextView name,type,fromToDate,reason,reason1,day,monthYear;


        public myviewholder(View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.name);
            type = (TextView) itemView.findViewById(R.id.type);
            fromToDate = (TextView) itemView.findViewById(R.id.fromToDate);
            reason = (TextView) itemView.findViewById(R.id.reason);
            reason1 = (TextView) itemView.findViewById(R.id.reason1);
            day = (TextView) itemView.findViewById(R.id.day);
            monthYear = (TextView) itemView.findViewById(R.id.monthYear);


        }


    }
}

