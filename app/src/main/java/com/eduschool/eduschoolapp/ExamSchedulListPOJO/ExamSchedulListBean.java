package com.eduschool.eduschoolapp.ExamSchedulListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 07/09/2017.
 */

public class ExamSchedulListBean {
    @SerializedName("exam_schedul_list")
    @Expose
    private List<ExamSchedulList> examSchedulList = null;

    public List<ExamSchedulList> getExamSchedulList() {
        return examSchedulList;
    }

    public void setExamSchedulList(List<ExamSchedulList> examSchedulList) {
        this.examSchedulList = examSchedulList;
    }

}
