package com.eduschool.eduschoolapp.GalleryAlbumPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/5/2017.
 */

public class AlbumListBean   {
    @SerializedName("album_list")
    @Expose
    private List<AlbumList> albumList = null;

    public List<AlbumList> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<AlbumList> albumList) {
        this.albumList = albumList;
    }

}