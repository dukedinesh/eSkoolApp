package com.eduschool.eduschoolapp.SurveyListParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/18/2017.
 */

public class SurveyListBeanParent {
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