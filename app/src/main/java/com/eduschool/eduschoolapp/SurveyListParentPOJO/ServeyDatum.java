package com.eduschool.eduschoolapp.SurveyListParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dinesh  on 02/09/2017.
 */

public class ServeyDatum {

    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("post_date")
    @Expose
    private String postDate;
    @SerializedName("surveyid")
    @Expose
    private String surveyid;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("question_status")
    @Expose
    private String questionStatus;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getSurveyid() {
        return surveyid;
    }

    public void setSurveyid(String surveyid) {
        this.surveyid = surveyid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

}