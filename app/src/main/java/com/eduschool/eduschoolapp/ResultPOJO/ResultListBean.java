package com.eduschool.eduschoolapp.ResultPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 02/09/2017.
 */

public class ResultListBean {

    @SerializedName("result_list")
    @Expose
    private List<ResultList> resultList = null;

    public List<ResultList> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultList> resultList) {
        this.resultList = resultList;
    }

}
