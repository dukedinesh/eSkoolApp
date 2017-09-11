package com.eduschool.eduschoolapp.BirthStuListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/23/2017.
 */

public class BirthStuListBean {
    @SerializedName("birth_stu_list")
    @Expose
    private List<BirthStuList> birthStuList = null;

    public List<BirthStuList> getBirthStuList() {
        return birthStuList;
    }

    public void setBirthStuList(List<BirthStuList> birthStuList) {
        this.birthStuList = birthStuList;
    }

}
