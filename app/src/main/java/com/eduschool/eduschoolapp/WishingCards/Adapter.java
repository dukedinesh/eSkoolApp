package com.eduschool.eduschoolapp.WishingCards;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CheckableImageButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.BirthStuListPOJO.BirthStuList;
import com.eduschool.eduschoolapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.eduschool.eduschoolapp.R.id.simpleCheckedTextView;

/**
 * Created by user on 8/23/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.myviewholder> {


    Context context;
    List<BirthStuList> list = new ArrayList<>();

    List<AdapterBean> p = new ArrayList<>();

    public Adapter(Context context, List<BirthStuList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<BirthStuList> list, List<AdapterBean> p) {
        this.list = list;
        this.p = p;
        notifyDataSetChanged();
    }

    public List<AdapterBean> getCheckList() {


        return p;
    }

    @Override
    public Adapter.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.birthday_student_model, parent, false);

        return new Adapter.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final Adapter.myviewholder holder, final int position) {

        final AdapterBean bean = new AdapterBean();
        final BirthStuList item = list.get(position);

        bean.setId(item.getStuId());
        bean.setStatus("0");
        p.add(position,bean);

        holder.ctv.setText(item.getStuName());
        holder.ctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.ctv.isChecked()) {

                    holder.ctv.setChecked(false);

                    p.clear();
                    bean.setId(item.getStuId());
                    bean.setStatus("0");
                    p.add(position,bean);

                } else

                {
                    holder.ctv.setChecked(true);

                    p.clear();
                    bean.setId(item.getStuId());
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
