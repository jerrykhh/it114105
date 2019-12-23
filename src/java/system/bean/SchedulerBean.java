/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.bean;

import java.io.Serializable;

/**
 *
 * @author JerryKwok
 */
public class SchedulerBean implements Serializable{

    private String id;
    private String title;
    private String description;
    private String start_date;
    private String end_date;
    private boolean holiday;
    private boolean schoolDay;
    private int start_day_week;
    private int end_date_week;
    private int countDate;
    private int countBusineseDay;

    public int getCountBusineseDay() {
        return countBusineseDay;
    }

    public void setCountBusineseDay(int countBusineseDay) {
        this.countBusineseDay = countBusineseDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getStart_date_Day() {
        return Integer.parseInt(start_date.substring(8));
    }

    public int getEnd_date_Day() {
        return Integer.parseInt(end_date.substring(8));
    }

    public boolean isHoliday() {
        return holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }

    public boolean isSchoolDay() {
        return schoolDay;
    }

    public void setSchoolDay(boolean schoolDay) {
        this.schoolDay = schoolDay;
    }

    public int getStart_day_week() {
        return start_day_week;
    }

    public void setStart_day_week(int start_day_week) {
        this.start_day_week = start_day_week;
    }

    public int getEnd_date_week() {
        return end_date_week;
    }

    public void setEnd_date_week(int end_date_week) {
        this.end_date_week = end_date_week;
    }

    public int getCountDate() {
        return countDate;
    }

    public void setCountDate(int countDate) {
        this.countDate = countDate;
    }

    public String getStart_day_Date() {
        String[] start_dateArray = start_date.split("-");
        return start_dateArray[2];
    }

    public String getStart_day_Month() {
        String[] start_dateArray = start_date.split("-");
        return start_dateArray[1];
    }

    public String getStart_day_Year() {
        String[] start_dateArray = start_date.split("-");
        return start_dateArray[0];
    }

    public String getEnd_day_Date() {
        String[] end_dateArray = end_date.split("-");
        return end_dateArray[2];
    }

    public String getEnd_day_Month() {
        String[] end_dateArray = end_date.split("-");
        return end_dateArray[1];
    }

    public String getEnd_day_Year() {
        String[] end_dateArray = end_date.split("-");
        return end_dateArray[0];
    }

}
