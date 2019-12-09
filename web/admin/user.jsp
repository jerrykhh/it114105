<%-- 
    Document   : dashboard
    Created on : 2019/11/11, 下午 10:08:39
    Author     : JerryKwok
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="system.bean.StudentBean, system.bean.AdminBean, system.bean.TeacherBean"%>
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
                                <a href="lecture" class="list-group-item main-color-bg-nav">
                                    <i class="material-icons">view_module</i> 
                                    <span>Lecture</span>
                                </a>
                                <a href="class" class="list-group-item ">
                                    <i class="material-icons">class</i><span> Class</span>
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
                                <h5 class="card-title"><%= request.getParameter("role")%> List</h5>
                            </div>
                            <div class="card-body">
                                <%
                                    if (request.getAttribute("saveMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong>" + request.getParameter("role") + " #" + request.getAttribute("pwdTeaMes") + " Infromation Saved </strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }

                                    if (request.getAttribute("addMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong>" + request.getParameter("role") + " #" + request.getAttribute("addMes") + " Added </strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }

                                    if (request.getAttribute("deleteMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong>" + request.getParameter("role") + " #" + request.getAttribute("deleteMes") + " Deleted </strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                %>
                                <div class="format-table">
                                    <form action="user?role=<%= request.getParameter("role")%>" method="POST" class="format">
                                        <button type="submit" class="btn btn-primary">Add User</button>
                                        <input type="hidden" name="action" value="addPage">
                                    </form>
                                    <button type="button" class="btn btn-info tableBtn" id="btnExportExcel"><i class="material-icons">save_alt</i></button>
                                    <form class="form-inline float-right" action="user?role=<%= request.getParameter("role")%>" method="POST">
                                        <div class="form-group mb-2">
                                            <font>Search: </font>
                                        </div>
                                        <div class="form-group mx-sm-3 mb-2">
                                            <label class="sr-only">Password</label>
                                            <input type="text" class="form-control" name="searchVal" id="searchVal" placeholder="Search...">
                                        </div>
                                        <button type="submit" class="btn btn-dark mb-2">Search</button>
                                        <input type="hidden" name="action" value="search">
                                    </form>
                                    <table class="table exportExcel">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Gender</th>
                                                <th>Birthday</th>
                                                <th>Email</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <%
                                            if (request.getAttribute("studentList") != null) {
                                        %>
                                        <jsp:useBean id="studentList" scope="request" class="ArrayList<system.bean.StudentBean>"/>
                                        <%
                                            if (studentList.size() == 0 || studentList == null) {
                                                out.print("<tr>");
                                                out.print("<td colspon='6'>Not Match Result</td>");
                                                out.print("<tr>");
                                            } else {
                                                for (StudentBean student : studentList) {
                                                    out.print("<tr>");
                                                    out.print("<td>" + student.getId() + "</td>");
                                                    out.print("<td>" + student.getName() + "</td>");
                                                    out.print("<td>" + student.getGender() + "</td>");
                                                    out.print("<td>" + student.getBirthday() + "</td>");
                                                    out.print("<td>" + student.getEmail() + "</td>");
                                                    out.print("<td>");
                                                    out.print("<a href='user?role=Student&id=" + student.getId() + "'><button type='button' class='btn btn-info tableBtn'><i class='material-icons'>edit</i></button></a> ");
                                                    out.print("<a href='user?role=Student&id=" + student.getId() + "&action=delete'><button type='button' class='btn btn-danger tableBtn'><i class='material-icons'>delete</i></button></a>");
                                                    out.print("</td>");
                                                    out.print("</tr>");
                                                }
                                            }
                                            out.print("</table>");
                                            if (studentList.size() == 0) {
                                                out.print("<br><br><br><br><br>");
                                                out.print("<a href='user?role=" + request.getParameter("role") + "'><button type='button' class='btn btn-secondary'>Show All</button></a>");
                                            }

                                        } else if (request.getAttribute("teacherList") != null) {
                                        %>
                                        <jsp:useBean id="teacherList" scope="request" class="ArrayList<system.bean.TeacherBean>"/>
                                        <%
                                            if (teacherList.size() == 0 || teacherList == null) {
                                                out.print("<tr>");
                                                out.print("<td colspon='6'>Not Match Result</td>");
                                                out.print("<tr>");
                                            } else {
                                                for (TeacherBean teacher : teacherList) {
                                                    out.print("<tr>");
                                                    out.print("<td>" + teacher.getId() + "</td>");
                                                    out.print("<td>" + teacher.getName() + "</td>");
                                                    out.print("<td>" + teacher.getGender() + "</td>");
                                                    out.print("<td>" + teacher.getBirthday() + "</td>");
                                                    out.print("<td>" + teacher.getEmail() + "</td>");
                                                    out.print("<td>");
                                                    out.print("<a href='user?role=Teacher&id=" + teacher.getId() + "'><button type='button' class='btn btn-info tableBtn'><i class='material-icons'>edit</i></button></a> ");
                                                    out.print("<a href='user?role=Teacher&id=" + teacher.getId() + "&action=delete'><button type='button' class='btn btn-danger tableBtn'><i class='material-icons'>delete</i></button></a> ");
                                                    out.print(" <a href='user?role=Teacher&id=" + teacher.getId() + "&action=admin'><button type='button' class='btn btn-warning tableBtn'><i class='material-icons'>import_export</i></button></a>");
                                                    out.print("</td>");
                                                    out.print("</tr>");
                                                }
                                            }
                                            out.print("</table>");
                                            if (teacherList.size() == 0) {
                                                out.print("<br><br><br><br><br>");
                                                out.print("<a href='user?role=" + request.getParameter("role") + "'><button type='button' class='btn btn-secondary'>Show All</button></a>");
                                            }
                                        } else if (request.getAttribute("adminList") != null) {%>
                                        <jsp:useBean id="adminList" scope="request" class="ArrayList<system.bean.AdminBean>"/>
                                        <%  if (adminList.size() == 0 || adminList == null) {
                                                    out.print("<tr>");
                                                    out.print("<td colspon='6'>Not Match Result</td>");
                                                    out.print("<tr>");
                                                } else {
                                                    for (AdminBean admin : adminList) {
                                                        out.print("<tr>");
                                                        out.print("<td>" + admin.getId() + "</td>");
                                                        out.print("<td>" + admin.getName() + "</td>");
                                                        out.print("<td>" + admin.getGender() + "</td>");
                                                        out.print("<td>" + admin.getBirthday() + "</td>");
                                                        out.print("<td>" + admin.getEmail() + "</td>");
                                                        out.print("<td>");
                                                        out.print("<a href='user?role=Admin&id=" + admin.getId() + "'><button type='button' class='btn btn-info tableBtn'><i class='material-icons'>edit</i></button></a> ");
                                                        out.print("<a href='user?role=Admin&id=" + admin.getId() + "&action=delete'><button type='button' class='btn btn-danger tableBtn'><i class='material-icons'>delete</i></button></a> ");
                                                        out.print(" <a href='user?role=Teacher&id=" + admin.getId() + "&action=teacher'><button type='button' class='btn btn-warning tableBtn'><i class='material-icons'>import_export</i></button></a>");
                                                        out.print("</td>");
                                                        out.print("</tr>");
                                                    }
                                                }
                                                out.print("</table>");
                                                if (adminList.size() == 0) {
                                                    out.print("<br><br><br><br><br>");
                                                    out.print("<a href='user?role=" + request.getParameter("role") + "'><button type='button' class='btn btn-secondary'>Show All</button></a>");
                                                }

                                            }%>
                                </div>
                            </div>
                        </div>
                        <!-- Latest Users -->
                    </div>
                </div>
            </div>
        </section>
        <br><br>
        <footer></footer>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="../js/jquery.tableToexcel.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $('.alert').alert();
                $("#btnExportExcel").click(function () {
                    $(".exportExcel").table2excel({
                        exclude: ".excludeThisClass",
                        name: "Worksheet Name",
                        filename: "<%= request.getParameter("role") + "_Report"%>.xls", // do include extension
                        preserveColors: false // set to true if you want background colors and font colors preserved
                    });
                });
            });
        </script>
    </body>

</html>

