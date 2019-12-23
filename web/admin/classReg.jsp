<%-- 
    Document   : dashboard
    Created on : 2019/11/11, 下午 10:08:39
    Author     : JerryKwok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="system.bean.SearchBean, system.bean.StudentBean, system.bean.ClassBean, system.bean.TeacherBean"%>
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
                        <nav:showNav role="Admin" active="ClassReg" />
                        </div>

                        <div class="col-lg-9">
                            <!-- Website Overview -->
                            <div class="card">
                                <div class="card-header main-color-bg">
                                    <h5 class="card-title">Student Registered Class</h5>
                                </div>
                                <div class="card-body">
                                <%
                                    if (request.getAttribute("deletenMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong>Student #" + request.getAttribute("deletenMes") + " Class Deleted </strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    } else if (request.getAttribute("saveMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong>Student Class #" + request.getAttribute("saveMes") + " Saved </strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    } else if (request.getAttribute("addMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong> Added the student to Class #" + request.getAttribute("addMes") + "</strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                %>
                                <div class="format-table">
                                    <form action="classReg" method="POST" class="format">
                                        <button type="submit" class="btn btn-primary">Add Student to Class</button>
                                        <input type="hidden" name="action" value="addPage">
                                    </form>
                                    <button type="button" class="btn btn-info tableBtn" id="btnExportExcel"><i class="material-icons">save_alt</i></button>
                                    <form class="form-inline float-right" action="classReg" method="POST">
                                        <div class="form-group mb-2">
                                            <font>Search: </font>
                                        </div>
                                        <div class="form-group mx-sm-3 mb-2">
                                            <label class="sr-only">Password</label>
                                            <input list="searchlist" type="text" class="form-control" name="searchVal" id="searchVal" placeholder="Search...">
                                            <datalist id="searchlist">
                                                <jsp:useBean scope="request" id="searchList" class="java.util.ArrayList<system.bean.SearchBean>"/>
                                                <%
                                                    for (SearchBean search : searchList) {
                                                        out.print("<option label='" + search.getKeyword() + "' value='" + search.getKeyword() + "' />");
                                                    }
                                                %>
                                            </datalist>
                                        </div>
                                        <button type="submit" class="btn btn-dark mb-2">Search</button>
                                        <input type="hidden" name="action" value="search">
                                    </form>
                                    <table class="table exportExcel">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>ID</th>
                                                <th>Student Name</th>
                                                <th>Class</th>
                                                <th>Head Teacher</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <jsp:useBean id="studentClassList" scope="request" class="java.util.ArrayList<system.bean.StudentBean>"/>
                                        <%
                                            if (studentClassList.size() > 0) {
                                                for (StudentBean student : studentClassList) {
                                                    out.print("<tr>");
                                                    out.print("<td><a href='user?role=Student&searchVal=" + student.getId() + "&action=search'>" + student.getId() + "</a></td>");
                                                    out.print("<td>" + student.getName() + "</td>");
                                                    out.print("<td>" + student.getClassName().getClassName() + "</td>");
                                                    out.print("<td><a href='user?role=Teacher&searchVal=" + student.getClassName().getTeacherBean().getId() + "&action=search'>" + student.getClassName().getTeacherBean().getTeacherFormalName() + "</a></td>");
                                                    out.print("<td>");
                                                    out.print("<a href='classReg?id=" + student.getStudentClassid() + "'><button type='button' class='btn btn-info tableBtn'><i class='material-icons'>edit</i></button></a> ");
                                                    out.print("<a href='classReg?id=" + student.getStudentClassid() + "&action=delete'><button type='button' class='btn btn-danger tableBtn'><i class='material-icons'>delete</i></button></a>");
                                                    out.print("</td>");
                                                    out.print("</tr>");
                                                }
                                                out.print("</table>");
                                                if (request.getAttribute("searchHis") != null) {
                                                    out.print("<br><br><br><br><br>");
                                                    out.print("<a href='classReg'><button type='button' class='btn btn-secondary'>Show All</button></a>");
                                                }
                                            } else {
                                                out.print("<tr>");
                                                out.print("<td colspon=5>Not Match Result</td>");
                                                out.print("</tr>");
                                                out.print("</table>");
                                                out.print("<br><br><br><br><br>");
                                                out.print("<a href='classReg'><button type='button' class='btn btn-secondary'>Show All</button></a>");
                                            }
                                        %>
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
                        filename: "Class_Report.xls", // do include extension
                        preserveColors: false // set to true if you want background colors and font colors preserved
                    });
                });
            });
        </script>
    </body>

</html>

