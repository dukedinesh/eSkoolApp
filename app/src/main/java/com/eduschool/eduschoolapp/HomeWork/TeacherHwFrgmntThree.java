package com.eduschool.eduschoolapp.HomeWork;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterList;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.HomewrkPOJO.HomewrkBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentList;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.UpdateHwPOJO.UpdateHwBean;
import com.eduschool.eduschoolapp.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.app.Activity.RESULT_OK;
import static com.eduschool.eduschoolapp.R.id.view;

/**
 * Created by user on 5/25/2017.
 */

public class TeacherHwFrgmntThree extends Fragment {
    Toolbar toolbar;
    TextView subjectName, classSection, createDate, DueDate, file;
    EditText note;
    Spinner chapter;
    Button update;
    Bitmap bmp;
    String mCurrentPhotoPath;
    ProgressBar progress;
    String classId, subjectId;
    List<String> chapterlist, studentlist;
    List<String> chapterId, studentId;
    String cId, ssId, sId, hId, sChapter, sNote;
    public List<StudentList> listStudent;
    public List<ChapterList> listChapter;
    private final int PICK_IMAGE_REQUEST = 2;


    public TeacherHwFrgmntThree() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_hw_frgmnt_three, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        final String strtext = getArguments().getString("message");
        progress = (ProgressBar) view.findViewById(R.id.progress);
        //Toast.makeText(getActivity(),String.valueOf(strtext), Toast.LENGTH_SHORT).show();

        subjectName = (TextView) view.findViewById(R.id.subject);
        note = (EditText) view.findViewById(R.id.note);
        classSection = (TextView) view.findViewById(R.id.className);
        createDate = (TextView) view.findViewById(R.id.date);
        file = (TextView) view.findViewById(R.id.file);
        chapter = (Spinner) view.findViewById(R.id.chapter);
        DueDate = (TextView) view.findViewById(R.id.dueDate);
        chapterlist = new ArrayList<>();
        listChapter = new ArrayList<>();
        chapterId = new ArrayList<>();
        studentlist = new ArrayList<>();
        studentId = new ArrayList<>();
        listStudent = new ArrayList<>();
        update = (Button) view.findViewById(R.id.update);


        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);

            }
        });


        final User b = (User) getActivity().getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);
        Call<HomewrkBean> call2 = cr.homewrk(b.school_id, strtext);
        progress.setVisibility(View.VISIBLE);


        call2.enqueue(new Callback<HomewrkBean>() {

            @Override
            public void onResponse(Call<HomewrkBean> call, Response<HomewrkBean> response) {

                subjectName.setText(response.body().getHomeworkData().get(0).getSubject());
                classSection.setText(response.body().getHomeworkData().get(0).getClass_() + " " + response.body().getHomeworkData().get(0).getSection());
                createDate.setText(response.body().getHomeworkData().get(0).getCreateDate());
                DueDate.setText(response.body().getHomeworkData().get(0).getDueDate());
                //chapter.setText(response.body().getHomeworkData().get(0).getChapter());
                note.setText(response.body().getHomeworkData().get(0).getNotes());

                classId = response.body().getHomeworkData().get(0).getClassId();
                subjectId = response.body().getHomeworkData().get(0).getSubjectId();


                chapterlist.add(response.body().getHomeworkData().get(0).getChapter());

                chapterId.add(response.body().getHomeworkData().get(0).getChapterId());


                ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, chapterlist);

                adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adp2.notifyDataSetChanged();
                chapter.setAdapter(adp2);

                cId = response.body().getHomeworkData().get(0).getClassId();
                ssId = response.body().getHomeworkData().get(0).getSubjectId();
                sId = response.body().getHomeworkData().get(0).getSectionId();
                hId = response.body().getHomeworkData().get(0).getHomeworkId();

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<HomewrkBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);

            }

        });




        chapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                Call<ChapterListbean> call = cr.chapterList(b.school_id, cId, ssId);

                progress.setVisibility(View.VISIBLE);


                call.enqueue(new Callback<ChapterListbean>() {

                    @Override
                    public void onResponse(Call<ChapterListbean> call, Response<ChapterListbean> response) {

                        sChapter = chapterId.get(i);

                        listChapter = response.body().getChapterList();
                        chapterlist.clear();
                        chapterId.clear();
                        for (int i = 0; i < response.body().getChapterList().size(); i++) {

                            chapterlist.add(response.body().getChapterList().get(i).getChapterName());

                            chapterId.add(response.body().getChapterList().get(i).getChapterId());

                        }

                        progress.setVisibility(View.GONE);

                        Call<StudentListbean> call3 = cr.student_list(b.school_id, cId, sId);

                        progress.setVisibility(View.VISIBLE);

                        call3.enqueue(new Callback<StudentListbean>() {

                            @Override
                            public void onResponse(Call<StudentListbean> call3, Response<StudentListbean> response) {


                                listStudent = response.body().getStudentList();
                                studentlist.clear();
                                studentId.clear();
                                for (int i = 0; i < response.body().getStudentList().size(); i++) {

                                    studentlist.add(response.body().getStudentList().get(i).getStudentName());

                                    studentId.add(response.body().getStudentList().get(i).getStudentId());
                                }

                                Log.d("studenttt",String.valueOf(studentId.size()));
                                progress.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<StudentListbean> call, Throwable throwable) {
                                progress.setVisibility(View.GONE);

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<ChapterListbean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        DueDate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                android.app.DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getFragmentManager(), "df");


            }
        });


     /*   chapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                Call<ChapterListbean> call = cr.chapterList(b.school_id, cId, ssId);

                progress.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<ChapterListbean>() {

                    @Override
                    public void onResponse(Call<ChapterListbean> call, Response<ChapterListbean> response) {

                        sChapter = chapterId.get(i);
                        progress.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<ChapterListbean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(false);
                dialog.setMessage("Are you sure you want to Update Home work ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        sNote = note.getText().toString().trim();


                        Call<UpdateHwBean> call = cr.update_hw(hId, b.school_id, b.user_id, cId, sId, ssId, sChapter, sNote, DueDate.getText().toString(), studentId, "Image");


                        progress.setVisibility(View.VISIBLE);


                        call.enqueue(new Callback<UpdateHwBean>() {

                            @Override
                            public void onResponse(Call<UpdateHwBean> call, Response<UpdateHwBean> response) {

                                if (response.body().getStatus().equals("1")) {
                                    Toast.makeText(getContext(), "Home Work Updated Successfully", Toast.LENGTH_LONG).show();

                                    getFragmentManager().popBackStack();
                                } else {
                                    Toast.makeText(getContext(), "Home Work did Not updated successfully!", Toast.LENGTH_LONG).show();
                                }

                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<UpdateHwBean> call, Throwable throwable) {
                                progress.setVisibility(View.GONE);

                            }
                        });

                        dialog.dismiss();
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

        return view;
    }

    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends android.app.DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int day) {

            CharSequence strDate = null;
            Time chosenDate = new Time();
            chosenDate.set(day, monthOfYear, year);
            long dtDob = chosenDate.toMillis(true);
            strDate = DateFormat.format("dd-MMMM-yyyy", dtDob);
            DueDate.setText(strDate);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //use the current date as the default date in the picker
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = null;
            datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

            return datePickerDialog;
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home Work");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


      /*  if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            try {
                bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(String.valueOf(data.getData())));


                Uri selectedImageUri = data.getData();


                mCurrentPhotoPath = getPath(getActivity(), selectedImageUri);
                Log.d("fileeee", mCurrentPhotoPath);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            try {
                bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(String.valueOf(data.getData())));

                //browse_image.setImageBitmap(bitmap);
                Uri selectedImageUri = data.getData();

                mCurrentPhotoPath = getPath(getActivity().getApplicationContext(), selectedImageUri);
                file.setText("Image01");

                Log.d("asdasdasd", String.valueOf(selectedImageUri));


                Log.d("asdasdasd", mCurrentPhotoPath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    private static String getPath(final Context context, final Uri uri) {
        final boolean isKitKatOrAbove = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isKitKatOrAbove && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[]{
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


}



