package com.eduschool.eduschoolapp.ClssWrkPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/9/2017.
 */

public class ClasswrkBean {

    @SerializedName("classwork_data")
    @Expose
    private List<ClassworkDatum> classworkData = null;

    public List<ClassworkDatum> getClassworkData() {
        return classworkData;
    }

    public void setClassworkData(List<ClassworkDatum> classworkData) {
        this.classworkData = classworkData;
    }

}