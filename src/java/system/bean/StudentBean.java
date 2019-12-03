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
public class StudentBean {

    private String id;
    private String fname;
    private String lname;
    private String gender;
    private String email;
    private String birthday;
    private int attendDay;
    private double attendRate;
    private ArrayList<AttendBean> attendList;

    public int getAttendDay() {
        return attendDay;
    }

    public void setAttendDay(int attendDay) {
        this.attendDay = attendDay;
    }

    public double getAttendRate() {
        return attendRate;
    }

    public void setAttendRate(double attendRate) {
        this.attendRate = attendRate;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return fname + " " + lname;
    }

    public ArrayList<AttendBean> getAttendList() {
        return attendList;
    }

    public void setAttendList(ArrayList<AttendBean> attendList) {
        this.attendList = attendList;
    }

    
}
