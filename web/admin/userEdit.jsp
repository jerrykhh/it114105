<%-- 
    Document   : dashboard
    Created on : 2019/11/11, 下午 10:08:39
    Author     : JerryKwok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="system.bean.StudentBean, system.bean.ClassBean, java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin Area | Dashboard</title>
        <!-- Bootstrap core CSS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
              rel="stylesheet">
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/style.css" rel="stylesheet">
        <link  href="../css/bootstrap-datepicker.min.css" rel="stylesheet">

    </head>
    <body>
        <jsp:include page="../header.jsp"></jsp:include>
            <section id="main">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="list-group"> 
                                <a href="dashboard" class="list-group-item main-color-bg-nav">
                                    <i class="material-icons">dashboard</i> 
                                    <span>Dashboard</span>
                                </a>
                                <a href="class" class="list-group-item">
                                    <i class="material-icons">class</i><span> Class</span>
                                </a>
                                <a href="lecture" class="list-group-item main-color-bg-nav">
                                    <i class="material-icons">view_module</i> 
                                    <span>Lecture</span>
                                </a>
                            <%
                                if (request.getParameter("role").equals("Student")) {
                                    out.println("<a href=\"user?role=Student\" class=\"list-group-item active\">");
                                    out.print("<i class=\"material-icons\">library_books</i><span> Student</span>");
                                    out.print("</a>");
                                } else {
                                    out.println("<a href=\"user?role=Student\" class=\"list-group-item\">");
                                    out.print("<i class=\"material-icons\">library_books</i><span> Student</span>");
                                    out.print("</a>");
                                }

                                if (request.getParameter("role").equals("Teacher")) {
                                    out.println("<a href=\"user?role=Teacher\" class=\"list-group-item active\">");
                                    out.print("<i class=\"material-icons\">supervisor_account</i><span> Teacher</span>");
                                    out.print("</a>");
                                } else {
                                    out.println("<a href=\"user?role=Teacher\" class=\"list-group-item\">");
                                    out.print("<i class=\"material-icons\">supervisor_account</i><span> Teacher</span>");
                                    out.print("</a>");
                                }

                                if (request.getParameter("role").equals("Admin")) {
                                    out.println("<a href=\"user?role=Admin\" class=\"list-group-item active\">");
                                    out.print("<i class=\"material-icons\">vpn_key</i><span> Admin</span>");
                                    out.print("</a>");
                                } else {
                                    out.println("<a href=\"user?role=Admin\" class=\"list-group-item\">");
                                    out.print("<i class=\"material-icons\">vpn_key</i><span> Admin</span>");
                                    out.print("</a>");
                                }
                            %>
                            <a href="schedule" class="list-group-item">
                                <i class="material-icons">schedule</i><span> Schedule</span>
                            </a>

                            <a href="attendance" class="list-group-item">
                                <i class="material-icons">check_box</i
                                ><span> Attendace</span>
                            </a>
                            <a href="report" class="list-group-item">
                                <i class="material-icons">insert_drive_file</i>
                                <span> Reports</span>
                            </a>
                            <a href="../login?action=logout" class="list-group-item text-right">
                                <span>  Logout</span> 
                            </a>
                        </div>
                    </div>

                    <div class="col-lg-9">
                        <!-- Website Overview -->
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title">Edit Student Information</h5>
                            </div>
                            <div class="card-body">
                                <%
                                    if (request.getAttribute("pwdMes") != null) {
                                        out.print("<div class='alert alert-danger alert-dismissible fade show' role='alert'>");
                                        out.print("<strong>Password and Confirm password is not match</strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                %>
                                <br>
                                <%
                                    String gender = null;
                                    String fname = null;
                                    String lname = null;
                                    String id = null;
                                    String email = null;
                                    String birthday = null;
                                    String role = null;
                                    if (request.getAttribute("student") != null) {
                                %>
                                <jsp:useBean id="student" scope="request" class="system.bean.StudentBean"/>
                                <%
                                    id = student.getId();
                                    fname = student.getFname();
                                    lname = student.getLname();
                                    email = student.getEmail();
                                    birthday = student.getBirthday();
                                    gender = student.getGender();
                                    role = "Student";
                                } else if (request.getAttribute("teacher") != null) {%>
                                <jsp:useBean id="teacher" scope="request" class="system.bean.TeacherBean"/>
                                <%
                                    id = teacher.getId();
                                    System.out.println(id);
                                    fname = teacher.getFname();
                                    lname = teacher.getLname();
                                    email = teacher.getEmail();
                                    birthday = teacher.getBirthday();
                                    gender = teacher.getGender();
                                    role = "Teacher";
                                } else if (request.getAttribute("admin") != null) {%>
                                <jsp:useBean id="admin" scope="request" class="system.bean.AdminBean"/>
                                <%
                                        id = admin.getId();
                                        fname = admin.getFname();
                                        lname = admin.getLname();
                                        email = admin.getEmail();
                                        birthday = admin.getBirthday();
                                        gender = admin.getGender();
                                        role = "Admin";
                                    }%>
                                <form action="user?role=<%=role%>&id=<%= (request.getAttribute("id") != null) ? request.getAttribute("id") : request.getParameter("id")%>" method="POST">
                                    <table class="table">
                                        <tr>
                                            <th><%= role%> ID</th>
                                            <td><%= request.getParameter("id")%></td>
                                        </tr>
                                        <tr>
                                            <th><%= role%>  Email</th>
                                            <td><%= request.getParameter("email") != null ? request.getParameter("email") : email%></td>
                                        </tr>
                                        <tr>
                                            <th><%= role%>  First Name</th>
                                            <td><input name="fname" class="form-control" value="<%= request.getParameter("fname") != null ? request.getParameter("fname") : fname%>" required></td>
                                        </tr>
                                        <tr>
                                            <th><%= role%>  Last Name</th>
                                            <td><input name="lname" class="form-control" value="<%= request.getParameter("lname") != null ? request.getParameter("lname") : lname%>" required></td>
                                        </tr>
                                        <tr>
                                            <th><%= role%>  Gender</th>
                                            <td><select name="gender" class="form-control" required>
                                                    <%
                                                        System.out.println("2");
                                                        if (request.getParameter("gender") != null) {
                                                            if (request.getParameter("gender").equals("M")) {
                                                                out.print("<option value='M' selected>Male</option>");
                                                                out.print("<option value='F'>Female</option>");
                                                            } else {
                                                                out.print("<option value='F'selected>Female</option>");
                                                                out.print("<option value='M'>Male</option>");
                                                            }
                                                        } else {

                                                            System.out.println("3");
                                                            if (gender.equals("M")) {
                                                                out.print("<option value='M' selected>Male</option>");
                                                                out.print("<option value='F'>Female</option>");
                                                            } else {
                                                                out.print("<option value='F'>Female</option>");
                                                                out.print("<option value='M'>Male</option>");
                                                            }
                                                        }
                                                        System.out.println("test1");
                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Birthday</th>
                                            <td>
                                                <div class="form-group">
                                                    <div class="input-group date">
                                                        <input type="text" class="form-control" id="datepicker" name="birthday" value="<%= request.getParameter("birthday") != null ? request.getParameter("birthday") : birthday%>">
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Change Password</th>
                                            <td><input type='text' class="form-control" name="password" value=""></td>
                                        </tr>
                                        <tr>
                                            <th>Confirm Change Password</th>
                                            <td><input type="text" class="form-control" name="pwd" value=""></td>
                                        </tr>
                                    </table>
                                    <button type="btn" class="btn btn-primary">Saved</button>
                                    <input type="hidden" name="id" value="<%= id%>">

                                    <input type="hidden" name="email" value="<%= email%>">
                                    <input type="hidden" name="action" value="save">
                                </form>
                            </div>
                        </div>
                        <a href="user?role=<%=role%>"><button type="button" class="btn btn-secondary">Back</button></a>
                        <!-- Latest Users -->
                    </div>
                </div>
            </div>
        </section>
        <br><br>
        <footer></footer>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap-datepicker.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#datepicker').datepicker({
                    format: "yyyy-mm-dd",
                    daysOfWeekHighlighted: "1,2,3,4,5",
                    todayHighlight: true
                });
                $('.alert').alert();
            });
        </script>
    </body>

</html>

