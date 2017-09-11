package com.eduschool.eduschoolapp.SurveyListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/12/2017.
 */

public class SurveyListBean {
    @SerializedName("survey_listteacher")
    @Expose
    private List<SurveyListteacher> surveyListteacher = null;

    public List<SurveyListteacher> getSurveyListteacher() {
        return surveyListteacher;
    }

    public void setSurveyListteacher(List<SurveyListteacher> surveyListteacher) {
        this.surveyListteacher = surveyListteacher;
    }

}
