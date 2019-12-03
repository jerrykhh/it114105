/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.bean.SchedulerBean;
import system.bean.SemesterBean;
import system.bean.StudentBean;
import system.db.TeacherDB;

/**
 *
 * @author JerryKwok
 */
@WebServlet(name = "TeacherReportController", urlPatterns = {"/teacher/report"})
public class TeacherReportController extends HttpServlet {

    private TeacherDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new TeacherDB(dbUrl, dbUser, dbPassword);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            response.sendRedirect("../index.jsp");
            return;
        }
        RequestDispatcher rd;
        String className = request.getParameter("class");
        String strTerm = request.getParameter("term");
        String studentId = request.getParameter("studentId");
        request.setAttribute("classList", db.getAllClass());
        if (className != null) {
            String year = request.getParameter("year");
            ArrayList<String> errList = new ArrayList<String>();
            if (strTerm == null) {
                strTerm = "1";
            };
            if (year == null) {
                year = Calendar.getInstance().get(Calendar.YEAR) + "";
            }

            try {
                int term = Integer.parseInt(strTerm);
                SemesterBean semBean = db.getSemsterDate(year, term);
                int totalSchoolDay = semBean.getCountBusineseDate();
                int countHoliday = 0;
                for (SchedulerBean scheduler : db.getSchCalenderholiday()) {
                    if (scheduler.isHoliday() && scheduler.getCountDate() == 0) {
                        switch (scheduler.getStart_day_week()) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                countHoliday++;
                        }
                    } else if (scheduler.isHoliday() && scheduler.getCountDate() > 0) {
                        countHoliday += scheduler.getCountBusineseDay() + 1;
                        //countHoliday += scheduler.getCountDate();
                    }
                }
                totalSchoolDay = totalSchoolDay - countHoliday;
                System.out.println(totalSchoolDay);
                ArrayList<StudentBean> studentList = db.getStudentAttend(semBean.getStart_date(), semBean.getEnd_date(), className);
                int allStudentCountDay = 0;
                int numberOfStudent = 0;
                int numberOfStudentNotMeetTar = 0;
                int numberOfStudentMeetTar = 0;
                double avgStudentAttendDay = 0;
                StudentBean studentBean = null;
                for (StudentBean student : studentList) {
                    int countDay = student.getAttendDay();
                    allStudentCountDay += countDay;
                    double attendRate = Math.round((countDay / (float) totalSchoolDay) * 100);
                    student.setAttendRate(attendRate);
                    numberOfStudent++;
                    if (attendRate >= db.getAttendanceRateTarget(className)) {
                        numberOfStudentMeetTar++;
                    } else {
                        numberOfStudentNotMeetTar++;
                    }
                    System.out.println(student.getId());
                    if (studentId != null && student.getId().equals(studentId)) {
                        studentBean = student;
                    }
                }
                if (studentList.size() == 0) {
                    request.setAttribute("studentAttendList", db.getAllStudentAttendZero(className));
                } else {
                    request.setAttribute("studentAttendList", studentList);
                }
                if (studentId != null) {
                    studentBean.setAttendList(db.getStudentAttendDetail(studentId, year, term));
                    request.setAttribute("studentDetials", studentBean);
                }
                try {
                    avgStudentAttendDay = (allStudentCountDay / numberOfStudent);
                } catch (ArithmeticException e) {
                    avgStudentAttendDay = 0;
                }
                request.setAttribute("numberOfStudentMeetTar", numberOfStudentMeetTar);
                request.setAttribute("numberOfStudentNotMeetTar", numberOfStudentNotMeetTar);
                request.setAttribute("attendAVG", (totalSchoolDay - avgStudentAttendDay) / totalSchoolDay * 100);

            } catch (NumberFormatException e) {
                errList.add("The term must be Integer");
            }

            request.setAttribute("errMessage", errList);
            request.setAttribute("yearList", db.getLatestReportYear());
            request.setAttribute("termList", db.getSemsterTerm(year));
            if (studentId != null) {
                rd = this.getServletContext().getRequestDispatcher("/teacher/reportStudent.jsp");
                rd.forward(request, response);
            } else {
                rd = this.getServletContext().getRequestDispatcher("/teacher/reportClass.jsp");
                rd.forward(request, response);
            }
        }

        rd = this.getServletContext().getRequestDispatcher("/teacher/report.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
