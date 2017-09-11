package com.eduschool.eduschoolapp.HomeWrkDetailsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/14/2017.
 */

public class HomeWrkDetailsBean {
    @SerializedName("homework_detail")
    @Expose
    private List<HomeworkDetail> homeworkDetail = null;

    public List<HomeworkDetail> getHomeworkDetail() {
        return homeworkDetail;
    }

    public void setHomeworkDetail(List<HomeworkDetail> homeworkDetail) {
        this.homeworkDetail = homeworkDetail;
    }

}
