package com.eduschool.eduschoolapp.StudentSummary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.Library.Album;
import com.eduschool.eduschoolapp.R;

import java.util.List;

/**
 * Created by user on 7/13/2017.
 */

public class AdapterCalendar extends RecyclerView.Adapter<AdapterCalendar.myviewholder> {

    Context context;
    private List<Album> albumList;

    public AdapterCalendar(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;

    }

    /*  public void setGridData(List<CartDatum> list)
      {
          this.list = list;
          notifyDataSetChanged();
      }
  */
    @Override
    public AdapterCalendar.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.parent_attendance_model, parent, false);

        return new AdapterCalendar.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterCalendar.myviewholder holder, int position) {
    }


    @Override
    public int getItemCount() {
        return 8;
    }

    public class myviewholder extends RecyclerView.ViewHolder {



        public myviewholder(View itemView) {
            super(itemView);




        }

    }


}


