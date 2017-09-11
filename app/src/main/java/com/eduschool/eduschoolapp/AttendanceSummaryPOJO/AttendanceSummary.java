package com.eduschool.eduschoolapp.AttendanceSummaryPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/22/2017.
 */

public class AttendanceSummary {

    @SerializedName("date")
    @Expose
    private String date;
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
    @SerializedName("total_student")
    @Expose
    private Integer totalStudent;
    @SerializedName("total_leave_stu")
    @Expose
    private Integer totalLeaveStu;
    @SerializedName("total_present_stu")
    @Expose
    private Integer totalPresentStu;
    @SerializedName("total_absent_stu")
    @Expose
    private Integer totalAbsentStu;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Integer getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(Integer totalStudent) {
        this.totalStudent = totalStudent;
    }

    public Integer getTotalLeaveStu() {
        return totalLeaveStu;
    }

    public void setTotalLeaveStu(Integer totalLeaveStu) {
        this.totalLeaveStu = totalLeaveStu;
    }

    public Integer getTotalPresentStu() {
        return totalPresentStu;
    }

    public void setTotalPresentStu(Integer totalPresentStu) {
        this.totalPresentStu = totalPresentStu;
    }

    public Integer getTotalAbsentStu() {
        return totalAbsentStu;
    }

    public void setTotalAbsentStu(Integer totalAbsentStu) {
        this.totalAbsentStu = totalAbsentStu;
    }

}
