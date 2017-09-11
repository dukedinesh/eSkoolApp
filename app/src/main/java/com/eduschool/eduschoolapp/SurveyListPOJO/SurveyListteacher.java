package com.eduschool.eduschoolapp.SurveyListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/12/2017.
 */

public class SurveyListteacher {
    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("survey_id")
    @Expose
    private String surveyId;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("post_date")
    @Expose
    private String postDate;
    @SerializedName("question_status")
    @Expose
    private String questionStatus;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

}