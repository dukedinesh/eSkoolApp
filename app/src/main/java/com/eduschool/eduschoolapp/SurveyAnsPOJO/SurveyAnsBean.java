package com.eduschool.eduschoolapp.SurveyAnsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 02/09/2017.
 */

public class SurveyAnsBean {
    @SerializedName("survey_answer")
    @Expose
    private List<SurveyAnswer> surveyAnswer = null;

    public List<SurveyAnswer> getSurveyAnswer() {
        return surveyAnswer;
    }

    public void setSurveyAnswer(List<SurveyAnswer> surveyAnswer) {
        this.surveyAnswer = surveyAnswer;
    }

}
