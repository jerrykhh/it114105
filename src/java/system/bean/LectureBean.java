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
public class LectureBean {

    private String id;
    private String lecture;
    private String description;
    private String className;
    private LectureTimeBean time;
    private LectureDayBean day;
    private String room;
    private String teacherId;

    public LectureBean() {
        time = new LectureTimeBean();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LectureTimeBean getTime() {
        return time;
    }

    public void setTime(LectureTimeBean time) {
        this.time = time;
    }

    public LectureDayBean getDay() {
        return day;
    }

    public void setDay(LectureDayBean day) {
        this.day = day;
    }


    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

}
