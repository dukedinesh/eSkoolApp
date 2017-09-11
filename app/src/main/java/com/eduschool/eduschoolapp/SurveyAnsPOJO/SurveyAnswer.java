package com.eduschool.eduschoolapp.SurveyAnsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dinesh  on 02/09/2017.
 */

public class SurveyAnswer {
    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("survey_id")
    @Expose
    private String surveyId;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("question_option")
    @Expose
    private List<QuestionOption> questionOption = null;
    @SerializedName("real_answer_id")
    @Expose
    private String realAnswerId;
    @SerializedName("your_answer")
    @Expose
    private String yourAnswer;

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

    public List<QuestionOption> getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(List<QuestionOption> questionOption) {
        this.questionOption = questionOption;
    }

    public String getRealAnswerId() {
        return realAnswerId;
    }

    public void setRealAnswerId(String realAnswerId) {
        this.realAnswerId = realAnswerId;
    }

    public String getYourAnswer() {
        return yourAnswer;
    }

    public void setYourAnswer(String yourAnswer) {
        this.yourAnswer = yourAnswer;
    }

}
