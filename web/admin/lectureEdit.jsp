<%-- 
    Document   : dashboard
    Created on : 2019/11/11, 下午 10:08:39
    Author     : JerryKwok
--%>

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
                            <div class="list-group"> 
                                <a href="dashboard" class="list-group-item main-color-bg-nav">
                                    <i class="material-icons">dashboard</i> 
                                    <span>Dashboard</span>
                                </a>
                                <a href="lecture" class="list-group-item active main-color-bg-nav">
                                    <i class="material-icons">view_module</i> 
                                    <span>Lecture</span>
                                </a>
                                <a href="class" class="list-group-item">
                                    <i class="material-icons">class</i><span> Class</span>
                                </a>
                                <a href="user?role=Student" class="list-group-item">
                                    <i class="material-icons">library_books</i><span> Student</span>
                                </a>
                                <a href="user?role=Teacher" class="list-group-item">
                                    <i class="material-icons">supervisor_account</i><span> Teacher</span>
                                </a>
                                <a href="user?role=Admin" class="list-group-item">
                                    <i class="material-icons">vpn_key</i><span> Admin</span>
                                </a>
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
                                    <h5 class="card-title">Edit Student Class</h5>
                                </div>
                                <div class="card-body">
                                <%
                                    if (request.getAttribute("dupMes") != null) {
                                        out.print("<div class='alert alert-danger alert-dismissible fade show' role='alert'>");
                                        out.print("<strong>Teacher must attend other class</strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                %>
                                <br>
                                <jsp:useBean id="lectureBean" scope="request" class="system.bean.LectureBean"/>
                                <jsp:useBean id="classList" scope="request" class="ArrayList<system.bean.ClassBean>"/>
                                <jsp:useBean id="dayList" scope="request" class="ArrayList<system.bean.LectureDayBean>"/>
                                <jsp:useBean id="timeList" scope="request" class="ArrayList<system.bean.LectureTimeBean>" />
                                <jsp:useBean id="teacherList" scope="request" class="ArrayList<system.bean.TeacherBean>" />
                                <form action="lecture?id=<%= request.getParameter("id")%>" method="POST">
                                    <table class="table">
                                        <tr>
                                            <th>Lecture ID</th>
                                            <td>${lectureId}</td>
                                        </tr>
                                        <tr>
                                            <th>Lecture</th>
                                            <td><input type="text" name="lecture" class="form-control" value="<%= request.getAttribute("lecture") != null ? request.getAttribute("lecture") : lectureBean.getLecture()%>" required></td>
                                        </tr> 
                                        <tr>
                                            <th>Description</th>
                                            <td><textarea class="form-control" name="description"><%= request.getAttribute("description") != null ? request.getAttribute("description") : lectureBean.getDescription()%></textarea></td>
                                        </tr>
                                        <tr>
                                            <th>Class</th>
                                            <td>
                                                <select name="className" required>
                                                    <%
                                                        if (request.getAttribute("className") != null) {
                                                            for (ClassBean className : classList) {
                                                                if (className.getId().equals(request.getAttribute("className"))) {
                                                                    out.print("<option value='" + className.getId() + "' selected>" + className.getClassName() + "</option>");
                                                                } else {
                                                                    out.print("<option value='" + className.getId() + "'>" + className.getClassName() + "</option>");
                                                                }
                                                            }
                                                        } else {
                                                            for (ClassBean className : classList) {
                                                                if (className.getClassName().equals(lectureBean.getClassName())) {
                                                                    out.print("<option value='" + className.getId() + "' selected>" + className.getClassName() + "</option>");
                                                                } else {
                                                                    out.print("<option value='" + className.getId() + "'>" + className.getClassName() + "</option>");
                                                                }
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Time</th>
                                            <td>
                                                <select name="time" required>
                                                    <%
                                                        if (request.getAttribute("time") != null) {
                                                            for (LectureTimeBean time : timeList) {
                                                                if (time.getId().equals(request.getAttribute("time"))) {
                                                                    out.print("<option value='" + time.getId() + "' selected>" + time.getStartTime() + "-" + time.getEndTime() + "</option>");
                                                                } else {
                                                                    out.print("<option value='" + time.getId() + "'>" + time.getStartTime() + "-" + time.getEndTime() + "</option>");
                                                                }
                                                            }
                                                        } else {
                                                            for (LectureTimeBean time : timeList) {
                                                                if (time.getStartTime().equals(lectureBean.getTime().getStartTime()) && time.getEndTime().equals(lectureBean.getTime().getEndTime())) {
                                                                    out.print("<option value='" + time.getId() + "' selected>" + time.getStartTime() + "-" + time.getEndTime() + "</option>");
                                                                } else {
                                                                    out.print("<option value='" + time.getId() + "'>" + time.getStartTime() + "-" + time.getEndTime() + "</option>");
                                                                }
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Day</th>
                                            <td>
                                                <select name="day" required>
                                                    <%
                                                        if (request.getAttribute("day") != null) {
                                                            for (LectureDayBean day : dayList) {
                                                                if (day.getId().equals(request.getAttribute("day"))) {
                                                                    out.print("<option value='" + day.getId() + "' selected>" + day.getDay() + "</option>");
                                                                } else {
                                                                    out.print("<option value='" + day.getId() + "'>" + day.getDay() + "</option>");
                                                                }
                                                            }
                                                        } else {
                                                            for (LectureDayBean day : dayList) {
                                                                if (day.getDay().equals(lectureBean.getDay().getDay())) {
                                                                    out.print("<option value='" + day.getId() + "' selected>" + day.getDay() + "</option>");
                                                                } else {
                                                                    out.print("<option value='" + day.getId() + "'>" + day.getDay() + "</option>");
                                                                }
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Teacher</th>
                                            <td>
                                                <select name="teacher" >
                                                    <%
                                                        out.print("<option value=''> </option>");
                                                        if (request.getAttribute("teacher") != null) {
                                                            for (TeacherBean teacher : teacherList) {
                                                                if (teacher.getId().equals(request.getAttribute("teacher"))) {
                                                                    out.print("<option value='" + teacher.getId() + "' selected>" + teacher.getTeacherFormalName() + "</option>");
                                                                } else {
                                                                    out.print("<option value='" + teacher.getId() + "'>" + teacher.getTeacherFormalName() + "</option>");
                                                                }
                                                            }

                                                        } else {
                                                            for (TeacherBean teacher : teacherList) {
                                                                if (teacher.getId().equals(lectureBean.getTeacherId())) {
                                                                    out.print("<option value='" + teacher.getId() + "' selected>" + teacher.getTeacherFormalName() + "</option>");
                                                                } else {
                                                                    out.print("<option value='" + teacher.getId() + "'>" + teacher.getTeacherFormalName() + "</option>");
                                                                }
                                                            }

                                                        }
                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                    <button type="submit" class="btn btn-primary">Saved</button>
                                    <input type="hidden" name="id" value="${lectureId}">
                                    <input type="hidden" name="action" value="save">
                                </form>
                            </div>
                        </div>
                        <a href="report">
                            <button type="lecture" class="btn btn-secondary">
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

