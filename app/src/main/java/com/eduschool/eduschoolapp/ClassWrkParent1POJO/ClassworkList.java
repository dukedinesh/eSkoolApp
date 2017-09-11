package com.eduschool.eduschoolapp.ClassWrkParent1POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/16/2017.
 */

public class ClassworkList {
    @SerializedName("classwork_id")
    @Expose
    private String classworkId;
    @SerializedName("classwork_title")
    @Expose
    private String classworkTitle;
    @SerializedName("classwork_status")
    @Expose
    private String classworkStatus;
    @SerializedName("additional_note")
    @Expose
    private String additionalNote;
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    @SerializedName("chapter_id")
    @Expose
    private String chapterId;
    @SerializedName("chapter_name")
    @Expose
    private String chapterName;
    @SerializedName("posted_date")
    @Expose
    private String postedDate;
    @SerializedName("status")
    @Expose
    private String status;

    public String getClassworkId() {
        return classworkId;
    }

    public void setClassworkId(String classworkId) {
        this.classworkId = classworkId;
    }

    public String getClassworkTitle() {
        return classworkTitle;
    }

    public void setClassworkTitle(String classworkTitle) {
        this.classworkTitle = classworkTitle;
    }

    public String getClassworkStatus() {
        return classworkStatus;
    }

    public void setClassworkStatus(String classworkStatus) {
        this.classworkStatus = classworkStatus;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }

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

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}