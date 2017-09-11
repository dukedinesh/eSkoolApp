package com.eduschool.eduschoolapp.SyllabusPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/23/2017.
 */

public class SyllabusList {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("exam_name")
    @Expose
    private String examName;
    @SerializedName("chapter_list_status")
    @Expose
    private List<ChapterListStatus> chapterListStatus = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public List<ChapterListStatus> getChapterListStatus() {
        return chapterListStatus;
    }

    public void setChapterListStatus(List<ChapterListStatus> chapterListStatus) {
        this.chapterListStatus = chapterListStatus;
    }

}
