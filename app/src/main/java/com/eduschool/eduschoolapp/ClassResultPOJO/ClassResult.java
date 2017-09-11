package com.eduschool.eduschoolapp.ClassResultPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dinesh  on 04/09/2017.
 */

public class ClassResult {
    @SerializedName("classid")
    @Expose
    private String classid;
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("section_id")
    @Expose
    private String sectionId;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("exam_id")
    @Expose
    private String examId;
    @SerializedName("exam_name")
    @Expose
    private String examName;
    @SerializedName("total_student")
    @Expose
    private Integer totalStudent;
    @SerializedName("total_pass_student")
    @Expose
    private Integer totalPassStudent;
    @SerializedName("total_fail_student")
    @Expose
    private Integer totalFailStudent;

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Integer getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(Integer totalStudent) {
        this.totalStudent = totalStudent;
    }

    public Integer getTotalPassStudent() {
        return totalPassStudent;
    }

    public void setTotalPassStudent(Integer totalPassStudent) {
        this.totalPassStudent = totalPassStudent;
    }

    public Integer getTotalFailStudent() {
        return totalFailStudent;
    }

    public void setTotalFailStudent(Integer totalFailStudent) {
        this.totalFailStudent = totalFailStudent;
    }

}
