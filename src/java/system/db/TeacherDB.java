/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.db;

import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import system.bean.AttendBean;
import system.bean.ClassBean;
import system.bean.LectureBean;
import system.bean.LectureDayBean;
import system.bean.LectureTimeBean;
import system.bean.SchedulerBean;
import system.bean.SemesterBean;
import system.bean.StudentBean;

/**
 *
 * @author JerryKwok
 */
public class TeacherDB {

    private String url;
    private String username;
    private String password;
    private String teacherId;

    public TeacherDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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
            String preQuery = "SELECT COUNT(*) FROM lecture WHERE teacherId = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, teacherId);
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
            String preQuery = "SELECT COUNT(DISTINCT studentId) FROM studentClass, class, lecture WHERE studentclass.classId = class.id AND class.id = lecture.classId AND lecture.teacherId = ?";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, teacherId);
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

    public ArrayList<AttendBean> getAttendClass(String className, String date) {
        ArrayList<AttendBean> AttendList = new ArrayList<AttendBean>();
        try {
            Connection connt = getConnection();
            String preSearchQuery = "SELECT DISTINCT student.* FROM studentclass, class, student WHERE studentclass.classId = class.id AND studentclass.studentId = student.id AND class.class = ?";
            PreparedStatement ptment = connt.prepareStatement(preSearchQuery);
            ptment.setString(1, className);
            ResultSet result = ptment.executeQuery();
            while (result.next()) {
                StudentBean studentBean = new StudentBean();
                studentBean.setFname(result.getString("fname"));
                studentBean.setLname(result.getString("lname"));
                studentBean.setGender(result.getString("gender"));
                studentBean.setEmail(result.getString("email"));
                studentBean.setId(result.getString("id"));
                String preAttendQuery = "SELECT * FROM studentattend WHERE attendDate=? AND studentId=?";
                PreparedStatement ptmentAttend = connt.prepareStatement(preAttendQuery);
                ptmentAttend.setString(1, date);
                ptmentAttend.setString(2, studentBean.getId());
                ResultSet resultAttend = ptmentAttend.executeQuery();
                AttendBean attendBean = new AttendBean();
                if (resultAttend.next()) {
                    attendBean.setId(resultAttend.getString("studentAttendanceId"));
                    attendBean.setDate(date);
                    attendBean.setAttend(resultAttend.getBoolean("attend"));
                } else {
                    attendBean.setDate(date);
                    attendBean.setAttend(false);
                }
                attendBean.setStBean(studentBean);
                AttendList.add(attendBean);
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return AttendList;
    }

    public ArrayList<StudentBean> getAllStudentClass(String className) {
        ArrayList<StudentBean> list = new ArrayList<StudentBean>();
        try {
            Connection connt = getConnection();
            //String preQuery = "SELECT * FROM studentattend, studentclass, student, class WHERE studentclass.classId = class.id AND studentclass.studentId = student.id AND class.class = ? AND studentattend.studentId = student.id AND attendDate = ?";
            String preQuery = "SELECT student.* FROM studentclass, class, student WHERE studentclass.classid = class.id AND studentclass.studentid = student.id AND class.class = ? ORDER BY student.fname ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, className);
            //  pStmnt.setString(2, date);
            ResultSet result = pStmnt.executeQuery();
            while (result.next()) {
                StudentBean studentBean = new StudentBean();
                studentBean.setFname(result.getString("fname"));
                studentBean.setLname(result.getString("lname"));
                studentBean.setGender(result.getString("gender"));
                studentBean.setEmail(result.getString("email"));
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

    public void insertStudentAttendance(String studentId, String date, boolean attend) {
        try {
            int count = 0;
            Connection connt = getConnection();
            String preQuery = "SELECT studentAttend.studentId FROM studentattend, studentclass, student, class WHERE studentclass.classId = class.id AND studentclass.studentId = student.id AND studentattend.studentId = ? AND studentattend.studentId = student.id AND attendDate = ? ";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, studentId);
            pStmnt.setString(2, date);
            ResultSet result = pStmnt.executeQuery();
            while (result.next()) {
                count = result.getInt(1);
            }
            if (count >= 1) {
                String preUpdateQuery = "UPDATE studentattend SET Attend = ? WHERE attendDate = ? AND studentId = ? ";
                PreparedStatement pUpdateStmnt = connt.prepareStatement(preUpdateQuery);
                pUpdateStmnt.setBoolean(1, attend);
                pUpdateStmnt.setString(2, date);
                pUpdateStmnt.setString(3, studentId);
                pUpdateStmnt.executeUpdate();
            } else {
                String preUpdateQuery = "INSERT INTO studentattend(studentId, attendDate, attend) VALUES (?, ?, ?)";
                PreparedStatement pUpdateStmnt = connt.prepareStatement(preUpdateQuery);
                pUpdateStmnt.setString(1, studentId);
                pUpdateStmnt.setString(2, date);
                pUpdateStmnt.setBoolean(3, attend);
                pUpdateStmnt.executeUpdate();
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

    public ArrayList<SemesterBean> getLatestReportYear() {
        ArrayList<SemesterBean> list = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT DISTINCT year FROM semester ORDER BY year DESC LIMIT 3";
            Statement stmnt = connt.createStatement();
            ResultSet result = stmnt.executeQuery(sql);
            list = new ArrayList<SemesterBean>();
            while (result.next()) {
                SemesterBean bean = new SemesterBean();
                bean.setYear(result.getString("year"));
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
        return list;
    }

    public ArrayList<SemesterBean> getSemsterTerm(String year) {
        ArrayList<SemesterBean> list = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT term FROM semester WHERE year = ? AND start_date<=CURRENT_DATE";
            PreparedStatement preStmnt = connt.prepareStatement(preQuery);
            preStmnt.setString(1, year);
            ResultSet result = preStmnt.executeQuery();
            list = new ArrayList<SemesterBean>();
            while (result.next()) {
                SemesterBean bean = new SemesterBean();
                bean.setTerm(result.getInt("term"));
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
        return list;
    }

    public SemesterBean getSemsterDate(String year, int term) {
        SemesterBean semBean = null;
        try {
            Connection connt = getConnection();
            String perQuery = "SELECT start_date, end_date, 5 * ((DATEDIFF(end_date, start_date) ) DIV 7) + MID('0123455501234445012333450122234501101234000123450', 7 * WEEKDAY(start_date) + WEEKDAY(end_date) + 1, 1) AS countBusinDay FROM class, semester WHERE class.termId = semester.id AND semester.start_date AND semester.year = ? AND semester.term = ?";
            PreparedStatement pStmnt = connt.prepareStatement(perQuery);
            pStmnt.setString(1, year);
            pStmnt.setInt(2, term);
            ResultSet result = pStmnt.executeQuery();
            if (result.next()) {
                semBean = new SemesterBean();
                semBean.setStart_date(result.getString("start_date"));
                semBean.setEnd_date(result.getString("end_date"));
                semBean.setTerm(term);
                semBean.setYear(year);
                semBean.setCountBusineseDate(result.getInt("countBusinDay"));
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return semBean;
    }

    public ArrayList<SchedulerBean> getSchCalenderholiday() {
        ArrayList<SchedulerBean> list = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT *, WEEKDAY(start_date) AS start_day_week, WEEKDAY(end_date) AS end_day_week, end_date - start_date AS countDate, 5 * ((DATEDIFF(end_date, start_date) ) DIV 7) + MID('0123455501234445012333450122234501101234000123450', 7 * WEEKDAY(start_date) + WEEKDAY(end_date) + 1, 1) AS countBusinDay FROM scheduler WHERE holiday = true";
            Statement stmnt = connt.createStatement();
            ResultSet result = stmnt.executeQuery(sql);
            list = new ArrayList<SchedulerBean>();
            while (result.next()) {
                SchedulerBean scheBean = new SchedulerBean();
                scheBean.setId(result.getString("id"));
                scheBean.setTitle(result.getString("title"));
                scheBean.setDescription(result.getString("description"));
                scheBean.setStart_date(result.getString("start_date"));
                scheBean.setEnd_date(result.getString("end_date"));
                scheBean.setHoliday(result.getBoolean("holiday"));
                scheBean.setSchoolDay(result.getBoolean("schoolDay"));
                scheBean.setStart_day_week(result.getInt("start_day_week"));
                scheBean.setEnd_date_week(result.getInt("end_day_week"));
                scheBean.setCountDate(result.getInt("countDate"));
                scheBean.setCountBusineseDay(result.getInt("countBusinDay"));
                list.add(scheBean);
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

    public ArrayList<StudentBean> getStudentAttend(String startDate, String endDate, String className) {
        ArrayList<StudentBean> list = null;
        try {
            Connection connt = getConnection();
            String perQuery = "SELECT COUNT(*) AS attendDay, student.* FROM studentattend, studentclass, student, class WHERE studentclass.classId = class.id AND studentclass.studentId = student.id AND class.class = ? AND studentattend.studentId = student.id AND attendDate >= ? AND attendDate <= ? GROUP BY studentattend.studentId ORDER BY student.fname ASC";
            PreparedStatement pStmnt = connt.prepareStatement(perQuery);
            pStmnt.setString(1, className);
            pStmnt.setString(2, startDate);
            pStmnt.setString(3, endDate);
            ResultSet result = pStmnt.executeQuery();
            list = new ArrayList<StudentBean>();
            while (result.next()) {
                StudentBean studentBean = new StudentBean();
                studentBean.setFname(result.getString("fname"));
                studentBean.setLname(result.getString("lname"));
                studentBean.setGender(result.getString("gender"));
                studentBean.setEmail(result.getString("email"));
                studentBean.setBirthday(result.getString("birthday"));
                studentBean.setId(result.getString("id"));
                studentBean.setAttendDay(result.getInt("attendDay"));
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

    public double getAttendanceRateTarget(String className) {
        double target = 0;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT attendanceTarget FROM class, attendanceTarget WHERE class.attendanceTargetId = attendanceTarget.id AND class.class = ?";
            PreparedStatement preStmnt = connt.prepareStatement(preQuery);
            preStmnt.setString(1, className);
            ResultSet result = preStmnt.executeQuery();
            if (result.next()) {
                target = result.getDouble("attendanceTarget");
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return target;
    }

    public ArrayList<AttendBean> getStudentAttendDetail(String studentId, String year, int term) {
        ArrayList<AttendBean> list = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT studentattend.*, class.termId FROM studentattend, class, semester, studentclass WHERE studentattend.studentId = ? AND studentattend.attendDate >= semester.start_date AND studentattend.attendDate <= semester.end_date AND semester.year = ? AND semester.term = ? AND studentclass.studentId = studentattend.studentId AND studentclass.classId = class.id";
            PreparedStatement preStmnt = connt.prepareStatement(preQuery);
            preStmnt.setString(1, studentId);
            preStmnt.setString(2, year);
            preStmnt.setInt(3, term);
            ResultSet result = preStmnt.executeQuery();
            list = new ArrayList<AttendBean>();
            while (result.next()) {
                AttendBean bean = new AttendBean();
                bean.setId(result.getString("studentAttendanceId"));
                bean.setDate(result.getString("attendDate"));
                bean.setAttend(result.getBoolean("attend"));
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
        return list;
    }

    public ArrayList<StudentBean> getAllStudentAttendZero(String className) {
        ArrayList<StudentBean> list = new ArrayList<StudentBean>();
        try {
            Connection connt = getConnection();
            //String preQuery = "SELECT * FROM studentattend, studentclass, student, class WHERE studentclass.classId = class.id AND studentclass.studentId = student.id AND class.class = ? AND studentattend.studentId = student.id AND attendDate = ?";
            String preQuery = "SELECT student.* FROM studentclass, class, student WHERE studentclass.classid = class.id AND studentclass.studentid = student.id AND class.class = ? ORDER BY student.fname ASC";
            PreparedStatement pStmnt = connt.prepareStatement(preQuery);
            pStmnt.setString(1, className);
            //  pStmnt.setString(2, date);
            ResultSet result = pStmnt.executeQuery();
            while (result.next()) {
                StudentBean studentBean = new StudentBean();
                studentBean.setFname(result.getString("fname"));
                studentBean.setLname(result.getString("lname"));
                studentBean.setGender(result.getString("gender"));
                studentBean.setEmail(result.getString("email"));
                studentBean.setId(result.getString("id"));
                studentBean.setAttendRate(0);
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

    public ArrayList<LectureTimeBean> getAllTimeLecture() {
        ArrayList<LectureTimeBean> list = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT * FROM timeTable ORDER BY timeTable.starttime ASC";
            Statement stmnt = connt.createStatement();
            ResultSet result = stmnt.executeQuery(sql);
            list = new ArrayList<LectureTimeBean>();
            while (result.next()) {
                LectureTimeBean bean = new LectureTimeBean();
                bean.setId(result.getString("timeId"));
                bean.setStartTime(result.getString("starttime"));
                bean.setEndTime(result.getString("endtime"));
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
        return list;
    }

    public ArrayList<LectureBean> getAllLecture(String teacherId) {
        ArrayList<LectureBean> list = null;
        try {
            Connection connt = getConnection();
            String preQuery = "SELECT * FROM lecture, class, timetable, daytable WHERE lecture.classId = class.id AND lecture.timeId = timetable.timeId AND lecture.teacherId = ? and lecture.dayId = daytable.id";
            PreparedStatement preStmnt = connt.prepareStatement(preQuery);
            preStmnt.setString(1, teacherId);
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
    
        public ArrayList<LectureDayBean> getAllDayLecture() {
        ArrayList<LectureDayBean> list = null;
        try {
            Connection connt = getConnection();
            String sql = "SELECT * FROM daytable ORDER BY day ASC";
            Statement stmnt = connt.createStatement();
            ResultSet result = stmnt.executeQuery(sql);
            list = new ArrayList<LectureDayBean>();
            while (result.next()) {
                LectureDayBean bean = new LectureDayBean();
                bean.setId(result.getString("id"));
                bean.setDay(result.getString("day"));
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
        return list;
    }
    
    
    //SELECT *, WEEKDAY(start_date) as start_day_week, WEEKDAY(end_date) as end_day_week FROM scheduler
    //SELECT *, WEEKDAY(start_date) as start_day_week, WEEKDAY(end_date) as end_day_week, end_date - start_date as countDate FROM scheduler WHERE holiday = true

    // get count date select datediff(semester.end_date, semester.start_date) FROM semester, class WHERE class.termId = semester.id AND class.class = "1A"
}
