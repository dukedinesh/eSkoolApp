package com.eduschool.eduschoolapp.GalleryParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/16/2017.
 */

public class GalleryList  {

    @SerializedName("gallery_id")
    @Expose
    private String galleryId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("album_id")
    @Expose
    private String albumId;
    @SerializedName("album_name")
    @Expose
    private String albumName;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("gallery_image")
    @Expose
    private List<GalleryImage> galleryImage = null;
    @SerializedName("gallery_class")
    @Expose
    private List<Object> galleryClass = null;
    @SerializedName("gallery_section")
    @Expose
    private List<Object> gallerySection = null;
    @SerializedName("total_student")
    @Expose
    private Object totalStudent;
    @SerializedName("gallery_student")
    @Expose
    private List<Object> galleryStudent = null;

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<GalleryImage> getGalleryImage() {
        return galleryImage;
    }

    public void setGalleryImage(List<GalleryImage> galleryImage) {
        this.galleryImage = galleryImage;
    }

    public List<Object> getGalleryClass() {
        return galleryClass;
    }

    public void setGalleryClass(List<Object> galleryClass) {
        this.galleryClass = galleryClass;
    }

    public List<Object> getGallerySection() {
        return gallerySection;
    }

    public void setGallerySection(List<Object> gallerySection) {
        this.gallerySection = gallerySection;
    }

    public Object getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(Object totalStudent) {
        this.totalStudent = totalStudent;
    }

    public List<Object> getGalleryStudent() {
        return galleryStudent;
    }

    public void setGalleryStudent(List<Object> galleryStudent) {
        this.galleryStudent = galleryStudent;
    }

}