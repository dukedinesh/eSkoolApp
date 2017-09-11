package com.eduschool.eduschoolapp.ClassWrkParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/16/2017.
 */

public class ClasssubjectListBean {

    @SerializedName("classsubject_list")
    @Expose
    private List<ClasssubjectList> classsubjectList = null;

    public List<ClasssubjectList> getClasssubjectList() {
        return classsubjectList;
    }

    public void setClasssubjectList(List<ClasssubjectList> classsubjectList) {
        this.classsubjectList = classsubjectList;
    }

}
