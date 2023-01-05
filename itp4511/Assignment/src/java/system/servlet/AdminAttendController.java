/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.bean.StudentBean;
import system.db.AdminDB;
import system.db.TeacherDB;

/**
 *
 * @author JerryKwok
 */
@WebServlet(name = "AdminAttendController", urlPatterns = {"/admin/attendance"})
public class AdminAttendController extends HttpServlet {

    AdminDB db;
    TeacherDB tdb;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new AdminDB(dbUrl, dbUser, dbPassword);
        tdb = new TeacherDB(dbUrl, dbUser, dbPassword);
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
        try {
            if (session.getAttribute("username") == null) {
                response.sendRedirect("../index");
                return;
            }
        } catch (Exception e) {
            response.sendRedirect("../index");
            return;
        }
        String teacherId = (String) session.getAttribute("username");
        String className = request.getParameter("class");
        String attendDate = request.getParameter("date");
        String action = request.getParameter("action");
        RequestDispatcher rd = null;
        if (className == null) {
            request.setAttribute("classList", db.getAllClass());
            rd = this.getServletContext().getRequestDispatcher("/admin/attendance.jsp");
            rd.forward(request, response);
            return;
        }

        if (action != null && action.equals("save")) {
            if (className != null && attendDate != null) {
                ArrayList<StudentBean> studentList = tdb.getAllStudentClass(className);
                String[] attendStudents = request.getParameterValues("attend");
                if (attendStudents != null) {
                    for (String studentId : attendStudents) {
                        tdb.insertStudentAttendance(studentId, attendDate, true);
                        for (StudentBean student : studentList) {
                            if (student.getId().equals(studentId)) {
                                studentList.remove(student);
                                break;
                            }
                        }
                    }
                }

                for (StudentBean student : studentList) {
                    tdb.insertStudentAttendance(student.getId(), attendDate, false);
                }
            }
            request.setAttribute("message", true);
        }
        if (attendDate == null) {
            attendDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
        request.setAttribute("date", attendDate);
        request.setAttribute("studentAttendList", tdb.getAttendClass(className, attendDate));
        request.setAttribute("classList", db.getAllClass());
        rd = this.getServletContext().getRequestDispatcher("/admin/attendanceStudent.jsp");
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
