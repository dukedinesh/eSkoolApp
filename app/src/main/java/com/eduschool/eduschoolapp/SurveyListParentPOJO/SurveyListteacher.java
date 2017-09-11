package com.eduschool.eduschoolapp.SurveyListParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/18/2017.
 */

public class SurveyListteacher {
    @SerializedName("survey_id")
    @Expose
    private String surveyId;
    @SerializedName("servey_data")
    @Expose
    private List<ServeyDatum> serveyData = null;

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public List<ServeyDatum> getServeyData() {
        return serveyData;
    }

    public void setServeyData(List<ServeyDatum> serveyData) {
        this.serveyData = serveyData;
    }

}