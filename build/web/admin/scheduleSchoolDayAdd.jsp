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
    <%
        String role = request.getParameter("role");
    %>
    <body>
        <jsp:include page="../header.jsp"></jsp:include>
            <section id="main">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3">
                        <%@taglib uri="/WEB-INF/tlds/nav-taglib.tld" prefix="nav" %>
                        <nav:showNav role="Admin" active="Schedule" />
                    </div>

                    <div class="col-lg-9">
                        <!-- Website Overview -->
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title">Edit Student Information</h5>
                            </div>

                            <div class="card-body">
                                <%
                                    if (request.getAttribute("errMes") != null) {
                                        out.print("<div class='alert alert-danger alert-dismissible fade show' role='alert'>");
                                        out.print("<strong>School Day and Holiday can not checked in same time</strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                    if (request.getAttribute("notselectMes") != null) {
                                        out.print("<div class='alert alert-danger alert-dismissible fade show' role='alert'>");
                                        out.print("<strong>The checkbox must select at least one</strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                %>
                                <br>
                                <form action="schedule?class=schoolDay" method="POST">
                                    <table class="table">
                                        <tr>
                                            <th>Title</th>
                                            <th><textarea name="title" class="form-control" required><%= request.getAttribute("title") != null ? request.getAttribute("title") : ""%></textarea></th>
                                        </tr>
                                        <tr>
                                            <th>Start Date</th>
                                            <td>
                                                <div class="form-group">
                                                    <div class="input-group date">
                                                        <input type="date" class="form-control" id="startDatedatepicker" name="startDate" value="<%= request.getAttribute("startDate") != null ? request.getAttribute("startDate") : ""%>" required>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>End Date</th>
                                            <td>
                                                <div class="form-group">
                                                    <div class="input-group date">
                                                        <input type="date" class="form-control" id="endDatedatepicker" name="endDate" value="<%= request.getAttribute("endDate") != null ? request.getAttribute("endDate") : ""%>" required>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Holiday</th>
                                            <td><input type='checkbox' class="form-check-input" name="holiday" value="isHoliday"></td>
                                        </tr>
                                        <tr>
                                            <th>School Day</th>
                                            <td><input type="checkbox" class="form-check-input" name="schoolDay" value="isSchoolDay"></td>
                                        </tr>
                                    </table>
                                    <button type="btn" class="btn btn-primary">Add to Schedule</button>
                                    <input type="hidden" name="action" value="add">
                                </form>
                            </div>
                        </div>
                        <a href="schedule?class=schoolDay"><button type="button" class="btn btn-secondary">Back</button></a>
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
                $('#startDatedatepicker').datepicker({
                    format: "yyyy-mm-dd",
                    todayHighlight: true
                });
                $('#endDatedatepicker').datepicker({
                    format: "yyyy-mm-dd",
                    todayHighlight: true
                });
                $('.alert').alert();
            });
        </script>
    </body>

</html>

