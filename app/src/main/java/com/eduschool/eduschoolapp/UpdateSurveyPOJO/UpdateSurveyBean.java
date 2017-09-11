package com.eduschool.eduschoolapp.UpdateSurveyPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/17/2017.
 */

public class UpdateSurveyBean {
    @SerializedName("survey_listteacher")
    @Expose
    private String surveyListteacher;

    public String getSurveyListteacher() {
        return surveyListteacher;
    }

    public void setSurveyListteacher(String surveyListteacher) {
        this.surveyListteacher = surveyListteacher;
    }

}
