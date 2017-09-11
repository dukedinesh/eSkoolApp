package com.eduschool.eduschoolapp;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearSmoothScroller;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryListBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class New extends AppCompatActivity {


    ViewPager viewPager;
    MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new);


        viewPager = (ViewPager) findViewById(R.id.view);
        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);


        String pos = getIntent().getStringExtra("position");
        int i=Integer.parseInt(pos);
        viewPager.setCurrentItem(i);


        /*User b = (User)getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<GalleryListBean> call = cr.gallery_img(b.school_id, albumId, b.user_id);


        call.enqueue(new Callback<GalleryListBean>() {
            @Override
            public void onResponse(Call<GalleryListBean> call, Response<GalleryListBean> response) {



                Log.d("sdvs","sdvsdv");

            }

            @Override
            public void onFailure(Call<GalleryListBean> call, Throwable throwable) {

               // progress.setVisibility(View.GONE);

            }
        });*/




    }


    private class MyPagerAdapter extends PagerAdapter {

        List<Bitmap> lb = new ArrayList<>();

        int[] res = {
                R.drawable.teacher,
                R.drawable.studentimage,
                R.drawable.logo1,
                R.drawable.school,
                R.drawable.logo1};

        int[] backgroundcolor = {
                0xFF101010,
                0xFF202020,
                0xFF303030,
                0xFF404040,
                0xFF505050};

        @Override
        public int getCount() {
            return res.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(New.this);
            imageView.setImageResource(res[position]);

            ImageLoader loader = ImageLoader.getInstance();
            loader.loadImage("url", new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    lb.add(loadedImage);


                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });

            ViewGroup.LayoutParams imageParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(imageParams);

            LinearLayout layout = new LinearLayout(New.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layout.setBackgroundColor(backgroundcolor[position]);
            layout.setLayoutParams(layoutParams);
            layout.addView(imageView);

            final int page = position;
            layout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(New.this,
                            "Page " + page + " clicked",
                            Toast.LENGTH_LONG).show();
                }
            });


            container.addView(layout);
            return layout;



        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }

    }
}
