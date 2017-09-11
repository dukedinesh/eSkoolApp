package com.eduschool.eduschoolapp.HomeWork;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.AssignHomeWrkPOJO.AssignHWbean;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterList;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterListbean;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassList;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionList;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentList;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectList;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.Survey.Take_Survey;
import com.eduschool.eduschoolapp.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.eduschool.eduschoolapp.R.id.view;

/**
 * Created by user on 5/20/2017.
 */

public class FrgmntOne extends Fragment {
    Toolbar toolbar;
    Spinner className, sectionName, subjectName, chapter;
    AlertDialog.Builder alertDialog;
    View convertView;
    TextView date, due_date, upload;
    public String a;
    String mCurrentPhotoPath;
    ProgressBar progress;
    String cId, sId, ssId;
    Button submit;
    Bitmap bmp;
    EditText note;
    String sNote, sChapter;


    List<ClassList> list;
    public List<String> classlist, sectionlist, subjectlist, chapterlist, studentlist;
    public List<String> classId, sectionId, subjectId, chapterId, studentId;
    public List<SectionList> listSection;
    public List<SubjectList> listSubject;
    public List<StudentList> listStudent;
    public List<ChapterList> listChapter;

    private final int PICK_IMAGE_REQUEST = 2;

    boolean isFirst;


    public FrgmntOne() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.teacher_hw_frgmnt1, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);


        className = (Spinner) v.findViewById(R.id.className);
        date = (TextView) v.findViewById(R.id.date);
        upload = (TextView) v.findViewById(R.id.upload);
        due_date = (TextView) v.findViewById(R.id.due_date);
        sectionName = (Spinner) v.findViewById(R.id.sectonName);
        subjectName = (Spinner) v.findViewById(R.id.subjectName);
        chapter = (Spinner) v.findViewById(R.id.chapter);
        submit = (Button) v.findViewById(R.id.submit);
        note = (EditText) v.findViewById(R.id.note);


        alertDialog = new AlertDialog.Builder(this.getActivity());
        convertView = (View) inflater.inflate(R.layout.custom, null);
        progress = (ProgressBar) v.findViewById(R.id.progress);

        list = new ArrayList<>();
        classlist = new ArrayList<>();
        classId = new ArrayList<>();

        listSection = new ArrayList<>();
        sectionlist = new ArrayList<>();
        sectionId = new ArrayList<>();

        subjectlist = new ArrayList<>();
        listSubject = new ArrayList<>();
        subjectId = new ArrayList<>();


        chapterlist = new ArrayList<>();
        listChapter = new ArrayList<>();
        chapterId = new ArrayList<>();

        studentlist = new ArrayList<>();
        listStudent = new ArrayList<>();
        studentId = new ArrayList<>();

        ListView lv = (ListView) convertView.findViewById(R.id.listView1);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, classlist);
        lv.setAdapter(adapter1);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, PICK_IMAGE_REQUEST);*/


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

        Call<ClassListbean> call = cr.classList(b.school_id);
        progress.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<ClassListbean>() {
            @Override
            public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {


                list = response.body().getClassList();

                classlist.clear();
                classId.clear();


                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i).getClassName() != null && list.get(i).getClassId() != null) {

                        classlist.add(list.get(i).getClassName());
                        classId.add(list.get(i).getClassId());
                    }

                }

                ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, classlist);
                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                className.setAdapter(adp1);


                ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, sectionlist);

                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sectionName.setAdapter(adp);


                ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, subjectlist);

                adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectName.setAdapter(adp2);


                ArrayAdapter<String> adp3 = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, chapterlist);

                adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                chapter.setAdapter(adp3);

                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        className.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                cId = classId.get(i);
                Call<SectionListbean> call2 = cr.sectionList(b.school_id, classId.get(i));

                //Toast.makeText(getActivity(), a, Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.VISIBLE);


                call2.enqueue(new Callback<SectionListbean>() {

                    @Override
                    public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                        listSection = response.body().getSectionList();

                        sectionId.clear();
                        sectionlist.clear();

                        for (int i = 0; i < response.body().getSectionList().size(); i++) {

                            sectionlist.add(response.body().getSectionList().get(i).getSectionName());

                            sectionId.add(response.body().getSectionList().get(i).getSectionId());
                        }


                        ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, sectionlist);

                        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        adp.notifyDataSetChanged();
                        sectionName.setAdapter(adp);

                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }

                });


//                    Log.d("subjectId", String.valueOf(subjectId.get(0)));
                Call<ChapterListbean> call = cr.chapterList(b.school_id, cId, ssId);


                progress.setVisibility(View.VISIBLE);


                call.enqueue(new Callback<ChapterListbean>() {

                    @Override
                    public void onResponse(Call<ChapterListbean> call, Response<ChapterListbean> response) {


                        listChapter = response.body().getChapterList();
                        chapterlist.clear();
                        chapterId.clear();
                        for (int i = 0; i < response.body().getChapterList().size(); i++) {

                            chapterlist.add(response.body().getChapterList().get(i).getChapterName());

                            chapterId.add(response.body().getChapterList().get(i).getChapterId());

                        }


                        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, chapterlist);

                        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        adp2.notifyDataSetChanged();
                        chapter.setAdapter(adp2);

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
        });


        subjectName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ssId = subjectId.get(i);

                Call<SubjectListBean> call1 = cr.subjectList(b.school_id, cId,sId);

                progress.setVisibility(View.VISIBLE);

                call1.enqueue(new Callback<SubjectListBean>() {

                    @Override
                    public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {


                        for (int i = 0; i < response.body().getSubjectList().size(); i++) {

                        }


                        Call<ChapterListbean> call2 = cr.chapterList(b.school_id, cId, ssId);


                        progress.setVisibility(View.VISIBLE);


                        call2.enqueue(new Callback<ChapterListbean>() {

                            @Override
                            public void onResponse(Call<ChapterListbean> call2, Response<ChapterListbean> response) {


                                listChapter = response.body().getChapterList();
                                chapterlist.clear();
                                chapterId.clear();
                                for (int i = 0; i < response.body().getChapterList().size(); i++) {

                                    chapterlist.add(response.body().getChapterList().get(i).getChapterName());

                                    chapterId.add(response.body().getChapterList().get(i).getChapterId());

                                }
                                ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getContext(),
                                        android.R.layout.simple_list_item_1, chapterlist);

                                adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                adp2.notifyDataSetChanged();
                                chapter.setAdapter(adp2);
                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<ChapterListbean> call2, Throwable throwable) {
                                progress.setVisibility(View.GONE);

                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

                        for (int i = 0; i < response.body().getChapterList().size(); i++) {


                        }

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
        });


        sectionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final AllAPIs cr = retrofit.create(AllAPIs.class);

                Call<SectionListbean> call2 = cr.sectionList(b.school_id, classId.get(i));

                progress.setVisibility(View.VISIBLE);


                call2.enqueue(new Callback<SectionListbean>() {

                    @Override
                    public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                        for (int i = 0; i < response.body().getSectionList().size(); i++) {


                        }
                        Log.d("section", String.valueOf(sectionId.get(i)));
                        sId = sectionId.get(i);


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



                                progress.setVisibility(View.GONE);

                                Log.d("name", String.valueOf(studentlist.get(0)));

                            }

                            @Override
                            public void onFailure(Call<StudentListbean> call, Throwable throwable) {
                                progress.setVisibility(View.GONE);

                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }
                });


                Call<SubjectListBean> call1 = cr.subjectList(b.school_id, classId.get(i),sectionId.get(i));

                progress.setVisibility(View.VISIBLE);

                call1.enqueue(new Callback<SubjectListBean>() {

                    @Override
                    public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {

                        listSubject = response.body().getSubjectList();
                        subjectlist.clear();
                        subjectId.clear();


                        for (int i = 0; i < response.body().getSubjectList().size(); i++) {

                            subjectlist.add(response.body().getSubjectList().get(i).getSubjectName());
                            subjectId.add(response.body().getSubjectList().get(i).getSubjectId());
                        }

                        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, subjectlist);

                        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjectName.setAdapter(adp1);
                        adp1.notifyDataSetChanged();


                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        date.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                android.app.DialogFragment newFragment = new DatePickerFragment2();
                newFragment.show(getActivity().getFragmentManager(), "df");


            }
        });

        due_date.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                android.app.DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getFragmentManager(), "df");


            }
        });





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(false);
                dialog.setMessage("Are you sure you want to add Home Work ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        sNote = note.getText().toString().trim();

                        if (!due_date.getText().toString().equals("Due Date")) {


                            Log.d("note", cId);



                            JSONArray jsonArray = new JSONArray();

                            for (int i=0;i<studentId.size();i++){
                                JSONObject object =new JSONObject();

                                try {
                                    object.put("Id",studentId.get(i));
                                    jsonArray.put(object);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            JSONObject jsonObject=new JSONObject();
                            try {
                                jsonObject.put("student",jsonArray);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("dsds",jsonObject.toString());

                            File file = new File(mCurrentPhotoPath);
                            final User b = (User) getActivity().getApplicationContext();

                            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                            MultipartBody.Part body = MultipartBody.Part.createFormData("attach", file.getName(), reqFile);


                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            final AllAPIs cr = retrofit.create(AllAPIs.class);


                            Call<AssignHWbean> call3 = cr.assign_hw(b.school_id, cId, sId, ssId, sChapter, sNote, due_date.getText().toString(), studentId, body, b.user_id);

                            progress.setVisibility(View.VISIBLE);

                            Log.d("outputtt", String.valueOf(sId));
                            call3.enqueue(new Callback<AssignHWbean>() {

                                @Override
                                public void onResponse(Call<AssignHWbean> call3, Response<AssignHWbean> response) {


                                    if (response.body().getStatus().equals("1")) {
                                        Toast.makeText(getContext(), "Home Work Added Successfully.", Toast.LENGTH_LONG).show();
                                        note.setText(" ");
                                    } else {
                                        Toast.makeText(getContext(), "Home work did not added Successfully!", Toast.LENGTH_LONG).show();
                                    }
                                    progress.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<AssignHWbean> call3, Throwable throwable) {
                                    Log.d("yooooo", "sds");
                                    progress.setVisibility(View.GONE);

                                }
                            });
                        }else
                            due_date.setError("Invalid date");

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


        return v;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            try {
                bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(String.valueOf(data.getData())));

                //browse_image.setImageBitmap(bitmap);
                Uri selectedImageUri = data.getData();

                mCurrentPhotoPath = getPath(getActivity().getApplicationContext(), selectedImageUri);

                //Log.d("asdasdasd", mCurrentPhotoPath);

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


    @SuppressLint("ValidFragment")
    public class DatePickerFragment2 extends android.app.DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int day) {
            String years = "" + year;
            String months = "" + (monthOfYear + 1);
            String days = "" + day;

            if (monthOfYear >= 0 && monthOfYear < 9) {
                months = "0" + (monthOfYear + 1);
            }
            if (day > 0 && day < 10) {
                days = "0" + day;

            }

            CharSequence strDate = null;
            Time chosenDate = new Time();
            chosenDate.set(day, monthOfYear, year);
            long dtDob = chosenDate.toMillis(true);
            strDate = DateFormat.format("dd-MMMM-yyyy", dtDob);
            date.setText(strDate);


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


    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends android.app.DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int day) {
           /* String years = "" + year;
            String months = "" + (monthOfYear + 1);
            String days = "" + day;
            if (monthOfYear >= 0 && monthOfYear < 9) {
                months = "0" + (monthOfYear + 1);
            }
            if (day > 0 && day < 10) {
                days = "0" + day;

            }
            due_date.setText(days + "/" + months + "/" + years);*/

            CharSequence strDate = null;
            Time chosenDate = new Time();
            chosenDate.set(day, monthOfYear, year);
            long dtDob = chosenDate.toMillis(true);
            strDate = DateFormat.format("dd-MMMM-yyyy", dtDob);
            due_date.setText(strDate);
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


}

