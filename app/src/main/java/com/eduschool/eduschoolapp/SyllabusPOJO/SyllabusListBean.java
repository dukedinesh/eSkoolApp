package com.eduschool.eduschoolapp.SyllabusPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/23/2017.
 */

public class SyllabusListBean {
    @SerializedName("syllabus_list")
    @Expose
    private List<SyllabusList> syllabusList = null;

    public List<SyllabusList> getSyllabusList() {
        return syllabusList;
    }

    public void setSyllabusList(List<SyllabusList> syllabusList) {
        this.syllabusList = syllabusList;
    }

}
