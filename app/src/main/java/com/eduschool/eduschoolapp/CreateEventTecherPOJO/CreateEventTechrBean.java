package com.eduschool.eduschoolapp.CreateEventTecherPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dinesh  on 09-09-2017.
 */

public class CreateEventTechrBean {

    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}