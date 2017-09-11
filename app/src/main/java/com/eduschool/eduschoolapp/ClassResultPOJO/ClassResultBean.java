package com.eduschool.eduschoolapp.ClassResultPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 04/09/2017.
 */

public class ClassResultBean {
    @SerializedName("class_result")
    @Expose
    private List<ClassResult> classResult = null;

    public List<ClassResult> getClassResult() {
        return classResult;
    }

    public void setClassResult(List<ClassResult> classResult) {
        this.classResult = classResult;
    }

}