package com.eduschool.eduschoolapp.ClassWrkParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/16/2017.
 */

public class SubjectList {
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    @SerializedName("newclasswork")
    @Expose
    private String newclasswork;
    @SerializedName("status")
    @Expose
    private String status;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getNewclasswork() {
        return newclasswork;
    }

    public void setNewclasswork(String newclasswork) {
        this.newclasswork = newclasswork;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}