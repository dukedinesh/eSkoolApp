package com.eduschool.eduschoolapp.GalleryParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/16/2017.
 */

public class GalleryStudent {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("gallery_id")
    @Expose
    private String galleryId;
    @SerializedName("stu_id")
    @Expose
    private String stuId;
    @SerializedName("stu_name")
    @Expose
    private String stuName;

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

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

}