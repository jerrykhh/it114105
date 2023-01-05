<%-- 
    Document   : dashboard
    Created on : 2019/11/11, 下午 10:08:39
    Author     : JerryKwok
--%>

<%@page import="system.bean.ClassYearBean"%>
<%@page import="system.bean.TeacherBean"%>
<%@page import="system.bean.LectureDayBean"%>
<%@page import="system.bean.LectureTimeBean"%>
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

    </head>
    <body>
        <jsp:include page="../header.jsp"></jsp:include>
            <section id="main">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3">
                        <%@taglib uri="/WEB-INF/tlds/nav-taglib.tld" prefix="nav" %>
                        <nav:showNav role="Admin" active="class" />
                    </div>

                    <div class="col-lg-9">
                        <!-- Website Overview -->
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title">Edit Student Class</h5>
                            </div>
                            <div class="card-body">
                                <%
                                    if (request.getAttribute("dupMes") != null) {
                                        out.print("<div class='alert alert-danger alert-dismissible fade show' role='alert'>");
                                        out.print("<strong>The Class Name is Created</strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                %>
                                <br>
                                <jsp:useBean id="classBean" scope="request" class="system.bean.ClassBean" />
                                <jsp:useBean id="classYearList" scope="request" class="ArrayList<system.bean.ClassYearBean>" />
                                <jsp:useBean id="teacherList" scope="request" class="ArrayList<system.bean.TeacherBean>" />
                                <form action="class?id=<%= request.getParameter("id") %>" method="POST">
                                    <table class="table">
                                        <tr>
                                            <th>Class Name</th>
                                            <td><input type="text" name="class" class="form-control" value="<%= request.getAttribute("className") != null ? request.getAttribute("className") : classBean.getClassName()%>" required></td>
                                        </tr> 
                                        <tr>
                                            <th>Teacher</th>
                                            <td>
                                                <select name="teacherId"  class="form-control" required="">
                                                    <%
                                                        if (request.getAttribute("teacherId") != null) {
                                                            for (TeacherBean teacher : teacherList) {
                                                                if (teacher.getId().equals(request.getAttribute("teacherId"))) {
                                                                    out.print("<option value='" + teacher.getId() + "' selected>" + teacher.getTeacherFormalName() + "</option>");
                                                                } else {
                                                                    out.print("<option value='" + teacher.getId() + "'>" + teacher.getTeacherFormalName() + "</option>");
                                                                }
                                                            }

                                                        } else {
                                                            out.print("<option value='" + classBean.getTeacherBean().getId() + "' selected>" + classBean.getTeacherBean().getTeacherFormalName() + "</option>");
                                                            for (TeacherBean teacher : teacherList) {
                                                                out.print("<option value='" + teacher.getId() + "'>" + teacher.getTeacherFormalName() + "</option>");
                                                            }

                                                        }


                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Year</th>
                                            <td>
                                                <select name="yearId"  class="form-control" required>
                                                    <%                                                        if (request.getAttribute("yearId") != null) {
                                                            for (ClassYearBean year : classYearList) {
                                                                if (year.getId().equals(request.getAttribute("yearId"))) {
                                                                    out.print("<option value='" + year.getId() + "' selected>" + year.getYear() + "</option>");
                                                                } else {
                                                                    out.print("<option value='" + year.getId() + "'>" + year.getYear() + "</option>");
                                                                }
                                                            }
                                                        } else {
                                                            for (ClassYearBean year : classYearList) {
                                                                if (classBean.getYear().equals(year.getYear())) {
                                                                    out.print("<option value='" + year.getId() + "' selected>" + year.getYear() + "</option>");
                                                                } else {
                                                                    out.print("<option value='" + year.getId() + "'>" + year.getYear() + "</option>");
                                                                }

                                                            }
                                                        }

                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                    <button type="submit" class="btn btn-primary">Save Lecture</button>
                                    <input type="hidden" name="action" value="save">
                                    <input type="hidden" name="id" value="<%= request.getParameter("id")%>"
                                </form>
                            </div>
                        </div>
                        <a href="class">
                            <button class="btn btn-secondary">
                                Back
                            </button>
                        </a>
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
        <script>
            $(document).ready(function () {
                $('.alert').alert();
            });
        </script>
    </body>

</html>

