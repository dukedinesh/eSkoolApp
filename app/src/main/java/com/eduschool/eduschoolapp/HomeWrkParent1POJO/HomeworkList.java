package com.eduschool.eduschoolapp.HomeWrkParent1POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/14/2017.
 */

public class HomeworkList {
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
    @SerializedName("no_of_homeworklist")
    @Expose
    private Integer noOfHomeworklist;
    @SerializedName("homework_list")
    @Expose
    private List<HomeworkList_> homeworkList = null;

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

    public Integer getNoOfHomeworklist() {
        return noOfHomeworklist;
    }

    public void setNoOfHomeworklist(Integer noOfHomeworklist) {
        this.noOfHomeworklist = noOfHomeworklist;
    }

    public List<HomeworkList_> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(List<HomeworkList_> homeworkList) {
        this.homeworkList = homeworkList;
    }

}