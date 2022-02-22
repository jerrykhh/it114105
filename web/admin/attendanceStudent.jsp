<%-- 
    Document   : dashboard
    Created on : 2019/11/11, 下午 10:08:39
    Author     : JerryKwok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="system.bean.ClassBean, system.bean.AttendBean, java.util.ArrayList, java.util.Date, java.text.SimpleDateFormat"%>
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
        <link  href="../css/bootstrap-datepicker.min.css" rel="stylesheet">
        <link href="../css/style.css" rel="stylesheet">
    </head>
    <%!
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    %>
    <%
        String urlForm = "attendance?";
        String urlDate = request.getParameter("date");
        String urlClass = request.getParameter("class");
        if (urlDate != null) {
            urlForm += ("date=" + urlDate + "&");
        }
        if (urlDate != null) {
            urlForm += ("class=" + urlClass);
        }
    %>
    <body>
        <jsp:include page="../header.jsp"></jsp:include>
            <section id="main">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3">
                        <%@taglib uri="/WEB-INF/tlds/nav-taglib.tld" prefix="nav" %>
                        <nav:showNav role="Admin" active="attendance" />
                    </div>
                    <div class="col-lg-9">
                        <%
                            if (request.getAttribute("message") != null) {
                                out.print("<div class='alert alert-success alert-dismissible fade show' role='alert'>");
                                out.print("<strong>Attendance Saved</strong>");
                                out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                out.print("<span aria-hidden='true'>&times;</span></button></div>");

                            }
                        %>
                        <!-- Main -->
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title">Select Date</h5>
                            </div>
                            <form action="attendance" method="GET">
                                <div class="card-body">
                                    <div class="form-group">
                                        <label>Class</label>
                                        <select class="form-control" name="class">
                                            <jsp:useBean id="classList" scope="request" class="java.util.ArrayList<system.bean.ClassBean>"/>
                                            <%
                                                for (ClassBean classVal : classList) {
                                                    if (request.getParameter("class").equals(classVal.getClassName())) {
                                                        out.print("<option value='" + classVal.getClassName() + "' selected>" + classVal.getClassName() + "</option>");
                                                    } else {
                                                        out.print("<option value='" + classVal.getClassName() + "'>" + classVal.getClassName() + "</option>");
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Select Date</label>
                                        <div class="input-group date">
                                            <input type="text" class="form-control" id="datepicker" name="date" value="<%= request.getParameter("date") == null ? formatter.format(date) : request.getParameter("date")%>">
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>

                                    <br>    
                                    <a href="attendance">
                                        <button type="button" class="btn btn-secondary">
                                            Back
                                        </button>
                                    </a>
                                    <button type="submit" class="btn btn-primary right">Submit</button>
                                </div>
                            </form>
                        </div>
                        <!-- Main -->
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title">Attend List</h5>
                            </div>
                            <form action="<%= urlForm%>" method="POST">
                                <div class="card-body">
                                    <div class="form-group">
                                        <label>Class</label>
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Attend</th>
                                                    <th scope="col">Student ID</th>
                                                    <th scope="col">Student Name</th>
                                                    <th scope="col">Gender</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <jsp:useBean id="studentAttendList" scope="request" class="java.util.ArrayList<system.bean.AttendBean>"/>
                                                <%
                                                    for (AttendBean student : studentAttendList) {
                                                        out.print("<tr>");
                                                        out.print("<td>");
                                                        out.print("<input type='checkbox' name='attend' value='" + student.getStBean().getId() + "'");
                                                        if (student.isAttend()) {
                                                            out.print(" checked>");
                                                        } else {
                                                            out.print(" >");
                                                        }
                                                        out.print("</td>");
                                                        out.print("<td>" + student.getStBean().getId() + "</td>");
                                                        out.print("<td>" + student.getStBean().getName() + "</td>");
                                                        out.print("<td>" + student.getStBean().getGender() + "</td>");
                                                        out.print("</tr>");
                                                    }
                                                %>
                                            </tbody>
                                        </table>

                                    </div>

                                    <button type="submit" class="btn btn-primary float-right">Save</button>
                                    <br><br>
                                    <input type="hidden" name="action" value="save">
                                    <input type="hidden" name="class" id="attendDate" value="<%= request.getParameter("class")%>">
                                    <input type="hidden" name="date" id="class" value=" <%= request.getParameter("date") == null ? formatter.format(date) : request.getParameter("date")%>">
                                </div>
                            </form>
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
                    daysOfWeekDisabled: "0,6",
                    daysOfWeekHighlighted: "1,2,3,4,5",
                    todayHighlight: true
                });
            });
        </script>
    </body>

</html>

