package com.eduschool.eduschoolapp.SyllabusPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/23/2017.
 */

public class ChapterListStatus {
    @SerializedName("chapter_name")
    @Expose
    private String chapterName;
    @SerializedName("chapter_status")
    @Expose
    private String chapterStatus;

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterStatus() {
        return chapterStatus;
    }

    public void setChapterStatus(String chapterStatus) {
        this.chapterStatus = chapterStatus;
    }

}