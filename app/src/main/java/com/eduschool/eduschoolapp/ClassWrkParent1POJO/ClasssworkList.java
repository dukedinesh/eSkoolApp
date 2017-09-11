package com.eduschool.eduschoolapp.ClassWrkParent1POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/16/2017.
 */

public class ClasssworkList  {
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("student_roll")
    @Expose
    private String studentRoll;
    @SerializedName("class_id")
    @Expose
    private String classId;
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("section_id")
    @Expose
    private String sectionId;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("no_of_classworklist")
    @Expose
    private Integer noOfClassworklist;
    @SerializedName("classwork_list")
    @Expose
    private List<ClassworkList> classworkList = null;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentRoll() {
        return studentRoll;
    }

    public void setStudentRoll(String studentRoll) {
        this.studentRoll = studentRoll;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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

    public Integer getNoOfClassworklist() {
        return noOfClassworklist;
    }

    public void setNoOfClassworklist(Integer noOfClassworklist) {
        this.noOfClassworklist = noOfClassworklist;
    }

    public List<ClassworkList> getClassworkList() {
        return classworkList;
    }

    public void setClassworkList(List<ClassworkList> classworkList) {
        this.classworkList = classworkList;
    }

}
