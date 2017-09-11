package com.eduschool.eduschoolapp.SurveyQusPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/14/2017.
 */

public class QuestionOption {
    @SerializedName("option_id")
    @Expose
    private int optionId;
    @SerializedName("option_value")
    @Expose
    private String optionValue;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

}
