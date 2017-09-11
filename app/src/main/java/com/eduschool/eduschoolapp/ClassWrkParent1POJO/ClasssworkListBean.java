package com.eduschool.eduschoolapp.ClassWrkParent1POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/16/2017.
 */

public class ClasssworkListBean {
    @SerializedName("classswork_list")
    @Expose
    private List<ClasssworkList> classsworkList = null;

    public List<ClasssworkList> getClasssworkList() {
        return classsworkList;
    }

    public void setClasssworkList(List<ClasssworkList> classsworkList) {
        this.classsworkList = classsworkList;
    }

}