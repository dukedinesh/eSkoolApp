package com.eduschool.eduschoolapp.AttendanceSummaryPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/22/2017.
 */

public class AttendanceSummaryBean {

    @SerializedName("attendance_summary")
    @Expose
    private List<AttendanceSummary> attendanceSummary = null;

    public List<AttendanceSummary> getAttendanceSummary() {
        return attendanceSummary;
    }

    public void setAttendanceSummary(List<AttendanceSummary> attendanceSummary) {
        this.attendanceSummary = attendanceSummary;
    }

}
