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
@WebServlet(name = "AdminClassController", urlPatterns = {"/admin/class"})
public class AdminClassController extends HttpServlet {

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
            String classId = request.getParameter("id");

            RequestDispatcher rd = null;
            request.setAttribute("classList", db.getClassList());
            request.setAttribute("searchList", db.getSearchHistory("6", username));
            if (action != null && action.equals("addPage")) {
                request.setAttribute("teacherList", db.getNotClassTeacher());
                request.setAttribute("classYearList", db.getClassYear());
                rd = this.getServletContext().getRequestDispatcher("/admin/classAdd.jsp");
                rd.forward(request, response);

            } else if (action != null && action.equals("add")) {
                String className = request.getParameter("class");
                String teacherId = request.getParameter("teacherId");
                String yearId = request.getParameter("yearId");
                if (db.checkClassNameDupli(className)) {
                    System.out.println("TESTPOTIN_TRUE");
                    request.setAttribute("className", className);
                    request.setAttribute("teacherId", teacherId);
                    request.setAttribute("yearId", yearId);
                    request.setAttribute("dupMes", true);
                    request.setAttribute("teacherList", db.getNotClassTeacher());
                    request.setAttribute("classYearList", db.getClassYear());
                    rd = this.getServletContext().getRequestDispatcher("/admin/classAdd.jsp");
                    rd.forward(request, response);
                }else{
                System.out.println("TESTPOTIN_false");
                db.insertClass(className, teacherId, yearId, 1);
                request.setAttribute("addMes", true);
                request.setAttribute("classList", db.getClassList());
                
                rd = this.getServletContext().getRequestDispatcher("/admin/class.jsp");
                rd.forward(request, response);
                
                }
            } else if (action != null && action.equals("save")) {
                String className = request.getParameter("class");
                String teacherId = request.getParameter("teacherId");
                String yearId = request.getParameter("yearId");
                if (db.checkClassNameDupli(className, classId)) {
                    request.setAttribute("className", className);
                    request.setAttribute("teacherId", teacherId);
                    request.setAttribute("yearId", yearId);
                    request.setAttribute("dupMes", true);
                    request.setAttribute("classBean", db.getClassDetails(classId));
                    request.setAttribute("teacherList", db.getNotClassTeacher());
                    request.setAttribute("classYearList", db.getClassYear());
                    rd = this.getServletContext().getRequestDispatcher("/admin/classEdit.jsp");
                    rd.forward(request, response);
                }
                db.updateClass(className, teacherId, yearId, 1, classId);
                System.out.println(className + "asd");
                 System.out.println(teacherId + "asd");
                  System.out.println(yearId + "asd");
                   System.out.println(classId + "asd");
                request.setAttribute("classList", db.getClassList());
                request.setAttribute("saveMes", classId);
                rd = this.getServletContext().getRequestDispatcher("/admin/class.jsp");
                rd.forward(request, response);
                
            } else if (action != null && action.equals("search")) {
                String searchVal = request.getParameter("searchVal");
                db.insertSearchHistory(searchVal, "6", username);
                request.setAttribute("classList", db.searchClass(searchVal));
                request.setAttribute("searchList", db.getSearchHistory("6", username));
                request.setAttribute("search", true);
                rd = this.getServletContext().getRequestDispatcher("/admin/class.jsp");
                rd.forward(request, response);
            } else if (action != null && action.equals("delete")) {
                db.deleteClass(classId);
                request.setAttribute("deleteMes", classId);
                request.setAttribute("classList", db.getClassList());
            } else if (classId != null) {
                request.setAttribute("classBean", db.getClassDetails(classId));
                request.setAttribute("teacherList", db.getNotClassTeacher());
                request.setAttribute("classYearList", db.getClassYear());
                rd = this.getServletContext().getRequestDispatcher("/admin/classEdit.jsp");
                rd.forward(request, response);
            }

            rd = this.getServletContext().getRequestDispatcher("/admin/class.jsp");
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
