package com.eduschool.eduschoolapp.ClswrkDetailsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/18/2017.
 */

public class ClasssworkDetail {

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
    @SerializedName("posted_date")
    @Expose
    private String postedDate;

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

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

}