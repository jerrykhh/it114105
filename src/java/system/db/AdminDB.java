/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import system.bean.ClassBean;
import system.bean.StudentBean;
import system.bean.TeacherBean;

/**
 *
 * @author JerryKwok
 */
public class AdminDB {

    private String url;
    private String username;
    private String password;
    private String adminId;

    public AdminDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return DriverManager.getConnection(url, username, password);
    }

    public int getCountClass() {
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT COUNT(*) FROM lecture";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            ResultSet result = pStmnt.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getCountStudent() {
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT COUNT(studentId) FROM studentClass";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            ResultSet result = pStmnt.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public ArrayList<StudentBean> getAllStudentClass() {
        ArrayList<StudentBean> list = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT *, studentclass.*, studentclass.studentClassId as studendClassId, teacher.fname as Tfname, teacher.lname as Tlname, teacher.gender as Tgender, teacher.title as Ttitle, teacher.id as Tid FROM studentclass, class, teacher, student WHERE studentclass.classId = class.id AND class.teacherId = teacher.id AND studentclass.studentId = student.id ORDER BY studentClass.studentId ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<StudentBean>();
            while (result.next()) {
                StudentBean stBean = new StudentBean();
                System.out.println("asdasddassddsa");
                stBean.setId(result.getString("studentId"));
                stBean.setFname(result.getString("fname"));
                stBean.setLname(result.getString("lname"));
                stBean.setGender(result.getString("gender"));
                stBean.setStudentClassid(result.getString("studentClassId"));
                ClassBean clBean = new ClassBean();
                clBean.setClassName(result.getString("class"));
                TeacherBean teBean = new TeacherBean();
                teBean.setFname(result.getString("Tfname"));
                teBean.setLname(result.getString("Tlname"));
                teBean.setTitle(result.getString("Ttitle"));
                teBean.setId(result.getString("Tid"));
                teBean.setGender(result.getString("Tgender"));
                clBean.setTeacherBean(teBean);
                stBean.setClassName(clBean);
                System.out.println(stBean.getFname());
                list.add(stBean);
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void deleteStudentClass(String studentClassId) {
        try {
            Connection connt = getConnection();
            String preQuery = "DELETE FROM studentclass WHERE studentClassId = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, studentClassId);
            pStmnt.executeUpdate();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public StudentBean getStudentClassDeital(String studentClassId) {
        StudentBean studentBean = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT student.* FROM studentclass, student WHERE student.id = studentclass.studentId AND studentclass.studentClassId = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, studentClassId);
            ResultSet result = pStmnt.executeQuery();
            if (result.next()) {
                studentBean = new StudentBean();
                studentBean.setFname(result.getString("fname"));
                studentBean.setLname(result.getString("lname"));
                studentBean.setGender(result.getString("gender"));
                studentBean.setEmail(result.getString("email"));
                studentBean.setBirthday(result.getString("birthday"));
                studentBean.setId(result.getString("id"));
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return studentBean;
    }
    
    public ArrayList<ClassBean> getAllClass() {
        ArrayList<ClassBean> classList = new ArrayList<ClassBean>();
        try {
            Connection connt = getConnection();
            String sql = "SELECT class.*, teacher.fname, teacher.lname, teacher.email, teacher.gender FROM class, teacher WHERE class.teacherId = teacher.id";
            Statement stmnt = connt.createStatement();
            ResultSet result = stmnt.executeQuery(sql);
            while (result.next()) {
                ClassBean bean = new ClassBean();
                bean.getTeacherBean().setId(result.getString("id"));
                bean.setClassName(result.getString("class"));
                bean.getTeacherBean().setEmail(result.getString("email"));
                bean.getTeacherBean().setFname(result.getString("fname"));
                bean.getTeacherBean().setLname(result.getString("lname"));
                bean.getTeacherBean().setGender(result.getString("gender"));
                classList.add(bean);
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return classList;
    }

}
