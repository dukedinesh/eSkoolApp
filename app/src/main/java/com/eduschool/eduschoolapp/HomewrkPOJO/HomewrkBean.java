package com.eduschool.eduschoolapp.HomewrkPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/2/2017.
 */

public class HomewrkBean {

    @SerializedName("homework_data")
    @Expose
    private List<HomeworkDatum> homeworkData = null;

    public List<HomeworkDatum> getHomeworkData() {
        return homeworkData;
    }

    public void setHomeworkData(List<HomeworkDatum> homeworkData) {
        this.homeworkData = homeworkData;
    }

}

