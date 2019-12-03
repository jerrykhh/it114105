/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.bean.LoginBean;
import system.db.LoginValid;

/**
 *
 * @author jerrykwok
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private LoginValid db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new LoginValid(dbUrl, dbUser, dbPassword);
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
        response.setContentType("text/html;charset=UTF-8");

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
        doPost(request, response);
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

        String action = request.getParameter("action");
        if (action == null) {
            RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        } else if ("authenticate".equals(action)) {
            try {
                doAuthenticate(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } else if ("logout".equals(action)) {
            doLogout(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void doAuthenticate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        String role = request.getParameter("role");
        System.out.println(action + username + pwd + role);
        if (!role.equals("Teacher") && !role.equals("Student") && !role.equals("Admin")) {
            response.sendRedirect("/index?roleError");
        }
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(pwd);
        loginBean.setRole(role);
        loginBean = db.validateLogin(loginBean);
        if (loginBean == null) {
            response.sendRedirect("login?loginError&role=" + role);
            return;
        }
        String targetURL = null;
        HttpSession session = request.getSession();
        session.setAttribute("username", loginBean.getName());
        session.setAttribute("userrole", loginBean.getRole());
        switch (role) {
            case "Student":
                targetURL = "/show-class";
                break;
            case "Teacher":
                targetURL = "teacher/dashboard";
                break;
            case "Admin":
                targetURL = "admin/dashboard";
                break;
        }
        response.sendRedirect(targetURL);
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        boolean result = false;
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            result = true;
        }
        return result;
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("username");
            session.invalidate();
        }
        response.sendRedirect("/index");
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
