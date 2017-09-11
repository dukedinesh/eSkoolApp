package com.eduschool.eduschoolapp;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class User extends Application {
    public boolean back=false;

    public String baseURL = "http://technobrix.in/newtbx/";
    public String school_id,user_id,user_type,user_name,user_class,user_section,class_teacher,class_Name,section_Name;

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonty.ttf");

    }
}



