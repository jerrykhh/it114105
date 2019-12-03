/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.bean;

/**
 *
 * @author JerryKwok
 */
public class AttendBean {
    private String id;
    private boolean attend;
    private String date;
    private StudentBean stBean;

    public AttendBean() {
        stBean = new StudentBean();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAttend() {
        return attend;
    }

    public void setAttend(boolean attend) {
        this.attend = attend;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public StudentBean getStBean() {
        return stBean;
    }

    public void setStBean(StudentBean stBean) {
        this.stBean = stBean;
    }
    
}
