package com.eduschool.eduschoolapp.BirthTechrListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dinesh  on 05/09/2017.
 */

public class BirthTechrList {

    @SerializedName("teachr_id")
    @Expose
    private String teachrId;
    @SerializedName("teachr_name")
    @Expose
    private String teachrName;

    public String getTeachrId() {
        return teachrId;
    }

    public void setTeachrId(String teachrId) {
        this.teachrId = teachrId;
    }

    public String getTeachrName() {
        return teachrName;
    }

    public void setTeachrName(String teachrName) {
        this.teachrName = teachrName;
    }

}
