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
import java.time.Year;
import java.util.ArrayList;
import system.bean.AdminBean;
import system.bean.ClassBean;
import system.bean.ClassYearBean;
import system.bean.LectureBean;
import system.bean.LectureDayBean;
import system.bean.LectureTimeBean;
import system.bean.SchedulerBean;
import system.bean.SearchBean;
import system.bean.SemesterBean;
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

    public void insertSearchHistory(String searchVal, String page, String username) {
        try {
            String classId;
            Connection connt = getConnection();
            String preQuery = "INSERT INTO search(keyword, searchPage, staffId) VALUES(?, ?, ?)";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preQuery);
            preUpdStmnt.setString(1, searchVal);
            preUpdStmnt.setString(2, page);
            preUpdStmnt.setString(3, username);
            preUpdStmnt.executeUpdate();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<SearchBean> getSearchHistory(String searchPage, String username) {
        ArrayList<SearchBean> list = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT COUNT(*) AS COUNT, search.* FROM search WHERE searchPage = ? AND staffId = ? GROUP BY keyword ORDER BY COUNT DESC LIMIT 3";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, searchPage);
            pStmnt.setString(2, username);
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<SearchBean>();
            while (result.next()) {
                SearchBean seBean = new SearchBean();
                seBean.setId(result.getString("id"));
                seBean.setKeyword(result.getString("keyword"));
                seBean.setSearchPage(result.getInt("searchPage"));
                seBean.setStaffId(result.getString("staffId"));
                list.add(seBean);
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

    public ArrayList<StudentBean> getAllStudentClass() {
        ArrayList<StudentBean> list = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT *, student.fname AS Sfname, student.lname AS Slname, student.gender AS Sgender, studentclass.*, studentclass.studentClassId as studendClassId, teacher.fname as Tfname, teacher.lname as Tlname, teacher.gender as Tgender, teacher.title as Ttitle, teacher.id as Tid FROM studentclass, class, teacher, student WHERE studentclass.classId = class.id AND class.teacherId = teacher.id AND studentclass.studentId = student.id AND teacher.title = 1 ORDER BY studentClass.studentId ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<StudentBean>();
            while (result.next()) {
                StudentBean stBean = new StudentBean();
                stBean.setId(result.getString("studentId"));
                stBean.setFname(result.getString("Sfname"));
                stBean.setLname(result.getString("Slname"));
                stBean.setGender(result.getString("Sgender"));
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

    public String deleteStudentClass(String studentClassId) {
        String studentId = null;
        try {
            Connection connt = getConnection();
            String seleQuery = "SELECT studentId FROM studentclass WHERE studentClassId = ?";
            PreparedStatement seleStmnt = connt.prepareStatement(seleQuery);
            seleStmnt.setString(1, studentClassId);
            ResultSet result = seleStmnt.executeQuery();
            if (result.next()) {
                studentId = result.getString("studentId");
            }
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
        return studentId;
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
                bean.getTeacherBean().setId(result.getString("teacherId"));
                bean.setClassName(result.getString("class"));
                bean.setId(result.getString("id"));
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

    public void updateStudentClass(String studentClassId, String className) {
        try {
            String classId;
            Connection connt = getConnection();
            String preQuery = "SELECT class.id FROM class WHERE class.class = ? ";
            PreparedStatement preStmnt = connt.prepareStatement(preQuery);
            preStmnt.setString(1, className);
            ResultSet result = preStmnt.executeQuery();
            if (result.next()) {
                classId = result.getString("id");
                String preUpdQuery = "UPDATE studentClass SET studentClass.classId = ? WHERE studentClassId = ?";
                PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
                preUpdStmnt.setString(1, classId);
                preUpdStmnt.setString(2, studentClassId);
                preUpdStmnt.executeUpdate();
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<StudentBean> searchStudentClass(String searchInput) {
        ArrayList<StudentBean> list = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM (SELECT student.*, class.class , student.fname AS Sfname, student.lname AS Slname, student.gender AS Sgender, studentclass.*, studentclass.studentClassId as studendClassId, teacher.fname as Tfname, teacher.lname as Tlname, teacher.gender as Tgender, teacher.title as Ttitle, teacher.id as Tid FROM studentclass, class, teacher, student WHERE studentclass.classId = class.id AND class.teacherId = teacher.id AND studentclass.studentId = student.id ORDER BY studentClass.studentId ASC) AS studentclassTotal WHERE UPPER(studentclassTotal.studentId) LIKE UPPER(?) OR UPPER(studentclassTotal.Tfname) LIKE UPPER(?) OR  UPPER(studentclassTotal.Slname) LIKE UPPER(?) OR UPPER(studentclassTotal.Sfname) LIKE UPPER(?) OR UPPER(studentclassTotal.Tlname) LIKE UPPER(?) OR UPPER(studentclassTotal.class) LIKE UPPER(?)";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, "%" + searchInput + "%");
            pStmnt.setString(2, "%" + searchInput + "%");
            pStmnt.setString(3, "%" + searchInput + "%");
            pStmnt.setString(4, "%" + searchInput + "%");
            pStmnt.setString(5, "%" + searchInput + "%");
            pStmnt.setString(6, "%" + searchInput + "%");
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<StudentBean>();
            while (result.next()) {
                StudentBean stBean = new StudentBean();
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

    public ArrayList<StudentBean> getUnRegAllStudent() {
        ArrayList<StudentBean> list = null;
        StudentBean stdBean = null;
        try {
            Connection connt = getConnection();
            String preGetRegQuery = "SELECT * FROM student";
            PreparedStatement perGetRegStmnt = connt.prepareStatement(preGetRegQuery);
            ResultSet GetRegresult = perGetRegStmnt.executeQuery();
            list = new ArrayList<StudentBean>();
            while (GetRegresult.next()) {
                StudentBean GetRegstBean = new StudentBean();
                GetRegstBean.setId(GetRegresult.getString("id"));
                GetRegstBean.setFname(GetRegresult.getString("fname"));
                GetRegstBean.setLname(GetRegresult.getString("lname"));
                GetRegstBean.setGender(GetRegresult.getString("gender"));
                GetRegstBean.setBirthday(GetRegresult.getString("birthday"));
                list.add(GetRegstBean);
            }
            String preQuery = "SELECT *, Student.birthday AS Sbirthday ,student.fname AS Sfname, student.lname AS Slname, student.gender AS Sgender, studentclass.*, studentclass.studentClassId as studendClassId, teacher.fname as Tfname, teacher.lname as Tlname, teacher.gender as Tgender, teacher.title as Ttitle, teacher.id as Tid FROM studentclass, class, teacher, student WHERE studentclass.classId = class.id AND class.teacherId = teacher.id AND studentclass.studentId = student.id ORDER BY studentClass.studentId ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            ResultSet result = pStmnt.executeQuery();
            while (result.next()) {
                StudentBean stBean = new StudentBean();
                stBean.setId(result.getString("studentId"));
                stBean.setFname(result.getString("Sfname"));
                stBean.setLname(result.getString("Slname"));
                stBean.setGender(result.getString("Sgender"));
                stBean.setBirthday(result.getString("Sbirthday"));
                for (StudentBean bean : list) {
                    if (bean.getId().equals(stBean.getId())) {
                        stdBean = bean;
                    }
                }
                if (list.size() > 0) {
                    list.remove(stdBean);
                }
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

    public void insertStudentClass(String studentId, String className) {
        try {
            String classId;
            Connection connt = getConnection();
            String preQuery = "SELECT class.id AS id FROM class WHERE class.class = ? ";
            PreparedStatement preStmnt = connt.prepareStatement(preQuery);
            preStmnt.setString(1, className);
            ResultSet result = preStmnt.executeQuery();
            if (result.next()) {
                classId = result.getString("id");
                String preUpdQuery = "INSERT INTO studentClass(classId, studentId) VALUES(?, ?)";
                PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
                preUpdStmnt.setString(1, classId);
                preUpdStmnt.setString(2, studentId);
                preUpdStmnt.executeUpdate();
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<StudentBean> getStudent() {
        ArrayList<StudentBean> list = null;
        StudentBean studentBean;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM student";
            Statement pStmnt = connt.createStatement();
            ResultSet result = pStmnt.executeQuery(preQuery);
            list = new ArrayList<StudentBean>();
            while (result.next()) {
                studentBean = new StudentBean();
                studentBean.setFname(result.getString("fname"));
                studentBean.setLname(result.getString("lname"));
                studentBean.setGender(result.getString("gender"));
                studentBean.setEmail(result.getString("email"));
                studentBean.setBirthday(result.getString("birthday"));
                studentBean.setId(result.getString("id"));
                list.add(studentBean);
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

    public ArrayList<TeacherBean> getTeacher() {
        ArrayList<TeacherBean> list = null;
        TeacherBean teacherBean;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM teacher WHERE title = 1 ORDER BY fname ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<TeacherBean>();
            while (result.next()) {
                teacherBean = new TeacherBean();
                teacherBean.setFname(result.getString("fname"));
                teacherBean.setLname(result.getString("lname"));
                teacherBean.setGender(result.getString("gender"));
                teacherBean.setEmail(result.getString("email"));
                teacherBean.setBirthday(result.getString("birthday"));
                teacherBean.setId(result.getString("id"));
                list.add(teacherBean);
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

    public void updateTeacherToAdmin(String teacherId) {
        try {
            Connection connt = getConnection();
            String preUpdQuery = "UPDATE teacher SET title = 2 WHERE id = ?";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
            preUpdStmnt.setString(1, teacherId);
            preUpdStmnt.executeUpdate();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateAdminToTeacher(String adminId) {
        try {
            Connection connt = getConnection();
            String preUpdQuery = "UPDATE teacher SET title = 1 WHERE id = ?";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
            preUpdStmnt.setString(1, adminId);
            preUpdStmnt.executeUpdate();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<AdminBean> getAdmin() {
        ArrayList<AdminBean> list = null;
        AdminBean adminBean;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM teacher WHERE title = 2 ORDER BY id ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<AdminBean>();
            while (result.next()) {
                adminBean = new AdminBean();
                adminBean.setFname(result.getString("fname"));
                adminBean.setLname(result.getString("lname"));
                adminBean.setGender(result.getString("gender"));
                adminBean.setEmail(result.getString("email"));
                adminBean.setBirthday(result.getString("birthday"));
                adminBean.setId(result.getString("id"));
                list.add(adminBean);
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

    public void deleteStudent(String studentId) {
        try {
            Connection connt = getConnection();
            String preQuery = "DELETE FROM student WHERE id = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, studentId);
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

    public StudentBean getStudentDetials(String studentId) {
        StudentBean studentBean = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM student WHERE id = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, studentId);
            System.out.println("asd" + studentId);
            ResultSet result = pStmnt.executeQuery();
            if (result.next()) {
                studentBean = new StudentBean();
                System.out.println("dasdfgdfg");
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

    public ArrayList<StudentBean> searchStudent(String searchVal) {
        ArrayList<StudentBean> list = null;
        StudentBean studentBean;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM student WHERE id LIKE UPPER(?) OR UPPER(fname) LIKE UPPER(?) OR UPPER(lname) LIKE UPPER(?) OR birthday LIKE ? OR UPPER(email) LIKE UPPER(?) ORDER BY id ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, "%" + searchVal + "%");
            pStmnt.setString(2, "%" + searchVal + "%");
            pStmnt.setString(3, "%" + searchVal + "%");
            pStmnt.setString(4, "%" + searchVal + "%");
            pStmnt.setString(5, "%" + searchVal + "%");
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<StudentBean>();
            while (result.next()) {
                studentBean = new StudentBean();
                studentBean.setFname(result.getString("fname"));
                studentBean.setLname(result.getString("lname"));
                studentBean.setGender(result.getString("gender"));
                studentBean.setEmail(result.getString("email"));
                studentBean.setBirthday(result.getString("birthday"));
                studentBean.setId(result.getString("id"));
                list.add(studentBean);
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

    public String insertStudent(String fname, String lname, String gender, String birthday, String password) {
        String insertId = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT MAX(id) as insertId FROM student ";
            PreparedStatement preStmnt = connt.prepareStatement(preQuery);
            ResultSet result = preStmnt.executeQuery();
            if (result.next()) {
                insertId = (Integer.parseInt(result.getString("insertId")) + 1) + "";
                String preUpdQuery = "INSERT INTO student VALUES(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
                preUpdStmnt.setString(1, insertId);
                preUpdStmnt.setString(2, fname);
                preUpdStmnt.setString(3, lname);
                preUpdStmnt.setString(4, gender);
                preUpdStmnt.setString(5, insertId + "@stu.vtc.edu.hk");
                preUpdStmnt.setString(6, password);
                preUpdStmnt.setString(7, birthday);
                preUpdStmnt.executeUpdate();
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return insertId;
    }

    public void updateStudent(String id, String fname, String lname, String gender, String birthday, String pwd) {
        try {
            Connection connt = getConnection();
            String preUpdQuery = "UPDATE student SET fname = ?, lname = ?, gender = ?, birthday = ?, password = ?  WHERE id = ?";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
            preUpdStmnt.setString(1, fname);
            preUpdStmnt.setString(2, lname);
            preUpdStmnt.setString(3, gender);
            preUpdStmnt.setString(4, birthday);
            preUpdStmnt.setString(5, pwd);
            preUpdStmnt.setString(6, id);

            preUpdStmnt.executeUpdate();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateStudent(String id, String fname, String lname, String gender, String birthday) {
        try {
            Connection connt = getConnection();
            String preUpdQuery = "UPDATE student SET fname = ?, lname = ?, gender = ?, birthday = ?  WHERE id = ?";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
            preUpdStmnt.setString(1, fname);
            preUpdStmnt.setString(2, lname);
            preUpdStmnt.setString(3, gender);
            preUpdStmnt.setString(4, birthday);
            preUpdStmnt.setString(5, id);
            preUpdStmnt.executeUpdate();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void insertTeacher(String id, String fname, String lname, String gender, String birthday, String password) {
        try {
            Connection connt = getConnection();
            String preQuery = "INSERT INTO teacher VALUES(?, ?, ?, ?, ?, ?, ?,1, 1)";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preQuery);
            preUpdStmnt.setString(1, id);
            preUpdStmnt.setString(2, fname);
            preUpdStmnt.setString(3, lname);
            preUpdStmnt.setString(4, gender);
            preUpdStmnt.setString(5, id + "@stu.vtc.edu.hk");
            preUpdStmnt.setString(6, password);
            preUpdStmnt.setString(7, birthday);
            preUpdStmnt.executeUpdate();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkTeacherDupliId(String teacherId) {
        boolean isDup = true;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT id FROM teacher WHERE id = ? ";
            PreparedStatement stmnt = connt.prepareStatement(preQuery);
            stmnt.setString(1, teacherId);
            ResultSet result = stmnt.executeQuery();
            int count = 0;
            while (result.next()) {
                count++;
            }
            if (count == 0) {
                isDup = false;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isDup;
    }

    public ArrayList<TeacherBean> searchTeacher(String searchVal) {
        ArrayList<TeacherBean> list = null;
        TeacherBean teacherBean;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM teacher WHERE title = 1 AND id LIKE UPPER(?) OR UPPER(fname) LIKE UPPER(?) OR UPPER(lname) LIKE UPPER(?) OR birthday LIKE ? OR UPPER(email) LIKE UPPER(?) ORDER BY id ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, "%" + searchVal + "%");
            pStmnt.setString(2, "%" + searchVal + "%");
            pStmnt.setString(3, "%" + searchVal + "%");
            pStmnt.setString(4, "%" + searchVal + "%");
            pStmnt.setString(5, "%" + searchVal + "%");
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<TeacherBean>();
            while (result.next()) {
                teacherBean = new TeacherBean();
                teacherBean.setFname(result.getString("fname"));
                teacherBean.setLname(result.getString("lname"));
                teacherBean.setGender(result.getString("gender"));
                teacherBean.setEmail(result.getString("email"));
                teacherBean.setBirthday(result.getString("birthday"));
                teacherBean.setId(result.getString("id"));
                list.add(teacherBean);
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

    public void updateTeacher(String teacherId, String password, String fname, String lname, String gender, String birthday) {
        try {
            Connection connt = getConnection();
            String preUpdQuery = "UPDATE teacher SET fname = ?, lname = ?, gender = ?, birthday = ?, password = ?  WHERE id = ?";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
            preUpdStmnt.setString(1, fname);
            preUpdStmnt.setString(2, lname);
            preUpdStmnt.setString(3, gender);
            preUpdStmnt.setString(4, birthday);
            preUpdStmnt.setString(5, password);
            preUpdStmnt.setString(6, teacherId);
            preUpdStmnt.executeUpdate();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateTeacher(String teacherId, String fname, String lname, String gender, String birthday) {
        try {
            Connection connt = getConnection();
            String preUpdQuery = "UPDATE teacher SET fname = ?, lname = ?, gender = ?, birthday = ? WHERE id = ?";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
            preUpdStmnt.setString(1, fname);
            preUpdStmnt.setString(2, lname);
            preUpdStmnt.setString(3, gender);
            preUpdStmnt.setString(4, birthday);
            preUpdStmnt.setString(5, teacherId);
            preUpdStmnt.executeUpdate();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public TeacherBean getTeacherDetials(String teacherId) {
        TeacherBean teacherBean = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM teacher WHERE id = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, teacherId);
            ResultSet result = pStmnt.executeQuery();
            if (result.next()) {
                teacherBean = new TeacherBean();
                teacherBean.setFname(result.getString("fname"));
                teacherBean.setLname(result.getString("lname"));
                teacherBean.setGender(result.getString("gender"));
                teacherBean.setEmail(result.getString("email"));
                teacherBean.setBirthday(result.getString("birthday"));
                teacherBean.setId(result.getString("id"));
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return teacherBean;
    }

    public void deleteTeacher(String teacherId) {
        try {
            Connection connt = getConnection();
            String delForSql = "SET FOREIGN_KEY_CHECKS=0";
            Statement st = connt.createStatement();
            st.executeUpdate(delForSql);
            String preQuery = "DELETE FROM teacher WHERE id = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, teacherId);
            pStmnt.executeUpdate();
            String conForSql = "SET FOREIGN_KEY_CHECKS=1";
            Statement tm = connt.createStatement();
            tm.executeUpdate(url);
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<AdminBean> searchAdmin(String searchVal) {
        ArrayList<AdminBean> list = null;
        AdminBean adminBean;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM Teacher WHERE title = 2 AND id LIKE UPPER(?) OR UPPER(fname) LIKE UPPER(?) OR UPPER(lname) LIKE UPPER(?) OR birthday LIKE ? OR UPPER(email) LIKE UPPER(?) ORDER BY id ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, "%" + searchVal + "%");
            pStmnt.setString(2, "%" + searchVal + "%");
            pStmnt.setString(3, "%" + searchVal + "%");
            pStmnt.setString(4, "%" + searchVal + "%");
            pStmnt.setString(5, "%" + searchVal + "%");
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<AdminBean>();
            while (result.next()) {
                adminBean = new AdminBean();
                adminBean.setFname(result.getString("fname"));
                adminBean.setLname(result.getString("lname"));
                adminBean.setGender(result.getString("gender"));
                adminBean.setEmail(result.getString("email"));
                adminBean.setBirthday(result.getString("birthday"));
                adminBean.setId(result.getString("id"));
                list.add(adminBean);
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

    public AdminBean getAdminDetials(String adminId) {
        AdminBean adminBean = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM teacher WHERE id = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, adminId);
            ResultSet result = pStmnt.executeQuery();
            if (result.next()) {
                adminBean = new AdminBean();
                adminBean.setFname(result.getString("fname"));
                adminBean.setLname(result.getString("lname"));
                adminBean.setGender(result.getString("gender"));
                adminBean.setEmail(result.getString("email"));
                adminBean.setBirthday(result.getString("birthday"));
                adminBean.setId(result.getString("id"));
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return adminBean;
    }

    public void updateAdmin(String adminId, String password, String fname, String lname, String gender, String birthday) {
        try {
            Connection connt = getConnection();
            String preUpdQuery = "UPDATE teacher SET fname = ?, lname = ?, gender = ?, birthday = ?, password = ?  WHERE id = ?";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
            preUpdStmnt.setString(1, fname);
            preUpdStmnt.setString(2, lname);
            preUpdStmnt.setString(3, gender);
            preUpdStmnt.setString(4, birthday);
            preUpdStmnt.setString(5, password);
            preUpdStmnt.setString(6, adminId);
            preUpdStmnt.executeUpdate();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateAdmin(String adminId, String fname, String lname, String gender, String birthday) {
        try {
            Connection connt = getConnection();
            String preUpdQuery = "UPDATE teacher SET fname = ?, lname = ?, gender = ?, birthday = ? WHERE id = ?";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
            preUpdStmnt.setString(1, fname);
            preUpdStmnt.setString(2, lname);
            preUpdStmnt.setString(3, gender);
            preUpdStmnt.setString(4, birthday);
            preUpdStmnt.setString(5, adminId);
            preUpdStmnt.executeUpdate();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void insertAdmin(String adminId, String fname, String lname, String gender, String birthday, String password) {
        try {
            Connection connt = getConnection();
            String preQuery = "INSERT INTO teacher VALUES(?, ?, ?, ?, ?, ?, ?, 2, 0)";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preQuery);
            preUpdStmnt.setString(1, adminId);
            preUpdStmnt.setString(2, fname);
            preUpdStmnt.setString(3, lname);
            preUpdStmnt.setString(4, gender);
            preUpdStmnt.setString(5, adminId + "@stu.vtc.edu.hk");
            preUpdStmnt.setString(6, password);
            preUpdStmnt.setString(7, birthday);
            preUpdStmnt.executeUpdate();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkAdminDupliId(String adminId) {
        boolean isDup = true;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT id FROM teacher WHERE id = ?";
            PreparedStatement stmnt = connt.prepareStatement(preQuery);
            stmnt.setString(1, adminId);
            ResultSet result = stmnt.executeQuery();
            int count = 0;
            while (result.next()) {
                count++;
            }
            if (count == 0) {
                isDup = false;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isDup;
    }

    public void deleteAdmin(String adminId) {
        try {
            Connection connt = getConnection();
            String preQuery = "DELETE FROM teacher WHERE id = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, adminId);
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

    
    public ArrayList<LectureBean> getLecture(String className) {
        ArrayList<LectureBean> list = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM lecture, timetable, daytable, class WHERE class.id = ? AND lecture.classId = class.id AND lecture.timeId = timetable.timeId AND lecture.dayId = daytable.id";
            PreparedStatement preStmnt = connt.prepareStatement(preQuery);
            preStmnt.setString(1, className);
            System.out.println(className);
            ResultSet result = preStmnt.executeQuery();
            list = new ArrayList<LectureBean>();
            while (result.next()) {
                LectureBean lectureBean = new LectureBean();
                LectureTimeBean timeBean = new LectureTimeBean();
                LectureDayBean dayBean = new LectureDayBean();
                lectureBean.setLecture(result.getString("lecture"));
                lectureBean.setDescription(result.getString("description"));
                lectureBean.setClassName(result.getString("class"));
                timeBean.setStartTime(result.getString("starttime"));
                timeBean.setEndTime(result.getString("endtime"));
                timeBean.setFullweek(result.getBoolean("fullday"));
                lectureBean.setTime(timeBean);
                dayBean.setDay(result.getString("day"));
                lectureBean.setDay(dayBean);
                lectureBean.setRoom(result.getString("room"));
                list.add(lectureBean);
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

    public ArrayList<LectureBean> getLecture() {
        ArrayList<LectureBean> list = null;
        LectureBean lectureBean;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT class.id AS classId, class.class, timetable.timeId AS timeId, timetable.starttime, timetable.endtime,lecture.id AS lectureId, lecture.lecture,lecture.description, daytable.id AS dayId, daytable.day AS day, timetable.fullday FROM lecture, class, timetable, daytable WHERE lecture.classId = class.id AND lecture.dayId = daytable.id AND lecture.timeId = timetable.timeId ORDER BY lecture.id DESC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<LectureBean>();
            while (result.next()) {
                lectureBean = new LectureBean();
                lectureBean.setId(result.getString("lectureId"));
                lectureBean.setLecture(result.getString("lecture"));
                lectureBean.setDescription(result.getString("description"));
                LectureDayBean dayBean = new LectureDayBean();
                dayBean.setDay(result.getString("day"));
                dayBean.setId(result.getString("dayId"));
                LectureTimeBean timeBean = new LectureTimeBean();
                timeBean.setId(result.getString("timeId"));
                timeBean.setStartTime(result.getString("starttime"));
                timeBean.setEndTime(result.getString("endtime"));
                timeBean.setFullweek(result.getBoolean("fullday"));
                lectureBean.setClassName(result.getString("class"));
                lectureBean.setTime(timeBean);
                lectureBean.setDay(dayBean);
                list.add(lectureBean);
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

    public void deleteLecture(String lectureId) {
        try {
            Connection connt = getConnection();
            String preQuery = "DELETE FROM lecture WHERE id = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, lectureId);
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

    public ArrayList<LectureBean> searchLecture(String searchVal) {

        ArrayList<LectureBean> list = null;
        LectureBean lectureBean;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM (SELECT class.id AS classId, class.class, timetable.timeId AS timeId, timetable.starttime, timetable.endtime,lecture.id AS lectureId, lecture.lecture,lecture.description, daytable.id AS dayId, daytable.day AS day, timetable.fullday FROM lecture, class, timetable, daytable WHERE lecture.classId = class.id AND lecture.dayId = daytable.id AND lecture.timeId = timetable.timeId ORDER BY lecture.id DESC) AS result WHERE UPPER(result.lecture) LIKE UPPER(?) OR  UPPER(result.class) LIKE UPPER(?) OR UPPER(result.description) LIKE UPPER(?) OR UPPER(result.day) LIKE UPPER(?) OR result.starttime LIKE  ? OR result.endtime LIKE ? ";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, "%" + searchVal + "%");
            pStmnt.setString(2, "%" + searchVal + "%");
            pStmnt.setString(3, "%" + searchVal + "%");
            pStmnt.setString(4, "%" + searchVal + "%");
            pStmnt.setString(5, "%" + searchVal + "%");
            pStmnt.setString(6, "%" + searchVal + "%");
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<LectureBean>();
            while (result.next()) {
                lectureBean = new LectureBean();
                lectureBean.setId(result.getString("lectureId"));
                lectureBean.setLecture(result.getString("lecture"));
                lectureBean.setDescription(result.getString("description"));
                LectureDayBean dayBean = new LectureDayBean();
                dayBean.setDay(result.getString("day"));
                dayBean.setId(result.getString("dayId"));
                LectureTimeBean timeBean = new LectureTimeBean();
                timeBean.setId(result.getString("timeId"));
                timeBean.setStartTime(result.getString("starttime"));
                timeBean.setEndTime(result.getString("endtime"));
                timeBean.setFullweek(result.getBoolean("fullday"));
                lectureBean.setClassName(result.getString("class"));
                lectureBean.setTime(timeBean);
                lectureBean.setDay(dayBean);
                list.add(lectureBean);
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

    public void insertLecture(String lecture, String description, String classId, String timeId, String day, String teacher) {
        try {
            Connection connt = getConnection();
            String preQuery = "INSERT INTO lecture(lecture, description, classId, timeId, dayId,teacherId) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preQuery);
            preUpdStmnt.setString(1, lecture);
            preUpdStmnt.setString(2, description);
            preUpdStmnt.setString(3, classId);
            preUpdStmnt.setString(4, timeId);
            preUpdStmnt.setString(5, day);
            preUpdStmnt.setString(6, teacher);
            preUpdStmnt.executeUpdate();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public LectureBean getLectureDetials(String lectureId) {
        LectureBean lectureBean = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT class.id AS classId, class.class, timetable.timeId AS timeId, timetable.starttime, timetable.endtime,lecture.id AS lectureId, lecture.lecture,lecture.description, daytable.id AS dayId, daytable.day AS day, timetable.endtime, timetable.fullday, lecture.teacherId AS teacherId FROM lecture, class, timetable, daytable WHERE lecture.classId = class.id AND lecture.dayId = daytable.id AND lecture.timeId = timetable.timeId AND lecture.id= ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, lectureId);
            ResultSet result = pStmnt.executeQuery();
            if (result.next()) {
                lectureBean = new LectureBean();
                lectureBean.setId(result.getString("lectureId"));
                lectureBean.setLecture(result.getString("lecture"));
                lectureBean.setDescription(result.getString("description"));
                lectureBean.setTeacherId(result.getString("teacherId"));
                LectureDayBean dayBean = new LectureDayBean();
                dayBean.setDay(result.getString("day"));
                dayBean.setId(result.getString("dayId"));
                LectureTimeBean timeBean = new LectureTimeBean();
                timeBean.setId(result.getString("timeId"));
                timeBean.setStartTime(result.getString("starttime"));
                timeBean.setEndTime(result.getString("endtime"));
                timeBean.setFullweek(result.getBoolean("fullday"));
                lectureBean.setClassName(result.getString("class"));
                lectureBean.setTime(timeBean);
                lectureBean.setDay(dayBean);
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lectureBean;
    }

    public ArrayList<LectureTimeBean> getTimeList() {
        ArrayList<LectureTimeBean> list = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT * FROM timetable ORDER BY starttime ASC";
            Statement stmnt = connt.createStatement();
            ResultSet result = stmnt.executeQuery(sql);
            list = new ArrayList<LectureTimeBean>();
            while (result.next()) {
                LectureTimeBean ltbean = new LectureTimeBean();
                ltbean.setId(result.getString("timeId"));
                ltbean.setStartTime(result.getString("starttime"));
                ltbean.setEndTime(result.getString("endtime"));
                ltbean.setFullweek(result.getBoolean("fullday"));
                list.add(ltbean);
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

    public ArrayList<SemesterBean> getNowSemester() {
        ArrayList<SemesterBean> list = null;
        try {
            Connection connt = getConnection();
            String query = "SELECT * FROM semester WHERE start_date<=CURRENT_DATE AND CURRENT_DATE<=end_date";
            Statement stmnt = connt.createStatement();
            System.out.println("POMT#");
            ResultSet result = stmnt.executeQuery(query);
            list = new ArrayList<SemesterBean>();
            if (result.next()) {
                SemesterBean nowTerm = new SemesterBean();
                nowTerm.setTerm(result.getInt("term"));
                nowTerm.setYear(result.getString("year"));
                nowTerm.setStart_date(result.getString("start_date"));
                nowTerm.setEnd_date(result.getString("end_date"));
                String preQuery = null;
                System.out.println("POMT#2");
                PreparedStatement preStmnt = null;
                ResultSet secResult = null;

                if (nowTerm.getTerm() == 2) {
                    preQuery = "SELECT * FROM semester WHERE year = ? AND term = ?";
                    preStmnt = connt.prepareStatement(preQuery);
                    preStmnt.setString(1, nowTerm.getYear());
                    preStmnt.setString(2, "1");
                    System.out.println("MOTHRT");
                    secResult = preStmnt.executeQuery();
                    if (secResult.next()) {
                        SemesterBean seTerm = new SemesterBean();
                        seTerm.setTerm(secResult.getInt("term"));
                        seTerm.setYear(secResult.getString("year"));
                        seTerm.setStart_date(secResult.getString("start_date"));
                        seTerm.setEnd_date(secResult.getString("end_date"));
                        list.add(seTerm);
                        list.add(nowTerm);
                    }
                } else if (nowTerm.getTerm() == 1) {
                    System.out.println("POMT#3");
                    preQuery = "SELECT * FROM semester WHERE year = ? AND term = ?";
                    preStmnt = connt.prepareStatement(preQuery);
                    preStmnt.setString(1, nowTerm.getYear());
                    preStmnt.setString(2, "2");
                    System.out.println("POMT#7");
                    secResult = preStmnt.executeQuery();
                    if (secResult.next()) {
                        System.out.println("POMT#5");
                        SemesterBean seTerm = new SemesterBean();
                        seTerm.setTerm(secResult.getInt("term"));
                        System.out.println("POMT#1");
                        seTerm.setYear(secResult.getString("year"));
                        System.out.println("POMT#2");
                        seTerm.setStart_date(secResult.getString("start_date"));
                        System.out.println("POMT#3");
                        seTerm.setEnd_date(secResult.getString("end_date"));
                        list.add(nowTerm);
                        list.add(seTerm);
                        System.out.println("POMT#5");
                    }
                }
            } else {
                System.out.println("ERR");
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

    public ArrayList<SchedulerBean> getScheduler() {
        ArrayList<SchedulerBean> list = null;
        System.out.println("TEST1");
        ArrayList<SemesterBean> semList = getNowSemester();
        try {
            System.out.println(semList.size());
            Connection connt = getConnection();
            String sql = "SELECT *, datediff(end_date, start_date) AS countDay FROM scheduler WHERE  ? <= start_date AND end_date <= ? ORDER BY start_date ASC";
            PreparedStatement preStmnt = connt.prepareStatement(sql);
            preStmnt.setString(1, semList.get(0).getStart_date());
            System.out.println(semList.get(1).getEnd_date() + 'a');
            preStmnt.setString(2, semList.get(1).getEnd_date());
            ResultSet result = preStmnt.executeQuery();
            list = new ArrayList<SchedulerBean>();
            while (result.next()) {
                SchedulerBean bean = new SchedulerBean();
                bean.setId(result.getString("id"));
                bean.setTitle(result.getString("title"));
                bean.setStart_date(result.getString("start_date"));
                bean.setEnd_date(result.getString("end_date"));
                bean.setHoliday(result.getBoolean("holiday"));
                bean.setSchoolDay(result.getBoolean("schoolDay"));
                bean.setCountDate(result.getInt("countDay"));
                list.add(bean);
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("return");
        return list;
    }

    public ArrayList<LectureDayBean> getDayList() {
        ArrayList<LectureDayBean> list = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT * FROM daytable ORDER BY day ASC";
            Statement stmnt = connt.createStatement();
            ResultSet result = stmnt.executeQuery(sql);
            list = new ArrayList<LectureDayBean>();
            while (result.next()) {
                LectureDayBean ldbean = new LectureDayBean();
                ldbean.setDay(result.getString("day"));
                ldbean.setId(result.getString("id"));
                list.add(ldbean);
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

    public ArrayList<ClassBean> getClassList() {
        ArrayList<ClassBean> list = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT class.*, classyear.year, teacher.fname, teacher.lname, teacher.gender FROM class, classyear, teacher WHERE class.classyearId = classyear.id AND class.teacherId = teacher.id ORDER BY classyear.year DESC, class ASC";
            Statement stmnt = connt.createStatement();
            ResultSet result = stmnt.executeQuery(sql);
            list = new ArrayList<ClassBean>();
            while (result.next()) {
                ClassBean clbean = new ClassBean();
                clbean.setId(result.getString("id"));
                clbean.setClassName(result.getString("class"));
                clbean.setYear(result.getString("year"));
                TeacherBean tbean = new TeacherBean();
                tbean.setFname(result.getString("fname"));
                tbean.setLname(result.getString("lname"));
                tbean.setGender(result.getString("gender"));
                clbean.setTeacherBean(tbean);
                list.add(clbean);
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

    public boolean checkTeacherDupSchedule(String time, String teacher, String lectureId) {
        boolean isDup = true;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT COUNT(*) AS number FROM lecture WHERE id != ? AND timeId = ? AND teacherId = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, lectureId);
            pStmnt.setString(2, time);
            pStmnt.setString(3, teacher);
            ResultSet result = pStmnt.executeQuery();
            if (result.next()) {
                if (result.getInt("number") < 1) {
                    isDup = false;
                }
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isDup;
    }

    public boolean checkTeacherDupSchedule(String time, String teacher) {
        boolean isDup = true;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT COUNT(*) AS number FROM lecture WHERE timeId = ? AND teacherId = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, time);
            pStmnt.setString(2, teacher);
            ResultSet result = pStmnt.executeQuery();
            if (result.next()) {
                if (result.getInt("number") < 1) {
                    isDup = false;
                }
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isDup;
    }

    public void updateLecture(String lectureId, String lecture, String description, String className, String time, String day, String teacher) {
        try {
            Connection connt = getConnection();
            String preUpdQuery = "UPDATE lecture SET lecture = ?, description = ?, classId = ?, timeId = ?, dayId = ?, teacherId = ?  WHERE id = ?";
            PreparedStatement preUpdStmnt = connt.prepareStatement(preUpdQuery);
            preUpdStmnt.setString(1, lecture);
            preUpdStmnt.setString(2, description);
            preUpdStmnt.setString(3, className);
            preUpdStmnt.setString(4, time);
            preUpdStmnt.setString(5, day);
            if (teacher.length() == 0) {
                preUpdStmnt.setString(6, null);
            } else {
                preUpdStmnt.setString(6, teacher);
            }
            preUpdStmnt.setString(7, lectureId);
            preUpdStmnt.executeUpdate();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<SchedulerBean> getSchedulerAll() {
        ArrayList<SchedulerBean> list = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT * FROM scheduler";
            PreparedStatement preStmnt = connt.prepareStatement(sql);
            ResultSet result = preStmnt.executeQuery();
            list = new ArrayList<SchedulerBean>();
            while (result.next()) {
                SchedulerBean bean = new SchedulerBean();
                bean.setId(result.getString("id"));
                bean.setTitle(result.getString("title"));
                bean.setStart_date(result.getString("start_date"));
                bean.setEnd_date(result.getString("end_date"));
                bean.setHoliday(result.getBoolean("holiday"));
                bean.setSchoolDay(result.getBoolean("schoolDay"));
                list.add(bean);
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("return");
        return list;
    }

    public SchedulerBean getSchedulerDetials(String scheduleId) {
        SchedulerBean bean = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT * FROM scheduler WHERE id = ?";
            PreparedStatement preStmnt = connt.prepareStatement(sql);
            preStmnt.setString(1, scheduleId);
            ResultSet result = preStmnt.executeQuery();
            bean = new SchedulerBean();
            if (result.next()) {
                bean.setId(result.getString("id"));
                bean.setTitle(result.getString("title"));
                bean.setStart_date(result.getString("start_date"));
                bean.setEnd_date(result.getString("end_date"));
                bean.setHoliday(result.getBoolean("holiday"));
                bean.setSchoolDay(result.getBoolean("schoolDay"));
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("return");
        return bean;
    }

    public ArrayList<SchedulerBean> searchScheduler(String searchVal) {
        ArrayList<SchedulerBean> list = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT * FROM scheduler WHERE UPPER(title) LIKE UPPER(?) OR start_date LIKE ? OR end_date LIKE ? ";
            PreparedStatement preStmnt = connt.prepareStatement(sql);
            preStmnt.setString(1, "%" + searchVal + "%");
            preStmnt.setString(2, "%" + searchVal + "%");
            preStmnt.setString(3, "%" + searchVal + "%");
            ResultSet result = preStmnt.executeQuery();
            list = new ArrayList<SchedulerBean>();
            while (result.next()) {
                SchedulerBean bean = new SchedulerBean();
                bean.setId(result.getString("id"));
                bean.setTitle(result.getString("title"));
                bean.setStart_date(result.getString("start_date"));
                bean.setEnd_date(result.getString("end_date"));
                bean.setHoliday(result.getBoolean("holiday"));
                bean.setSchoolDay(result.getBoolean("schoolDay"));
                list.add(bean);
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("return");
        return list;
    }

    public void deleteSchedule(String scheduleId) {
        try {
            Connection connt = getConnection();
            String preQuery = "DELETE FROM scheduler WHERE id = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, scheduleId);
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

    public void insertSchedule(String title, String startDate, String endDate, String isHoliday, String isSchoolDay) {
        try {
            boolean holiday = false, schoolDay = false;
            if (isHoliday != null && isHoliday.equalsIgnoreCase("isHoliday")) {
                holiday = true;
            }
            if (isSchoolDay != null && isSchoolDay.equalsIgnoreCase("isSchoolDay")) {
                schoolDay = true;
            }
            Connection connt = getConnection();
            String preQuery = "INSERT scheduler(title, start_date, end_date, holiday, schoolDay) VALUES(?,?,?,?,?)";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, title);
            pStmnt.setString(2, startDate);
            pStmnt.setString(3, endDate);
            pStmnt.setBoolean(4, holiday);
            pStmnt.setBoolean(5, schoolDay);
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

    public void updateSchedule(String title, String startDate, String endDate, String isHoliday, String isSchoolDay, String id) {
        try {
            boolean holiday = false, schoolDay = false;
            if (isHoliday != null && isHoliday.equalsIgnoreCase("isHoliday")) {
                holiday = true;
            }
            if (isSchoolDay != null && isSchoolDay.equalsIgnoreCase("isSchoolDay")) {
                schoolDay = true;
            }
            Connection connt = getConnection();
            String preQuery = "UPDATE scheduler SET title = ?, start_date = ?, end_date = ?,  holiday = ?, schoolDay =? WHERE id = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, title);
            pStmnt.setString(2, startDate);
            pStmnt.setString(3, endDate);
            pStmnt.setBoolean(4, holiday);
            pStmnt.setBoolean(5, schoolDay);
            pStmnt.setString(6, id);
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

    public ArrayList<TeacherBean> getNotClassTeacher() {
        ArrayList<TeacherBean> list = null;
        TeacherBean teacherBean;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT teacher.* FROM teacher WHERE teacher.id NOT IN (SELECT teacher.id FROM class, teacher WHERE class.teacherId = teacher.id) AND teach = true ORDER BY teacher.fname ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<TeacherBean>();
            while (result.next()) {
                teacherBean = new TeacherBean();
                teacherBean.setFname(result.getString("fname"));
                teacherBean.setLname(result.getString("lname"));
                teacherBean.setGender(result.getString("gender"));
                teacherBean.setEmail(result.getString("email"));
                teacherBean.setBirthday(result.getString("birthday"));
                teacherBean.setId(result.getString("id"));
                list.add(teacherBean);
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

    public ArrayList<ClassYearBean> getClassYear() {
        ArrayList<ClassYearBean> list = null;
        ;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM classyear ORDER BY id ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<ClassYearBean>();
            while (result.next()) {
                ClassYearBean classYearBean = new ClassYearBean();
                classYearBean.setId(result.getString("id"));
                classYearBean.setYear(result.getString("year"));
                list.add(classYearBean);
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

    public boolean checkClassNameDupli(String className) {
        boolean isDupli = false;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM class WHERE class.class = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, className);
            ResultSet result = pStmnt.executeQuery();
            if (result.next()) {
                isDupli = true;
            }

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isDupli;
    }

    public boolean checkClassNameDupli(String className, String classId) {
        boolean isDupli = false;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM class WHERE class.class = ? AND class.id != ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, className);
            pStmnt.setString(2, classId);
            ResultSet result = pStmnt.executeQuery();
            if (result.next()) {
                isDupli = true;
            }

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isDupli;
    }

    public void insertClass(String className, String teacherId, String yearId, int attendanceTargetId) {
        try {
            Connection connt = getConnection();
            System.out.println("TESTOINTinsertClass");
            String preQuery = "INSERT INTO class(class, teacherId, classyearId, attendanceTargetId) VALUES(?,?,?,?)";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, className);
            pStmnt.setString(2, teacherId);
            pStmnt.setString(3, yearId);
            pStmnt.setInt(4, attendanceTargetId);
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

    public ArrayList<ClassBean> searchClass(String searchVal) {
        ArrayList<ClassBean> list = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT * FROM (SELECT class.*, classyear.year, teacher.fname, teacher.lname, teacher.gender FROM class, classyear, teacher WHERE class.classyearId = classyear.id AND class.teacherId = teacher.id) AS result WHERE UPPER(result.class) LIKE UPPER(?) OR UPPER(result.fname) LIKE UPPER(?) OR UPPER(result.lname) LIKE UPPER(?) OR result.year LIKE ? ORDER BY result.year DESC, result.class ASC";
            PreparedStatement stmnt = connt.prepareCall(sql);
            stmnt.setString(1, "%" + searchVal + "%");
            stmnt.setString(2, "%" + searchVal + "%");
            stmnt.setString(3, "%" + searchVal + "%");
            stmnt.setString(4, "%" + searchVal + "%");
            ResultSet result = stmnt.executeQuery();
            list = new ArrayList<ClassBean>();
            while (result.next()) {
                ClassBean clbean = new ClassBean();
                clbean.setId(result.getString("id"));
                clbean.setClassName(result.getString("class"));
                clbean.setYear(result.getString("year"));
                TeacherBean tbean = new TeacherBean();
                tbean.setFname(result.getString("fname"));
                tbean.setLname(result.getString("lname"));
                tbean.setGender(result.getString("gender"));
                clbean.setTeacherBean(tbean);
                list.add(clbean);
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

    public void deleteClass(String classId) {
        try {
            Connection connt = getConnection();
            String preQuery = "DELETE FROM class WHERE id = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, classId);
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

    public ClassBean getClassDetails(String classId) {
        ClassBean clbean = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT class.*, classyear.year, teacher.fname, teacher.lname, teacher.gender FROM class, classyear, teacher WHERE class.classyearId = classyear.id AND class.teacherId = teacher.id AND class.id = ? ORDER BY classyear.year DESC, class ASC";
            PreparedStatement stmnt = connt.prepareStatement(sql);
            stmnt.setString(1, classId);
            ResultSet result = stmnt.executeQuery();
            clbean = new ClassBean();
            if (result.next()) {
                clbean.setId(result.getString("id"));
                clbean.setClassName(result.getString("class"));
                clbean.setYear(result.getString("year"));
                TeacherBean tbean = new TeacherBean();
                tbean.setFname(result.getString("fname"));
                tbean.setLname(result.getString("lname"));
                tbean.setGender(result.getString("gender"));
                tbean.setId(result.getString("teacherId"));
                clbean.setTeacherBean(tbean);
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return clbean;
    }

    public void updateClass(String className, String teacherId, String yearId, int attendanceTargetId, String classId) {
                try {
            Connection connt = getConnection();
            String preQuery = "UPDATE class set class = ?, teacherId = ?, classyearId = ?, attendanceTargetId = ? WHERE id = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
                    System.out.println("DATER" + className);
                     System.out.println("DATER" + teacherId);
                      System.out.println("DATER" + yearId);
                       System.out.println("DATER" + classId);
                      
            pStmnt.setString(1, className);
            pStmnt.setString(2, teacherId);
            pStmnt.setString(3, yearId);
            pStmnt.setInt(4, attendanceTargetId);
            pStmnt.setString(5, classId);
            pStmnt.executeUpdate();
            connt.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
