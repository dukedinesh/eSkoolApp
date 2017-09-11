package com.eduschool.eduschoolapp.ResultPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 02/09/2017.
 */

public class StudentList {
    @SerializedName("roll_no")
    @Expose
    private String rollNo;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("stu_marks")
    @Expose
    private List<StuMark> stuMarks = null;

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<StuMark> getStuMarks() {
        return stuMarks;
    }

    public void setStuMarks(List<StuMark> stuMarks) {
        this.stuMarks = stuMarks;
    }

}
