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
                                <a href="class" class="list-group-item active">
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
                    <jsp:useBean id="studnetIdList" scope="request" class="ArrayList<system.bean.StudentBean>"/>
                    <div class="col-lg-9">
                        <!-- Website Overview -->
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title">Add Student Class</h5>
                            </div>
                            <div class="card-body">
                                <%
                                    if (request.getAttribute("studnetIdList") != null && studnetIdList.size() < 1) {
                                        out.print("<div class='alert alert-danger alert-dismissible fade show' role='alert'>");
                                        out.print("<strong>No student need to class sign up</strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                %>
                                <br>
                                <jsp:useBean id="classList" scope="request" class="ArrayList<system.bean.ClassBean>"/>
                                <form action="class" method="POST">
                                    <table class="table">
                                        <tr>
                                            <th>Student ID</th>
                                            <td>
                                                <select name="id">
                                                    <%
                                                        for (StudentBean stBean : studnetIdList) {
                                                            out.print("<option value='" + stBean.getId() + "'>" + stBean.getId() + "</option>");
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Class</th>
                                            <td>
                                                <select name="className">
                                                    <%
                                                        for (ClassBean classBean : classList) {
                                                            out.print("<option value='" + classBean.getClassName() + "'>" + classBean.getClassName() + "</option>");
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                            <button type="submit" class="btn btn-primary" <% if (studnetIdList.size() < 1) {
                                                    out.print("disabled");
                                                }%>>Saved</button>
                                    <input type="hidden" name="action" value="add">
                                </form>
                            </div>
                        </div>
                        <a href="class"><button type="button" class="btn btn-secondary">Back</button></a>
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

