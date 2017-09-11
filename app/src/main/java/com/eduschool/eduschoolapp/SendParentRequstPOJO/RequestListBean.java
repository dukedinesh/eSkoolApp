package com.eduschool.eduschoolapp.SendParentRequstPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 07/09/2017.
 */

public class RequestListBean {

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
