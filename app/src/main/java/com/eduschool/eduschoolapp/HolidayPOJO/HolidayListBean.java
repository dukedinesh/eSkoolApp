package com.eduschool.eduschoolapp.HolidayPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/22/2017.
 */

public class HolidayListBean {
    @SerializedName("holiday_list")
    @Expose
    private List<HolidayList> holidayList = null;

    public List<HolidayList> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<HolidayList> holidayList) {
        this.holidayList = holidayList;
    }

}