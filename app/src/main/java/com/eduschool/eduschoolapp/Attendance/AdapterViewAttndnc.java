package com.eduschool.eduschoolapp.Attendance;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceDatum;
import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceList;
import com.eduschool.eduschoolapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/21/2017.
 */

public class AdapterViewAttndnc extends RecyclerView.Adapter<AdapterViewAttndnc.myviewholder> {


    Context context;
    List<AttendanceDatum> list = new ArrayList<>();

    public AdapterViewAttndnc(Context context, List<AttendanceDatum> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<AttendanceDatum> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterViewAttndnc.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.view_attndnc_model, parent, false);

        return new AdapterViewAttndnc.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterViewAttndnc.myviewholder holder, int position) {

        AttendanceDatum item = list.get(position);


        //loader.displayImage(item.get);
        holder.name.setText(item.getStudentName());
        holder.no.setText(String.valueOf(position + 1));

        String i=item.getAttendance();
        if (i.equals("1")){
            holder.p.setText("P");
            holder.p.setBackground(context.getResources().getDrawable(R.drawable.checkbox));
        }else if (i.equals("0")){
            holder.p.setText("A");
            holder.p.setBackground(context.getResources().getDrawable(R.drawable.absentcheckbox));
        }else if (i.equals("2")){
            holder.p.setText("L");
            holder.p.setBackground(context.getResources().getDrawable(R.drawable.leavecheckbox));
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView no, name, p;


        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            no = (TextView) itemView.findViewById(R.id.no);
            p = (TextView) itemView.findViewById(R.id.p);


        }


    }
}
