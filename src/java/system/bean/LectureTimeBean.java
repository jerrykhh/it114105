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
public class LectureTimeBean {
    private String id;
    private String startTime;
    private String endTime;
    private boolean fullweek;

    public boolean isFullweek() {
        return fullweek;
    }

    public void setFullweek(boolean fullweek) {
        this.fullweek = fullweek;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
}
