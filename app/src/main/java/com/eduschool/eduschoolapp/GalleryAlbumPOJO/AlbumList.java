package com.eduschool.eduschoolapp.GalleryAlbumPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/5/2017.
 */

public class AlbumList {
    @SerializedName("album_create_date")
    @Expose
    private String albumCreateDate;
    @SerializedName("created_date")
    @Expose
    private List<CreatedDate> createdDate = null;

    public String getAlbumCreateDate() {
        return albumCreateDate;
    }

    public void setAlbumCreateDate(String albumCreateDate) {
        this.albumCreateDate = albumCreateDate;
    }

    public List<CreatedDate> getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(List<CreatedDate> createdDate) {
        this.createdDate = createdDate;
    }

}