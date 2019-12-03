/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.bean;

import java.util.ArrayList;

/**
 *
 * @author JerryKwok
 */
public class SemesterBean {
    private String year;
    private int term;
    private String start_date;
    private String end_date;
    private int countBusineseDate;

    public int getCountBusineseDate() {
        return countBusineseDate;
    }

    public void setCountBusineseDate(int countBusineseDate) {
        this.countBusineseDate = countBusineseDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
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


    
    
    
}
