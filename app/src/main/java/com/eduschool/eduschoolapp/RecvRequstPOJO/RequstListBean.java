package com.eduschool.eduschoolapp.RecvRequstPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 09-09-2017.
 */

public class RequstListBean {
    @SerializedName("request_list")
    @Expose
    private List<RequestList> requestList = null;

    public List<RequestList> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<RequestList> requestList) {
        this.requestList = requestList;
    }

}
