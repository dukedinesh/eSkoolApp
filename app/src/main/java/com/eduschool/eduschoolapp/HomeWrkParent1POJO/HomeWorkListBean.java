package com.eduschool.eduschoolapp.HomeWrkParent1POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/14/2017.
 */

public class HomeWorkListBean {

    @SerializedName("homework_list")
    @Expose
    private List<HomeworkList> homeworkList = null;

    public List<HomeworkList> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(List<HomeworkList> homeworkList) {
        this.homeworkList = homeworkList;
    }

}
