package com.eduschool.eduschoolapp.HomewrkParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/14/2017.
 */

public class ParentSubjectListBean {
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
