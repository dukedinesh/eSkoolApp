package com.eduschool.eduschoolapp.BirthTechrListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 05/09/2017.
 */

public class BirthTechrListBean {
    @SerializedName("birth_techr_list")
    @Expose
    private List<BirthTechrList> birthTechrList = null;

    public List<BirthTechrList> getBirthTechrList() {
        return birthTechrList;
    }

    public void setBirthTechrList(List<BirthTechrList> birthTechrList) {
        this.birthTechrList = birthTechrList;
    }

}
