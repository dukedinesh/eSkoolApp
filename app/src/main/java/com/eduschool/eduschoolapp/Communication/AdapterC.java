package com.eduschool.eduschoolapp.Communication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.eduschool.eduschoolapp.BirthStuListPOJO.BirthStuList;
import com.eduschool.eduschoolapp.BirthTechrListPOJO.BirthTechrList;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.WishingCards.Adapter;
import com.eduschool.eduschoolapp.WishingCards.AdapterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinesh  on 05/09/2017.
 */

public class AdapterC extends RecyclerView.Adapter<AdapterC.myviewholder> {


    Context context;
    List<BirthTechrList> list = new ArrayList<>();

    List<AdapterBean> p = new ArrayList<>();

    public AdapterC(Context context, List<BirthTechrList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<BirthTechrList> list, List<AdapterBean> p) {
        this.list = list;
        this.p = p;
        notifyDataSetChanged();
    }

    public List<AdapterBean> getCheckList() {


        return p;
    }

    @Override
    public AdapterC.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.birthday_student_model, parent, false);

        return new AdapterC.myviewholder(itemView);    }


    @Override
    public void onBindViewHolder(final AdapterC.myviewholder holder, final int position) {

        final AdapterBean bean = new AdapterBean();
        final BirthTechrList item = list.get(position);

        bean.setId(item.getTeachrId());
        bean.setStatus("0");
        p.add(position,bean);

       holder.ctv.setText("Rohit");

        holder.ctv.setText(item.getTeachrName());
        holder.ctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.ctv.isChecked()) {

                    holder.ctv.setChecked(false);

                    p.clear();
                    bean.setId(item.getTeachrId());
                    bean.setStatus("0");
                    p.add(position,bean);

                } else

                {
                    holder.ctv.setChecked(true);

                    p.clear();
                    bean.setId(item.getTeachrId());
                    bean.setStatus("1");
                    p.add(position,bean);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        CheckedTextView ctv;


        public myviewholder(View itemView) {
            super(itemView);

            ctv = (CheckedTextView) itemView.findViewById(R.id.checkedTextView1);


        }


    }
}
