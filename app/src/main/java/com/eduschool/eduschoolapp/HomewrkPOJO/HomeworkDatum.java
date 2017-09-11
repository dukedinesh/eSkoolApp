package com.eduschool.eduschoolapp.HomewrkPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/2/2017.
 */

public class HomeworkDatum  {
    @SerializedName("homework_id")
    @Expose
    private String homeworkId;
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
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("total_student")
    @Expose
    private Integer totalStudent;
    @SerializedName("total_completehomwork_student")
    @Expose
    private Integer totalCompletehomworkStudent;
    @SerializedName("completehomwork_student")
    @Expose
    private List<CompletehomworkStudent> completehomworkStudent = null;
    @SerializedName("total_pendinghomwork_student")
    @Expose
    private Integer totalPendinghomworkStudent;
    @SerializedName("pendinghomwork_student")
    @Expose
    private List<PendinghomworkStudent> pendinghomworkStudent = null;
    @SerializedName("file_attachment")
    @Expose
    private List<Object> fileAttachment = null;

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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

    public Integer getTotalCompletehomworkStudent() {
        return totalCompletehomworkStudent;
    }

    public void setTotalCompletehomworkStudent(Integer totalCompletehomworkStudent) {
        this.totalCompletehomworkStudent = totalCompletehomworkStudent;
    }

    public List<CompletehomworkStudent> getCompletehomworkStudent() {
        return completehomworkStudent;
    }

    public void setCompletehomworkStudent(List<CompletehomworkStudent> completehomworkStudent) {
        this.completehomworkStudent = completehomworkStudent;
    }

    public Integer getTotalPendinghomworkStudent() {
        return totalPendinghomworkStudent;
    }

    public void setTotalPendinghomworkStudent(Integer totalPendinghomworkStudent) {
        this.totalPendinghomworkStudent = totalPendinghomworkStudent;
    }

    public List<PendinghomworkStudent> getPendinghomworkStudent() {
        return pendinghomworkStudent;
    }

    public void setPendinghomworkStudent(List<PendinghomworkStudent> pendinghomworkStudent) {
        this.pendinghomworkStudent = pendinghomworkStudent;
    }

    public List<Object> getFileAttachment() {
        return fileAttachment;
    }

    public void setFileAttachment(List<Object> fileAttachment) {
        this.fileAttachment = fileAttachment;
    }

}