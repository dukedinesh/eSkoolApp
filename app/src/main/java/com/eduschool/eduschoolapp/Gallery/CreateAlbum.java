package com.eduschool.eduschoolapp.Gallery;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AddAlbumPOJO.AddAlbumBean;
import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumListBean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 8/5/2017.
 */

public class CreateAlbum extends Fragment {

    Toolbar toolbar;
    EditText name;
    String sName;
    TextView create;
    ProgressBar progress;


    public CreateAlbum() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.create_album_frgmnt, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        name = (EditText) view.findViewById(R.id.name);
        create = (TextView) view.findViewById(R.id.create);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(false);


                dialog.setMessage("Are you sure you want to Create this album ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int id) {

                        sName = name.getText().toString().trim();
                        User b = (User) getActivity().getApplicationContext();


                        if (sName.length() > 0) {


                             final ProgressDialog dialog1 = ProgressDialog.show(getActivity(), "",
                                    "Creating Album. Please wait...", true);
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);


                            Call<AddAlbumBean> call = cr.add_album(sName, b.school_id, b.user_id);

                            call.enqueue(new Callback<AddAlbumBean>() {
                                @Override
                                public void onResponse(Call<AddAlbumBean> call, Response<AddAlbumBean> response) {

                                    if (response.body().getStatus().equals("1")) {

                                        Toast.makeText(getActivity(), "Album Created Successfully", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        dialog1.dismiss();
                                        getFragmentManager().popBackStack();
                                    } else {
                                        Toast.makeText(getActivity(), "Album did Not Created !", Toast.LENGTH_SHORT).show();
                                        dialog1.dismiss();
                                    }

                                    dialog1.dismiss();


                                }

                                @Override
                                public void onFailure(Call<AddAlbumBean> call, Throwable throwable) {

                                    dialog1.dismiss();

                                }
                            });


                        } else {
                            name.setError("Invalid Album Name");
                        }
                    }
                })
                        .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                                //dialog1.dismiss();
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
        toolbar.setTitle("Gallery");

        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}