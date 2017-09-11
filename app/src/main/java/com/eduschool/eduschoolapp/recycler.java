package com.eduschool.eduschoolapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class recycler extends AppCompatActivity {

    String android_version_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb"
    };


    final int android_image_urls[] = {
            android.R.drawable.ic_dialog_alert,
            android.R.drawable.ic_menu_camera,
            android.R.drawable.ic_menu_compass,
            android.R.drawable.ic_menu_directions,
            android.R.drawable.ic_menu_gallery};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        initViews();





/*        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.bottomView);

       for (int x = 0; x < android_image_urls.length; x++) {
            final Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),
                    android_image_urls[x]);

            int width = bitmapOrg.getWidth();
            int height = bitmapOrg.getHeight();
            int newWidth = 200;
            int newHeight = 200;

            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;

            Matrix matrix = new Matrix();

            matrix.postScale(scaleWidth, scaleHeight);
            matrix.postRotate(0);

            Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width,
                    height, matrix, true);
            BitmapDrawable bmd = new BitmapDrawable(getResources(),
                    resizedBitmap);

            ImageView imageView = new ImageView(this);
            imageView.setPadding(2, 0, 9, 5);
            imageView.setImageDrawable(bmd);
            imageView.setTag(x);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View To) {

                    Intent intent = new Intent(recycler.this, New.class);
                    intent.putExtra("selected_image", android_image_urls);
                    startActivity(intent);
                }
            });

            linearLayout1.addView(imageView);
        }*/


        }
    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<AndroidVersion> androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<AndroidVersion> prepareData(){

        ArrayList<AndroidVersion> android_version = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setAndroid_version_name(android_version_names[i]);
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            android_version.add(androidVersion);
        }
                               return android_version;
    }



    }