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
@WebServlet(name = "AdminClassRegController", urlPatterns = {"/admin/classReg"})
public class AdminClassRegController extends HttpServlet {

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
            String studentClassID = request.getParameter("id"); // edit function
            if (action != null && action.equals("delete")) {
                String studentId = db.deleteStudentClass(studentClassID);
                request.setAttribute("deletenMes", studentId);
                request.setAttribute("studentClassList", db.getAllStudentClass());
                request.setAttribute("searchList", db.getSearchHistory("2", username));
                RequestDispatcher rd = this.getServletContext()
                        .getRequestDispatcher("/admin/classReg.jsp");
                rd.forward(request, response);
            } else if (action != null && action.equals("save")) {
                String className = request.getParameter("className");
                db.updateStudentClass(studentClassID, className);
                request.setAttribute("saveMes", studentClassID);
                request.setAttribute("studentClassList", db.getAllStudentClass());
                request.setAttribute("searchList", db.getSearchHistory("2", username));
                RequestDispatcher rd = this.getServletContext()
                        .getRequestDispatcher("/admin/classReg.jsp");
                rd.forward(request, response);
            } else if (action != null && action.equals("search")) {
                String searchVal = request.getParameter("searchVal");
                request.setAttribute("studentClassList", db.searchStudentClass(searchVal));
                db.insertSearchHistory(searchVal, "2", username);
                request.setAttribute("searchHis", true);
                request.setAttribute("searchList", db.getSearchHistory("2", username));
                RequestDispatcher rd = this.getServletContext()
                        .getRequestDispatcher("/admin/classReg.jsp");
                rd.forward(request, response);
            } else if (action != null && action.equals("addPage")) {
                request.setAttribute("classList", db.getAllClass());
                request.setAttribute("studnetIdList", db.getUnRegAllStudent());
                RequestDispatcher rd = this.getServletContext()
                        .getRequestDispatcher("/admin/classRegAdd.jsp");
                rd.forward(request, response);
            } else if (action != null && action.equals("add")) {
                String className = request.getParameter("className");
                db.insertStudentClass(studentClassID, className);
                request.setAttribute("studentClassList", db.getAllStudentClass());
                request.setAttribute("searchList", db.getSearchHistory("2", username));
                request.setAttribute("addMes", className);
                RequestDispatcher rd = this.getServletContext()
                        .getRequestDispatcher("/admin/classReg.jsp");
                rd.forward(request, response);
            }

            if (studentClassID != null) {
                request.setAttribute("studenBean", db.getStudentClassDeital(studentClassID));
                request.setAttribute("classList", db.getAllClass());
                request.setAttribute("studentClassID", studentClassID);
                RequestDispatcher rd = this.getServletContext()
                        .getRequestDispatcher("/admin/classRegStudent.jsp");
                rd.forward(request, response);
            }
            request.setAttribute("studentClassList", db.getAllStudentClass());
            request.setAttribute("searchList", db.getSearchHistory("2", username));
            RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/admin/classReg.jsp");
            rd.forward(request, response);
        } catch (NullPointerException e) {
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
