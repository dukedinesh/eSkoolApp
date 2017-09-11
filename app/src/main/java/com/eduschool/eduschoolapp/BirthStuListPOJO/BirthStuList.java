package com.eduschool.eduschoolapp.BirthStuListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/23/2017.
 */

public class BirthStuList {


    @SerializedName("stu_id")
    @Expose
    private String stuId;
    @SerializedName("stu_name")
    @Expose
    private String stuName;
    @SerializedName("class_id")
    @Expose
    private String classId;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("section_id")
    @Expose
    private String sectionId;
    @SerializedName("section")
    @Expose
    private String section;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}