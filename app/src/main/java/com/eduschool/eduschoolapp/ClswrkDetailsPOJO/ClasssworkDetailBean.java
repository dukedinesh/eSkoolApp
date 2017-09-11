package com.eduschool.eduschoolapp.ClswrkDetailsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/18/2017.
 */

public class ClasssworkDetailBean {
    @SerializedName("classswork_detail")
    @Expose
    private List<ClasssworkDetail> classsworkDetail = null;

    public List<ClasssworkDetail> getClasssworkDetail() {
        return classsworkDetail;
    }

    public void setClasssworkDetail(List<ClasssworkDetail> classsworkDetail) {
        this.classsworkDetail = classsworkDetail;
    }

}
