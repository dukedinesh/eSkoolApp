package com.eduschool.eduschoolapp.ExmListParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 04/09/2017.
 */

public class ExamTypeListBean {
    @SerializedName("exam_type_list")
    @Expose

    private List<ExamTypeList> examTypeList = null;

    public List<ExamTypeList> getExamTypeList() {
        return examTypeList;
    }

    public void setExamTypeList(List<ExamTypeList> examTypeList) {
        this.examTypeList = examTypeList;
    }

}