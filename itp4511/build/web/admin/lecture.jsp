<%-- 
    Document   : dashboard
    Created on : 2019/11/11, 下午 10:08:39
    Author     : JerryKwok
--%>

<%@page import="system.bean.SearchBean"%>
<%@page import="system.bean.LectureBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="system.bean.StudentBean, system.bean.ClassBean, system.bean.TeacherBean"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin Area | Dashboard</title>
        <!-- Bootstrap core CSS -->
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
                        <nav:showNav role="Admin" active="lecture" />
                        </div>

                        <div class="col-lg-9">
                            <!-- Website Overview -->
                            <div class="card">
                                <div class="card-header main-color-bg">
                                    <h5 class="card-title">Lecture</h5>
                                </div>
                                <div class="card-body">
                                <%
                                    if (request.getAttribute("deleteMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong>Lecture #" + request.getAttribute("deleteMes") + " Deleted </strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    } else if (request.getAttribute("saveMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong>Lecture #" + request.getAttribute("saveMes") + " Saved </strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    } else if (request.getAttribute("addMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong> Lecture Added</strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                %>
                                <div class="format-table">
                                    <form action="lecture" method="POST" class="format">
                                        <button type="submit" class="btn btn-primary">Create Lecture</button>
                                        <input type="hidden" name="action" value="addPage">
                                    </form>
                                    <form class="form-inline float-right" action="lecture" method="POST">
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
                                            <div class="table-responsive">
                                    <table class="table">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Lecture</th>
                                                <th>Description</th>
                                                <th>Time</th>
                                                <th>Class</th>
                                                <th>Day</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <jsp:useBean id="lectureList" scope="request" class="java.util.ArrayList<system.bean.LectureBean>"/>
                                        <%                                            if (lectureList.size() == 0) {
                                                out.print("<tr>");
                                                out.print("<td colspon=5>Not Match Result</td>");
                                                out.print("</tr>");
                                            } else {
                                                for (LectureBean lecture : lectureList) {
                                                    out.print("<tr>");
                                                    out.print("<td>" + lecture.getLecture() + "</td>");
                                                    out.print("<td>" + lecture.getDescription() + "</td>");
                                                    out.print("<td>" + lecture.getTime().getStartTime() + " - " + lecture.getTime().getEndTime() + "</td>");
                                                    out.print("<td>" + lecture.getClassName() + "</td>");
                                                    out.print("<td>" + lecture.getDay().getDay() + "</td>");
                                                    out.print("<td><a href='lecture?id=" + lecture.getId() + "'><button type='button' class='btn btn-info tableBtn'><i class='material-icons'>edit</i></button></a>");
                                                    out.print("<a href='lecture?id=" + lecture.getId() + "&action=delete'><button type='button' class='btn btn-danger tableBtn'><i class='material-icons'>delete</i></button></a>");
                                                    out.print("</td></tr>");
                                                }
                                            }
                                            out.print("</table>");
                                            if (lectureList.size() == 0) {
                                                out.print("<br><br><br><br><br>");
                                                out.print("<a href='lecture'><button type='button' class='btn btn-secondary'>Show All</button></a>");
                                            }
                                        %>
                                    </table>
                                </div>
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
                <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $('.alert').alert();
            });
        </script>
    </body>

</html>

