package com.eduschool.eduschoolapp.HomeWrkUpdPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/14/2017.
 */

public class HomeworkUpdateBean {

    @SerializedName("homework_update")
    @Expose
    private List<HomeworkUpdate> homeworkUpdate = null;

    public List<HomeworkUpdate> getHomeworkUpdate() {
        return homeworkUpdate;
    }

    public void setHomeworkUpdate(List<HomeworkUpdate> homeworkUpdate) {
        this.homeworkUpdate = homeworkUpdate;
    }

}
