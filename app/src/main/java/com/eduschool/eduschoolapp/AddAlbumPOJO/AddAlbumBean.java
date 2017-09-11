package com.eduschool.eduschoolapp.AddAlbumPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/5/2017.
 */

public class AddAlbumBean {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("album_id")
    @Expose
    private Integer albumId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

}