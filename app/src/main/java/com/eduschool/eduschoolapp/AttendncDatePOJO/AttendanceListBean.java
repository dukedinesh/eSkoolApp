package com.eduschool.eduschoolapp.AttendncDatePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/21/2017.
 */

public class AttendanceListBean {

    @SerializedName("attendance_list")
    @Expose
    private List<AttendanceList> attendanceList = null;

    public List<AttendanceList> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<AttendanceList> attendanceList) {
        this.attendanceList = attendanceList;
    }

}
