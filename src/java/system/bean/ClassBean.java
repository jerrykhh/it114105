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
public class ClassBean {

    private String id;
    private String className;
    private TeacherBean teacherBean;

    public ClassBean() {
        teacherBean = new TeacherBean();
    }

    public TeacherBean getTeacherBean() {
        return teacherBean;
    }

    public void setTeacherBean(TeacherBean teacherBean) {
        this.teacherBean = teacherBean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
