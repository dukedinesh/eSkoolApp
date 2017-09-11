package com.eduschool.eduschoolapp.OnlineTestListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 30/08/2017.
 */

public class OnlinetestList {
    @SerializedName("onlinetest_id")
    @Expose
    private String onlinetestId;
    @SerializedName("test_name")
    @Expose
    private String testName;
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
    @SerializedName("max_score")
    @Expose
    private String maxScore;
    @SerializedName("min_score")
    @Expose
    private String minScore;
    @SerializedName("pass_percantage")
    @Expose
    private String passPercantage;
    @SerializedName("chapter_data")
    @Expose
    private List<ChapterDatum> chapterData = null;
    @SerializedName("take_test")
    @Expose
    private TakeTest takeTest;

    public String getOnlinetestId() {
        return onlinetestId;
    }

    public void setOnlinetestId(String onlinetestId) {
        this.onlinetestId = onlinetestId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
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

    public String getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(String maxScore) {
        this.maxScore = maxScore;
    }

    public String getMinScore() {
        return minScore;
    }

    public void setMinScore(String minScore) {
        this.minScore = minScore;
    }

    public String getPassPercantage() {
        return passPercantage;
    }

    public void setPassPercantage(String passPercantage) {
        this.passPercantage = passPercantage;
    }

    public List<ChapterDatum> getChapterData() {
        return chapterData;
    }

    public void setChapterData(List<ChapterDatum> chapterData) {
        this.chapterData = chapterData;
    }

    public TakeTest getTakeTest() {
        return takeTest;
    }

    public void setTakeTest(TakeTest takeTest) {
        this.takeTest = takeTest;
    }

}
