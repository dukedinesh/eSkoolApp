package com.eduschool.eduschoolapp.GalleryParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/16/2017.
 */

public class GalleryClas {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("gallery_id")
    @Expose
    private String galleryId;
    @SerializedName("class")
    @Expose
    private String _class;

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

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }
}
