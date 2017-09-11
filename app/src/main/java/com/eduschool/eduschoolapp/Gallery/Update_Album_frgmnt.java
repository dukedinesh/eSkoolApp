package com.eduschool.eduschoolapp.Gallery;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.New;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.UpdateAlbumPOJO.UpdateAlbumBean;
import com.eduschool.eduschoolapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 8/9/2017.
 */

public class Update_Album_frgmnt extends Fragment {
    Toolbar toolbar;
    EditText name;
    String Ename,albumId;
    TextView update;
    ProgressBar progress;

    public Update_Album_frgmnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.update_album, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        name = (EditText) view.findViewById(R.id.name);
        progress=(ProgressBar)view.findViewById(R.id.progress);
        update = (TextView) view.findViewById(R.id.updte);
        Ename = getArguments().getString("message1");
        albumId = getArguments().getString("message");
        name.setText(Ename);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(false);

                final ProgressDialog dialog1 = ProgressDialog.show(getActivity(), "",
                        "Updating Album. Please wait...", true);

                dialog.setMessage("Are you sure you want to Update this album ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int id) {

                        User b = (User) getActivity().getApplicationContext();


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);
                        //progress.setVisibility(View.VISIBLE);

                        String s=name.getText().toString().trim();
                        Call<UpdateAlbumBean> call = cr.update_album(b.school_id,albumId,s);

                        call.enqueue(new Callback<UpdateAlbumBean>() {
                            @Override
                            public void onResponse(Call<UpdateAlbumBean> call, Response<UpdateAlbumBean> response) {


                                if (response.body().getStatus().equals("1")){
                                    Toast.makeText(getActivity(),"Album Updated Successfully",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(),"Album not updated Successfully !",Toast.LENGTH_SHORT).show();
                                }
                                //progress.setVisibility(View.GONE);
                                dialog1.dismiss();
                                dialog.dismiss();
                                getFragmentManager().popBackStack();
                                getFragmentManager().popBackStack();

                            }

                            @Override
                            public void onFailure(Call<UpdateAlbumBean> call, Throwable throwable) {

                               // progress.setVisibility(View.GONE);

                            }
                        });

                    }
                })
                        .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                                dialog1.dismiss();
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Update Gallery");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}
