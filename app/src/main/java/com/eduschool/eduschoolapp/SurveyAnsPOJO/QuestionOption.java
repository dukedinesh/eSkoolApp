package com.eduschool.eduschoolapp.SurveyAnsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dinesh  on 02/09/2017.
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
