package com.eduschool.eduschoolapp.UpdateAlbumPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/9/2017.
 */

public class UpdateAlbumBean {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("album_id")
    @Expose
    private String albumId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

}
