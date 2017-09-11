package com.eduschool.eduschoolapp.Library;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.BookDetailsPOJO.BookListBean1;
import com.eduschool.eduschoolapp.BorrowedBookPOJO.BookListBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.ReserveBookPOJO.ReserveBookBean;
import com.eduschool.eduschoolapp.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by user on 6/24/2017.
 */

public class ParentLibrary2 extends AppCompatActivity {
    Toolbar toolbar;
    Button reserve;
    String Bid,Sstatus,book_id;
    ProgressBar progress;
    TextView name,author,book_no,status,publishDate,borrowName,borrowDate,textChange,dueDate,texT,textView3,borrowedFrom;
    LinearLayout bLayout,bLayout1,layout,bLayout2,layout1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_library2);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Library");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progress=(ProgressBar)findViewById(R.id.progress);
        name=(TextView)findViewById(R.id.name);
        author=(TextView)findViewById(R.id.author);
        book_no=(TextView)findViewById(R.id.book_no);
        publishDate=(TextView)findViewById(R.id.publishDate);
        bLayout=(LinearLayout)findViewById(R.id.bLayout);
        bLayout1=(LinearLayout)findViewById(R.id.bLayout1);
        bLayout2=(LinearLayout)findViewById(R.id.blayout2);
        layout1=(LinearLayout)findViewById(R.id.layout1);

        layout=(LinearLayout)findViewById(R.id.layout);
        status=(TextView)findViewById(R.id.status);
        borrowDate=(TextView)findViewById(R.id.borrowedDate);
        borrowName=(TextView)findViewById(R.id.borrowerName);
        textChange=(TextView)findViewById(R.id.textchange);
        dueDate=(TextView)findViewById(R.id.dueDate);
        texT=(TextView)findViewById(R.id.texT);
        borrowedFrom=(TextView)findViewById(R.id.borrowedFrom);
        textView3=(TextView)findViewById(R.id.textView3);
        reserve=(Button)findViewById(R.id.reserve);

        Bid=getIntent().getStringExtra("Id");
        Sstatus=getIntent().getStringExtra("Status");

        Log.d("dcddd",Sstatus);

        final User b = (User) getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);



        if (Sstatus.equals("0")){
            bLayout1.setVisibility(View.GONE);
            bLayout.setVisibility(View.GONE);
            bLayout2.setVisibility(View.GONE);



            progress.setVisibility(View.VISIBLE);

            Call<BookListBean1> call = cr.book_details1(b.school_id,Bid,Sstatus);


            call.enqueue(new Callback<BookListBean1>() {
                @Override
                public void onResponse(Call<BookListBean1> call, Response<BookListBean1> response) {
                    Log.d("dscs","ssssssss");

                    name.setText(response.body().getBookList().get(0).getTitle());
                    author.setText(response.body().getBookList().get(0).getAuthor());
                    book_no.setText(response.body().getBookList().get(0).getBookNo());
                    book_id=response.body().getBookList().get(0).getBookId();

                    status.setText(response.body().getBookList().get(0).getBookAvailable());
                    status.setBackgroundResource(R.drawable.lib2);
                    textChange.setText("Reserve Date");
                    dueDate.setText(response.body().getBookList().get(0).getReserveDate());


                    publishDate.setText(response.body().getBookList().get(0).getPublishDate());

                    for (int i = 0; i < response.body().getBookList().get(0).getBookCategory().size(); i++) {

                        TextView text = new TextView(ParentLibrary2.this);

                        text.setBackgroundResource(R.drawable.lib);
                        text.setText(response.body().getBookList().get(0).getBookCategory().get(i).getChapcatName());
                        text.setTextSize(ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textView));
                        text.setPadding(ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding), ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding), ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding), ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding));


                        layout.addView(text);

                    }


                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<BookListBean1> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });


        }else if(Sstatus.equals("1")){

            progress.setVisibility(View.VISIBLE);
            reserve.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);



            Call<BookListBean> call = cr.book_details(b.school_id,Bid,Sstatus);


            call.enqueue(new Callback<BookListBean>() {
                @Override
                public void onResponse(Call<BookListBean> call, Response<BookListBean> response) {


                    name.setText(response.body().getBookList().get(0).getTitle());
                    author.setText(response.body().getBookList().get(0).getAuthor());
                    book_no.setText(response.body().getBookList().get(0).getBookNo());

                    status.setText(response.body().getBookList().get(0).getBookAvailable());
                    status.setBackgroundResource(R.drawable.lib4);
                    publishDate.setText(response.body().getBookList().get(0).getPublishDate());
                    texT.setText("Borrowed by");

                    borrowName.setText(response.body().getBookList().get(0).getBorrowedBy());
                    borrowedFrom.setText(response.body().getBookList().get(0).getBorrowFromdate());
                    borrowDate.setText(response.body().getBookList().get(0).getBorrowTodate());
                    book_id=response.body().getBookList().get(0).getBookId();

                    for (int i = 0; i < response.body().getBookList().get(0).getBookCategory().size(); i++) {

                        TextView text = new TextView(ParentLibrary2.this);

                        text.setBackgroundResource(R.drawable.lib);
                        text.setText(response.body().getBookList().get(0).getBookCategory().get(i).getChapcatName());
                        text.setTextSize(ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textView));
                        text.setPadding(ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding), ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding), ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding), ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding));


                        layout.addView(text);

                    }
                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<BookListBean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });
        }else if (Sstatus.equals("2")){

            progress.setVisibility(View.VISIBLE);
            bLayout2.setVisibility(View.GONE);
            reserve.setVisibility(View.GONE);

            Call<com.eduschool.eduschoolapp.ReservBookPOJO.BookListBean> call = cr.book_reserv(b.school_id,Bid,Sstatus);


            call.enqueue(new Callback<com.eduschool.eduschoolapp.ReservBookPOJO.BookListBean>() {
                @Override
                public void onResponse(Call<com.eduschool.eduschoolapp.ReservBookPOJO.BookListBean> call, Response<com.eduschool.eduschoolapp.ReservBookPOJO.BookListBean> response) {



                    name.setText(response.body().getBookList().get(0).getTitle());
                    author.setText(response.body().getBookList().get(0).getAuthor());
                    book_no.setText(response.body().getBookList().get(0).getBookNo());
                    book_id=response.body().getBookList().get(0).getBookId();
                    textChange.setText("Reserve to");
                    dueDate.setText(response.body().getBookList().get(0).getReserveTodate());
                    texT.setText("Reserve from");
                    borrowName.setText(response.body().getBookList().get(0).getReserveFromdate());
                    textView3.setText("Reserve by");
                    borrowDate.setText(response.body().getBookList().get(0).getReserveBy());




                    status.setText(response.body().getBookList().get(0).getBookAvailable());
                    status.setBackgroundResource(R.drawable.lib3);
                    publishDate.setText(response.body().getBookList().get(0).getPublishDate());

                    for (int i = 0; i < response.body().getBookList().get(0).getBookCategory().size(); i++) {

                        TextView text = new TextView(ParentLibrary2.this);
                        text.setBackgroundResource(R.drawable.lib);
                        text.setText(response.body().getBookList().get(0).getBookCategory().get(i).getChapcatName());
                        text.setTextSize(ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textView));
                        text.setPadding(ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding), ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding), ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding), ParentLibrary2.this.getResources().getDimensionPixelSize(R.dimen.textPadding));

                        layout.addView(text);

                    }


                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<com.eduschool.eduschoolapp.ReservBookPOJO.BookListBean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });


        }






        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ParentLibrary2.this);
                dialog.setCancelable(false);
                dialog.setMessage("Are you sure you want to reserve the book ?" );
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".

                        bLayout1.setVisibility(View.GONE);
                        bLayout.setVisibility(View.GONE);
                        progress.setVisibility(View.VISIBLE);

                        Call<ReserveBookBean> call = cr.reserve_book(b.school_id,b.user_id,b.user_type,book_id,Bid);

                        Log.d("dcdsd",Bid);

                        call.enqueue(new Callback<ReserveBookBean>() {
                            @Override
                            public void onResponse(Call<ReserveBookBean> call, Response<ReserveBookBean> response) {


                                if (response.body().getStatus().equals("1")){

                                    Toast.makeText(ParentLibrary2.this,"Your Book is reserved successfully",Toast.LENGTH_SHORT).show();

                                }else {

                                    Toast.makeText(ParentLibrary2.this,"Book does not reserved!",Toast.LENGTH_SHORT).show();
                                }

                                progress.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<ReserveBookBean> call, Throwable throwable) {

                                progress.setVisibility(View.GONE);

                            }
                        });
                        dialog.dismiss();
                        finish();
                    }
                })
                        .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });

    }

}

