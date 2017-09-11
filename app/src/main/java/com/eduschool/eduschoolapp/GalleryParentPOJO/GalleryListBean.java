package com.eduschool.eduschoolapp.GalleryParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/16/2017.
 */

public class GalleryListBean {
    @SerializedName("gallery_list")
    @Expose
    private List<GalleryList> galleryList = null;

    public List<GalleryList> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(List<GalleryList> galleryList) {
        this.galleryList = galleryList;
    }

}