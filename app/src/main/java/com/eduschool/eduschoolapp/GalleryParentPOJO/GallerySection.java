package com.eduschool.eduschoolapp.GalleryParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/16/2017.
 */

public class GallerySection {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("gallery_id")
    @Expose
    private String galleryId;
    @SerializedName("section")
    @Expose
    private String section;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}