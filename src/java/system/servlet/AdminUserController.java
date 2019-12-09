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
@WebServlet(name = "AdminUserController", urlPatterns = {"/admin/user"})
public class AdminUserController extends HttpServlet {

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
            String role = request.getParameter("role");

            if (role.equals("Student")) {
                request.setAttribute("studentList", db.getStudent());
                String studentId = request.getParameter("id");
                String action = request.getParameter("action");
                System.out.println("TEST Point1");
                if (studentId != null && action != null && action.equals("delete")) {
                    System.out.println("TEST Point2");
                    db.deleteStudent(studentId);
                    request.setAttribute("deleteMes", studentId);
                    request.setAttribute("studentList", db.getStudent());
                } else if (action != null && action.equals("addPage")) {
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/userAdd.jsp");
                    rd.forward(request, response);
                    return;
                } else if (action != null && action.equals("add")) {
                    System.out.println("TEST Point3");
                    String fname = request.getParameter("fname");
                    String lname = request.getParameter("lname");
                    String gender = request.getParameter("gender");
                    String birthday = request.getParameter("birthday");
                    String password = request.getParameter("password");
                    String pwd = request.getParameter("pwd");
                    if (!password.equals(pwd)) {
                        request.setAttribute("fname", fname);
                        request.setAttribute("lname", lname);
                        request.setAttribute("gender", gender);
                        request.setAttribute("birthday", birthday);
                        request.setAttribute("pwdMes", true);
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/userAdd.jsp");
                        rd.forward(request, response);
                        return;
                    }
                    String insertId = db.insertStudent(fname, lname, gender, birthday, password);
                    request.setAttribute("addMes", insertId);
                    request.setAttribute("studentList", db.getStudent());
                } else if (action != null && action.equals("search")) {
                    System.out.println("TEST Point4");
                    String searchVal = request.getParameter("searchVal");
                    request.setAttribute("studentList", db.searchStudent(searchVal));
                } else if (action != null && action.equals("save")) {
                    System.out.println("TEST Point5");
                    String id = request.getParameter("id");
                    String fname = request.getParameter("fname");
                    String lname = request.getParameter("lname");
                    String gender = request.getParameter("gender");
                    String birthday = request.getParameter("birthday");
                    System.out.println("Birthday" + birthday);
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String pwd = request.getParameter("pwd");
                    request.setAttribute("id", id);
                    if (!password.equals(pwd)) {
                        request.setAttribute("id", id);
                        request.setAttribute("fname", fname);
                        request.setAttribute("lname", lname);
                        request.setAttribute("gender", gender);
                        request.setAttribute("birthday", birthday);
                        request.setAttribute("email", email);
                        request.setAttribute("pwdMes", true);
                        request.setAttribute("student", db.getStudentDetials(studentId));
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/userEdit.jsp");
                        rd.forward(request, response);
                        return;
                    }
                    if (password != "") {
                        db.updateStudent(id, fname, lname, gender, birthday, pwd);
                    } else {
                        db.updateStudent(id, fname, lname, gender, birthday);
                    }
                    request.setAttribute("saveMes", id);
                    request.setAttribute("studentList", db.getStudent());
                } else if (studentId != null) {
                    System.out.println("TEST Point6");
                    request.setAttribute("student", db.getStudentDetials(studentId));
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/userEdit.jsp");
                    rd.forward(request, response);
                }

            } else if (role.equals("Teacher")) {
                String action = request.getParameter("action");
                String teacherId = request.getParameter("id");
                request.setAttribute("teacherList", db.getTeacher());
                if (teacherId != null && action != null && action.equals("delete")) {
                    db.deleteTeacher(teacherId);
                    request.setAttribute("deleteMes", teacherId);
                    request.setAttribute("teacherList", db.getTeacher());
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/user.jsp");
                    rd.forward(request, response);
                } else if (action != null && action.equals("addPage")) {
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/userAdd.jsp");
                    rd.forward(request, response);
                } else if (action != null && action.equals("add")) {
                    String fname = request.getParameter("fname");
                    String lname = request.getParameter("lname");
                    String gender = request.getParameter("gender");
                    String birthday = request.getParameter("birthday");
                    String password = request.getParameter("password");
                    String pwd = request.getParameter("pwd");
                    if (db.checkTeacherDupliId(teacherId)) {
                        request.setAttribute("fname", fname);
                        request.setAttribute("lname", lname);
                        request.setAttribute("gender", gender);
                        request.setAttribute("birthday", birthday);
                        request.setAttribute("idMes", true);
                        request.setAttribute("id", teacherId);
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/userAdd.jsp");
                        rd.forward(request, response);
                        return;
                    }
                    if (!password.equals(pwd)) {
                        request.setAttribute("fname", fname);
                        request.setAttribute("lname", lname);
                        request.setAttribute("gender", gender);
                        request.setAttribute("birthday", birthday);
                        request.setAttribute("pwdMes", true);
                        request.setAttribute("id", teacherId);
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/userAdd.jsp");
                        rd.forward(request, response);
                        return;
                    }
                    db.insertTeacher(teacherId, fname, lname, gender, birthday, password);
                    request.setAttribute("addMes", teacherId);
                    request.setAttribute("teacherList", db.getTeacher());

                } else if (action != null && action.equals("search")) {
                    String searchVal = request.getParameter("searchVal");
                    request.setAttribute("teacherList", db.searchTeacher(searchVal));
                } else if (action != null && action.equals("save")) {
                    String fname = request.getParameter("fname");
                    String lname = request.getParameter("lname");
                    String gender = request.getParameter("gender");
                    String birthday = request.getParameter("birthday");
                    String password = request.getParameter("password");
                    String pwd = request.getParameter("pwd");

                    if (!password.equals(pwd)) {
                        request.setAttribute("fname", fname);
                        request.setAttribute("lname", lname);
                        request.setAttribute("gender", gender);
                        request.setAttribute("birthday", birthday);
                        request.setAttribute("pwdMes", true);
                        request.setAttribute("id", teacherId);
                        request.setAttribute("teacher", db.getTeacherDetials(teacherId));
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/userEdit.jsp");
                        rd.forward(request, response);
                        return;
                    }
                    if (password != "") {
                        db.updateTeacher(teacherId, password, fname, lname, gender, birthday);
                    } else {
                        db.updateTeacher(teacherId, fname, lname, gender, birthday);
                    }
                    request.setAttribute("saveMes", teacherId);
                    request.setAttribute("teacherList", db.getTeacher());
                } else if (teacherId != null) {
                    System.out.println("TEST Point6");
                    request.setAttribute("teacher", db.getTeacherDetials(teacherId));
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/userEdit.jsp");
                    rd.forward(request, response);
                }
            } else if (role.equals("Admin")) {
                
                 String action = request.getParameter("action");
                String adminId = request.getParameter("id");
                request.setAttribute("adminList", db.getAdmin());
                if (adminId != null && action != null && action.equals("delete")) {
                    db.deleteAdmin(adminId);
                    request.setAttribute("deleteMes", adminId);
                    request.setAttribute("adminList", db.getAdmin());
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/user.jsp");
                    rd.forward(request, response);
                } else if (action != null && action.equals("addPage")) {
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/userAdd.jsp");
                    rd.forward(request, response);
                } else if (action != null && action.equals("add")) {
                    String fname = request.getParameter("fname");
                    String lname = request.getParameter("lname");
                    String gender = request.getParameter("gender");
                    String birthday = request.getParameter("birthday");
                    String password = request.getParameter("password");
                    String pwd = request.getParameter("pwd");
                    if (db.checkAdminDupliId(adminId)) {
                        request.setAttribute("fname", fname);
                        request.setAttribute("lname", lname);
                        request.setAttribute("gender", gender);
                        request.setAttribute("birthday", birthday);
                        request.setAttribute("idMes", true);
                        request.setAttribute("id", adminId);
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/userAdd.jsp");
                        rd.forward(request, response);
                        return;
                    }
                    if (!password.equals(pwd)) {
                        request.setAttribute("fname", fname);
                        request.setAttribute("lname", lname);
                        request.setAttribute("gender", gender);
                        request.setAttribute("birthday", birthday);
                        request.setAttribute("pwdMes", true);
                        request.setAttribute("id", adminId);
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/userAdd.jsp");
                        rd.forward(request, response);
                        return;
                    }
                    db.insertAdmin(adminId, fname, lname, gender, birthday, password);
                    request.setAttribute("addMes", adminId);
                    request.setAttribute("adminList", db.getAdmin());

                } else if (action != null && action.equals("search")) {
                    String searchVal = request.getParameter("searchVal");
                    request.setAttribute("adminList", db.searchAdmin(searchVal));
                } else if (action != null && action.equals("save")) {
                    String fname = request.getParameter("fname");
                    String lname = request.getParameter("lname");
                    String gender = request.getParameter("gender");
                    String birthday = request.getParameter("birthday");
                    String password = request.getParameter("password");
                    String pwd = request.getParameter("pwd");

                    if (!password.equals(pwd)) {
                        request.setAttribute("fname", fname);
                        request.setAttribute("lname", lname);
                        request.setAttribute("gender", gender);
                        request.setAttribute("birthday", birthday);
                        request.setAttribute("pwdMes", true);
                        request.setAttribute("id", adminId);
                        request.setAttribute("admin", db.getAdminDetials(adminId));
                        rd = this.getServletContext()
                                .getRequestDispatcher("/admin/userEdit.jsp");
                        rd.forward(request, response);
                        return;
                    }
                    if (password != "") {
                        db.updateAdmin(adminId, password, fname, lname, gender, birthday);
                    } else {
                        db.updateAdmin(adminId, fname, lname, gender, birthday);
                    }
                    request.setAttribute("saveMes", adminId);
                    request.setAttribute("adminList", db.getAdmin());
                    
                } else if (adminId != null) {
                    System.out.println("TEST Point6");
                    request.setAttribute("admin", db.getAdminDetials(adminId));
                    rd = this.getServletContext()
                            .getRequestDispatcher("/admin/userEdit.jsp");
                    rd.forward(request, response);
                }
            }
            System.out.println("TEST Point10");
            rd = this.getServletContext()
                    .getRequestDispatcher("/admin/user.jsp");
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
