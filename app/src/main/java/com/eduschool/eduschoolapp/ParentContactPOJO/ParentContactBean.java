package com.eduschool.eduschoolapp.ParentContactPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dinesh  on 11-09-2017.
 */

public class ParentContactBean {

    @SerializedName("contact_name")
    @Expose
    private String contactName;
    @SerializedName("contact_email")
    @Expose
    private String contactEmail;
    @SerializedName("contact_phone")
    @Expose
    private String contactPhone;
    @SerializedName("contact_tempaddress")
    @Expose
    private String contactTempaddress;
    @SerializedName("contact_permaaddress")
    @Expose
    private String contactPermaaddress;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactTempaddress() {
        return contactTempaddress;
    }

    public void setContactTempaddress(String contactTempaddress) {
        this.contactTempaddress = contactTempaddress;
    }

    public String getContactPermaaddress() {
        return contactPermaaddress;
    }

    public void setContactPermaaddress(String contactPermaaddress) {
        this.contactPermaaddress = contactPermaaddress;
    }

}