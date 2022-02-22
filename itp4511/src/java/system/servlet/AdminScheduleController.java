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
@WebServlet(name = "AdminScheduleController", urlPatterns = {"/admin/schedule"})
public class AdminScheduleController extends HttpServlet {

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
            RequestDispatcher rd = null;
            String className = request.getParameter("class");
            String action = request.getParameter("action");
            String scheduleId = request.getParameter("id");
            request.setAttribute("scheduleList", db.getScheduler());
            request.setAttribute("scheduleAllList", db.getSchedulerAll());
            if (className != null && className.equalsIgnoreCase("schoolDay")) {
                if (action != null && action.equals("addPage")) {
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/scheduleSchoolDayAdd.jsp");
                    rd.forward(request, response);
                } else if (action != null && action.equals("search")) {
                    String searchVal = request.getParameter("searchVal");
                    request.setAttribute("scheduleAllList", db.searchScheduler(searchVal));
                     request.setAttribute("scheduleList", db.searchScheduler(searchVal));
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/scheduleSchoolDay.jsp");
                    rd.forward(request, response);

                } else if (action != null && action.equals("save")) {
                    String title = request.getParameter("title");
                    String startDate = request.getParameter("startDate");
                    String endDate = request.getParameter("endDate");
                    String isHoliday = request.getParameter("holiday");
                    String isSchoolDay = request.getParameter("schoolDay");
                    if (isHoliday != null && isHoliday.equalsIgnoreCase("isHoliday") && isSchoolDay != null && isSchoolDay.equalsIgnoreCase("isSchoolDay")) {
                        request.setAttribute("title", title);
                        request.setAttribute("startDate", startDate);
                        request.setAttribute("endDate", endDate);
                        request.setAttribute("errMes", true);
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/scheduleSchoolDayEdit.jsp");
                        rd.forward(request, response);
                    }
                    System.out.println("TESTADDD");
                    if (isHoliday == null && isSchoolDay == null) {
                        request.setAttribute("title", title);
                        request.setAttribute("startDate", startDate);
                        request.setAttribute("endDate", endDate);
                        request.setAttribute("notselectMes", true);
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/scheduleSchoolDayEdit.jsp");
                        rd.forward(request, response);
                    }
                    db.updateSchedule(title, startDate, endDate, isHoliday, isSchoolDay, scheduleId);
                    request.setAttribute("saveMes", scheduleId);
                    request.setAttribute("scheduleList", db.getScheduler());
                    request.setAttribute("scheduleAllList", db.getSchedulerAll());
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/scheduleSchoolDay.jsp");
                    rd.forward(request, response);

                } else if (action != null && action.equals("add")) {
                    System.out.println("TESTADDD");
                    String title = request.getParameter("title");
                    String startDate = request.getParameter("startDate");
                    String endDate = request.getParameter("endDate");
                    String isHoliday = request.getParameter("holiday");
                    String isSchoolDay = request.getParameter("schoolDay");
                    System.out.println("isC" + isSchoolDay);
                    if (isHoliday != null && isHoliday.equalsIgnoreCase("isHoliday") && isSchoolDay != null && isSchoolDay.equalsIgnoreCase("isSchoolDay")) {
                        request.setAttribute("title", title);
                        request.setAttribute("startDate", startDate);
                        request.setAttribute("endDate", endDate);
                        request.setAttribute("errMes", true);
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/scheduleSchoolDayAdd.jsp");
                        rd.forward(request, response);
                    }
                    System.out.println("TESTADDD");
                    if (isHoliday == null && isSchoolDay == null) {
                        request.setAttribute("title", title);
                        request.setAttribute("startDate", startDate);
                        request.setAttribute("endDate", endDate);
                        request.setAttribute("notselectMes", true);
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/scheduleSchoolDayAdd.jsp");
                        rd.forward(request, response);
                    }
                    System.out.println("test");
                    db.insertSchedule(title, startDate, endDate, isHoliday, isSchoolDay);
                    request.setAttribute("addMes", true);
                    request.setAttribute("scheduleAllList", db.getSchedulerAll());
                    request.setAttribute("scheduleList", db.getScheduler());
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/scheduleSchoolDay.jsp");
                    rd.forward(request, response);

                } else if (action != null && action.equals("delete") && scheduleId != null) {
                    db.deleteSchedule(scheduleId);
                    request.setAttribute("deleteMes", scheduleId);
                    request.setAttribute("scheduleAllList", db.getSchedulerAll());
                    request.setAttribute("scheduleList", db.getScheduler());
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/scheduleSchoolDay.jsp");
                    rd.forward(request, response);
                } else if (scheduleId != null) {
                    request.setAttribute("scheduleBean", db.getSchedulerDetials(scheduleId));
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/scheduleSchoolDayEdit.jsp");
                    rd.forward(request, response);
                }

                rd = this.getServletContext()
                        .getRequestDispatcher("/admin/scheduleSchoolDay.jsp");
                rd.forward(request, response);
            }
            if (className != null) {
                request.setAttribute("lectureList", db.getLecture(className));
                request.setAttribute("timeList", db.getTimeList());
                request.setAttribute("dayList", db.getDayList());
                rd = this.getServletContext()
                        .getRequestDispatcher("/admin/scheduleClass.jsp");
                rd.forward(request, response);
            } else if (className != null && className.equals("schoolDay")) {

            }
            request.setAttribute("classList", db.getAllClass());

            rd = this.getServletContext()
                    .getRequestDispatcher("/admin/schedule.jsp");
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
