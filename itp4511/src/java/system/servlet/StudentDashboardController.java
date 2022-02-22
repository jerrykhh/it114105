
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
import system.db.StudentDB;


@WebServlet(name = "StudentDashboardController", urlPatterns = {"/student/dashboard"})
public class StudentDashboardController extends HttpServlet {

    private StudentDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new StudentDB(dbUrl, dbUser, dbPassword);
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session.getAttribute("username") == null) {
                response.sendRedirect("../index");
                return;
            }
            String studentId = (String) session.getAttribute("username");
            db.setStudentId(studentId);
            request.setAttribute("courseCount", db.getCountClass());
            request.setAttribute("studentCount", db.getCountStudent());
            request.setAttribute("lectureList", db.getLecture(studentId));
            request.setAttribute("timeList", db.getAllTimeLecture());
            request.setAttribute("dayList", db.getAllDayLecture());
            RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/student/dashboard.jsp");
            rd.forward(request, response);
        } catch (NullPointerException e) {
            response.sendRedirect("../index");
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }

}
