/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.db.AdminDB;

/**
 *
 * @author JerryKwok
 */
@WebServlet(name = "AdminLectureController", urlPatterns = {"/admin/lecture"})
public class AdminLectureController extends HttpServlet {

    AdminDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new AdminDB(dbUrl, dbUser, dbPassword);
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
        try {
            HttpSession session = request.getSession(false);
            if (session.getAttribute("username") == null) {
                response.sendRedirect("../index");
                return;
            }
            String username = (String) session.getAttribute("username");
            String action = request.getParameter("action");
            String lectureId = request.getParameter("id");
            request.setAttribute("lectureList", db.getLecture());
            request.setAttribute("searchList", db.getSearchHistory("1", username));
            RequestDispatcher rd = null;
            if (action != null && action.equals("delete")) {
                db.deleteLecture(lectureId);
                request.setAttribute("deleteMes", lectureId);
                request.setAttribute("lectureList", db.getLecture());
                rd = this.getServletContext()
                        .getRequestDispatcher("/admin/lecture.jsp");
                rd.forward(request, response);
            } else if (action != null && action.equals("addPage")) {
                request.setAttribute("classList", db.getClassList());
                request.setAttribute("dayList", db.getDayList());
                request.setAttribute("timeList", db.getTimeList());
                request.setAttribute("teacherList", db.getTeacher());
                rd = this.getServletContext()
                        .getRequestDispatcher("/admin/lectureAdd.jsp");
                rd.forward(request, response);
            } else if (action != null && action.equals("search")) {
                String searchVal = request.getParameter("searchVal");
                request.setAttribute("lectureList", db.searchLecture(searchVal));
                db.insertSearchHistory(searchVal, "1", username);
                request.setAttribute("searchList", db.getSearchHistory("1", username));
                 rd = this.getServletContext()
                    .getRequestDispatcher("/admin/lecture.jsp");
            rd.forward(request, response);
            } else if (action != null && action.equals("save")) {
                String lecture = request.getParameter("lecture");
                String description = request.getParameter("description");
                String className = request.getParameter("className");
                String time = request.getParameter("time");
                String day = request.getParameter("day");
                String teacher = request.getParameter("teacher");
                if (db.checkTeacherDupSchedule(time, teacher, lectureId)) {
                    request.setAttribute("classList", db.getClassList());
                    request.setAttribute("dayList", db.getDayList());
                    request.setAttribute("timeList", db.getTimeList());
                    request.setAttribute("teacherList", db.getTeacher());
                    request.setAttribute("lectureBean", db.getLectureDetials(lectureId));
                    request.setAttribute("lectureId", lectureId);
                    request.setAttribute("lecture", lecture);
                    request.setAttribute("description", description);
                    request.setAttribute("className", className);
                    request.setAttribute("time", time);
                    request.setAttribute("day", day);
                    request.setAttribute("teacher", teacher);
                    request.setAttribute("dupMes", true);
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/lectureEdit.jsp");
                    rd.forward(request, response);
                    return;
                }
                db.updateLecture(lectureId, lecture, description, className, time, day, teacher);
                request.setAttribute("saveMes", lectureId);
                request.setAttribute("lectureList", db.getLecture());
                rd = this.getServletContext()
                        .getRequestDispatcher("/admin/lecture.jsp");
                rd.forward(request, response);
            } else if (action != null && action.equals("add")) {
                String lecture = request.getParameter("lecture");
                String description = request.getParameter("description");
                String className = request.getParameter("className");
                String time = request.getParameter("time");
                String day = request.getParameter("day");
                String teacher = request.getParameter("teacher");
                if (db.checkTeacherDupSchedule(time, teacher)) {
                    request.setAttribute("classList", db.getClassList());
                    request.setAttribute("dayList", db.getDayList());
                    request.setAttribute("timeList", db.getTimeList());
                    request.setAttribute("teacherList", db.getTeacher());
                    request.setAttribute("lectureId", lectureId);
                    request.setAttribute("lecture", lecture);
                    request.setAttribute("description", description);
                    request.setAttribute("className", className);
                    request.setAttribute("time", time);
                    request.setAttribute("day", day);
                    request.setAttribute("teacher", teacher);
                    request.setAttribute("dupMes", true);
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/lectureAdd.jsp");
                    rd.forward(request, response);
                    return;
                }
                db.insertLecture(lecture, description, className, time, day, teacher);
                request.setAttribute("lectureList", db.getLecture());
                request.setAttribute("addMes", true);
            }
            if (lectureId != null) {
                request.setAttribute("lectureId", lectureId);
                request.setAttribute("classList", db.getClassList());
                request.setAttribute("dayList", db.getDayList());
                request.setAttribute("timeList", db.getTimeList());
                request.setAttribute("teacherList", db.getTeacher());
                request.setAttribute("lectureBean", db.getLectureDetials(lectureId));
                rd = this.getServletContext()
                        .getRequestDispatcher("/admin/lectureEdit.jsp");
                rd.forward(request, response);
            }

            rd = this.getServletContext()
                    .getRequestDispatcher("/admin/lecture.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("../index");
            return;
        }
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
