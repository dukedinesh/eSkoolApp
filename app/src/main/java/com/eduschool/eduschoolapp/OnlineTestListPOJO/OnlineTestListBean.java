package com.eduschool.eduschoolapp.OnlineTestListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 30/08/2017.
 */

public class OnlineTestListBean {
    @SerializedName("onlinetest_list")
    @Expose
    private List<OnlinetestList> onlinetestList = null;

    public List<OnlinetestList> getOnlinetestList() {
        return onlinetestList;
    }

    public void setOnlinetestList(List<OnlinetestList> onlinetestList) {
        this.onlinetestList = onlinetestList;
    }

}