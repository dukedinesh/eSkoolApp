package com.eduschool.eduschoolapp.SubjectListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 7/22/2017.
 */

public class SubjectListBean {

    @SerializedName("subject_list")
    @Expose
    private List<SubjectList> subjectList = null;

    public List<SubjectList> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectList> subjectList) {
        this.subjectList = subjectList;
    }

}