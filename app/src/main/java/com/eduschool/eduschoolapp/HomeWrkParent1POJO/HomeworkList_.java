package com.eduschool.eduschoolapp.HomeWrkParent1POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/14/2017.
 */

public class HomeworkList_ {
    @SerializedName("homework_id")
    @Expose
    private String homeworkId;
    @SerializedName("homework_title")
    @Expose
    private String homeworkTitle;
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
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("homeworkstatus_id")
    @Expose
    private String homeworkstatusId;
    @SerializedName("homework_status")
    @Expose
    private String homeworkStatus;

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getHomeworkstatusId() {
        return homeworkstatusId;
    }

    public void setHomeworkstatusId(String homeworkstatusId) {
        this.homeworkstatusId = homeworkstatusId;
    }

    public String getHomeworkStatus() {
        return homeworkStatus;
    }

    public void setHomeworkStatus(String homeworkStatus) {
        this.homeworkStatus = homeworkStatus;
    }

}