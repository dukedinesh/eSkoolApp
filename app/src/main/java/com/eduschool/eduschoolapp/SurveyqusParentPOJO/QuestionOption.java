package com.eduschool.eduschoolapp.SurveyqusParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/18/2017.
 */

public class QuestionOption {
    @SerializedName("option_id")
    @Expose
    private String optionId;
    @SerializedName("option_value")
    @Expose
    private String optionValue;

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

}