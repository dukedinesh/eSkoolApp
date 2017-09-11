package com.eduschool.eduschoolapp.OnlineTest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eduschool.eduschoolapp.Library.ParentLibrary2;
import com.eduschool.eduschoolapp.OnlineTestListPOJO.OnlinetestList;
import com.eduschool.eduschoolapp.R;

import java.util.List;

/**
 * Created by Dinesh  on 30/08/2017.
 */

public class AdapterOnlineTestList extends RecyclerView.Adapter<AdapterOnlineTestList.myviewholder> {

    Context context;
    private List<OnlinetestList> albumList;

    public AdapterOnlineTestList(Context context, List<OnlinetestList> albumList) {
        this.context = context;
        this.albumList = albumList;

    }

    public void setGridData(List<OnlinetestList> albumList) {
        this.albumList = albumList;
        notifyDataSetChanged();
    }

    @Override
    public AdapterOnlineTestList.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.online_test_model, parent, false);

        return new AdapterOnlineTestList.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterOnlineTestList.myviewholder holder, int position) {
        OnlinetestList item = albumList.get(position);


    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView name, status, author, book_no;
        LinearLayout layout;


        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            status = (TextView) itemView.findViewById(R.id.status);
            book_no = (TextView) itemView.findViewById(R.id.book_no);
            author = (TextView) itemView.findViewById(R.id.author);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Intent intent = new Intent(context,TeacherLibrary2.class);

                    //context.startActivity(intent);
                    /*Intent intent = new Intent(context, ParentLibrary2.class);
                    intent.putExtra("Id", albumList.get(getAdapterPosition()).getBookNoId());
                    intent.putExtra("Status", albumList.get(getAdapterPosition()).getBookAvailableId());
                    context.startActivity(intent);*/
                }
            });
        }

    }


}
