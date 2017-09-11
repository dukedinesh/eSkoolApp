package com.eduschool.eduschoolapp.ClssWrkPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/9/2017.
 */

public class ClassworkDatum {
    @SerializedName("classwork_id")
    @Expose
    private String classworkId;
    @SerializedName("title")
    @Expose
    private String title;
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
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("chapter_id")
    @Expose
    private String chapterId;
    @SerializedName("chapter")
    @Expose
    private String chapter;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("class_status")
    @Expose
    private String classStatus;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("total_student")
    @Expose
    private Integer totalStudent;
    @SerializedName("student_list")
    @Expose
    private List<StudentList> studentList = null;
    @SerializedName("file_attachment")
    @Expose
    private List<Object> fileAttachment = null;

    public String getClassworkId() {
        return classworkId;
    }

    public void setClassworkId(String classworkId) {
        this.classworkId = classworkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(Integer totalStudent) {
        this.totalStudent = totalStudent;
    }

    public List<StudentList> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentList> studentList) {
        this.studentList = studentList;
    }

    public List<Object> getFileAttachment() {
        return fileAttachment;
    }

    public void setFileAttachment(List<Object> fileAttachment) {
        this.fileAttachment = fileAttachment;
    }

}