package com.eduschool.eduschoolapp.ExmNresultPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 02/09/2017.
 */

public class ExmTypeListBean {
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
