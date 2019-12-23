/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.tag;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author JerryKwok
 */
public class NavTag extends SimpleTagSupport {

    private String role;
    private String active;

    public void setRole(String role) {
        this.role = role;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void doTag() {
        try {
            JspWriter out = getJspContext().getOut();
            out.print("<div class=\"list-group\">");
            if (role.equalsIgnoreCase("Student")) {

                out.print("<a href=\"dashboard\" class=\"list-group-item main-color-bg-nav>");
                if (active.equalsIgnoreCase("dashboard")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">dashboard</i> ");
                out.print("<span>Dashboard</span>");
                out.print("</a>");
                out.print("<a href=\"attendance\" class=\"list-group-item >");
                if (active.equalsIgnoreCase("attendance")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">check_box</i>");
                out.print("<span> Attendance</span>");
                out.print("</a>");
                out.print("<a href=\"../login?action=logout\" class=\"list-group-item text-right\">");
                out.print("<span>  Logout</span> ");
                out.print("</a>");

            } else if (role.equalsIgnoreCase("Admin")) {

                out.print("<a href=\"dashboard\" class=\"list-group-item main-color-bg-nav");
                if (active.equalsIgnoreCase("dashboard")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">dashboard</i> ");
                out.print("<span>Dashboard</span>");
                out.print("</a>");
                out.print("<a href=\"lecture\" class=\"list-group-item main-color-bg-nav");
                if (active.equalsIgnoreCase("Lecture")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">view_module</i> ");
                out.print("<span>Lecture</span>");
                out.print("</a>");
                out.print("<a href=\"class\" class=\"list-group-item");
                if (active.equalsIgnoreCase("Class")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">class</i><span>Class</span>");
                out.print("</a>");
                out.print("<a href=\"classReg\" class=\"list-group-item");
                if (active.equalsIgnoreCase("ClassReg")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">account_tree</i><span> Class Register</span>");
                out.print("</a>");
                out.print("<a href=\"user?role=Student\" class=\"list-group-item");
                if (active.equalsIgnoreCase("Student")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">library_books</i><span> Student</span>");
                out.print("</a>");
                out.print("<a href=\"user?role=Teacher\" class=\"list-group-item");
                if (active.equalsIgnoreCase("Teacher")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">supervisor_account</i><span> Teacher</span>");
                out.print("</a>");
                out.print("<a href=\"user?role=Admin\" class=\"list-group-item");
                if (active.equalsIgnoreCase("Admin")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">vpn_key</i> <span> Admin</span>");
                out.print("</a>");
                out.print("<a href=\"schedule\" class=\"list-group-item");
                if (active.equalsIgnoreCase("Schdeule")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">schedule</i><span> Schedule</span>");
                out.print("</a>");
                out.print(" <a href=\"attendance\" class=\"list-group-item");
                if (active.equalsIgnoreCase("Attendance")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">check_box</i><span> Attendance</span>");
                out.print("</a>");
                out.print("<a href=\"report\" class=\"list-group-item");
                if (active.equalsIgnoreCase("Report")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">insert_drive_file</i><span> Reports</span>");
                out.print("</a>");
                out.print("<a href=\"../login?action=logout\" class=\"list-group-item text-right\">");
                out.print("<span>  Logout</span> ");
                out.print("</a>");

            } else if (role.equalsIgnoreCase("Teacher")) {
                out.print(" <a href=\"dashboard\" class=\"list-group-item main-color-bg-nav");
                if (active.equalsIgnoreCase("dashboard")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">dashboard</i><span>Dashboard</span></a>");
                out.print(" <a href=\"attendance\" class=\"list-group-item");
                if (active.equalsIgnoreCase("Attendance")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">check_box</i><span> Attendace</span></a>");
                out.print("<a href=\"report\" class=\"list-group-item");
                if (active.equalsIgnoreCase("Report")) {
                    out.print(" active\">");
                } else {
                    out.print("\">");
                }
                out.print("<i class=\"material-icons\">insert_drive_file</i><span> Reports</span></a>");
                out.print("<a href=\"../login?action=logout\" class=\"list-group-item text-right\"><span>  Logout</span></a>");
            }
            out.print("</div>");
        } catch (IOException e) {

        }
    }
}
