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
                        <nav:showNav role="Admin" active="<%=request.getParameter("role") %>" />
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
                                    if (request.getAttribute("idMes") != null) {
                                        out.print("<div class='alert alert-danger alert-dismissible fade show' role='alert'>");
                                        out.print("<strong>Your Id is duplicated</strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }

                                %>
                                <br>
                                <%                                    String roleName = request.getParameter("role");
                                %>
                                <form action="user?role=<%= roleName%>" method="POST">
                                    <table class="table">
                                        <tr>
                                            <th><%= roleName%> ID</th>
                                            <th>
                                                <%
                                                    if (role.equals("Student")) {
                                                        out.print("The Student ID will auto generate");
                                                    } else if (role.equals("Teacher") || role.equals("Admin")) {
                                                        String inputValId = "";
                                                        if (request.getAttribute("id") != null) {
                                                            inputValId = request.getAttribute("id") + "";
                                                        }
                                                        out.print("<input type='text' class='form-control' name='id' value='" + inputValId + "'>");
                                                    }
                                                %>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th><%= roleName%> Email</th>
                                            <th>The <%= roleName%> email will auto generate</th>
                                        </tr>
                                        <tr>
                                            <th><%= roleName%> First Name</th>
                                            <td><input name="fname" class="form-control" value="<%= request.getAttribute("fname") != null ? request.getAttribute("fname") : ""%>" required></td>
                                        </tr>
                                        <tr>
                                            <th><%= roleName%> Last Name</th>
                                            <td><input name="lname" class="form-control" value="<%= request.getAttribute("lname") != null ? request.getAttribute("lname") : ""%>" required></td>
                                        </tr>
                                        <tr>
                                            <th>Student Gender</th>
                                            <td>
                                                <select name="gender" class="form-control" required>
                                                    <%
                                                        if (request.getAttribute("gender") != null && request.getAttribute("gender").equals("M")) {
                                                            out.print("<option value='M' selected>Male</option>");
                                                            out.print("<option value='F'>Female</option>");
                                                        } else if (request.getAttribute("gender") != null && request.getAttribute("gender").equals("F")) {
                                                            out.print("<option value='M'>Male</option>");
                                                            out.print("<option value='F' selected>Female</option>");
                                                        } else {
                                                            out.print("<option value='M'>Male</option>");
                                                            out.print("<option value='F'>Female</option>");
                                                        }

                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Birthday</th>
                                            <td>
                                                <div class="form-group">
                                                    <div class="input-group date">
                                                        <input type="date" class="form-control" id="datepicker" name="birthday" value="<%= request.getAttribute("birthday") != null ? request.getAttribute("birthday") : ""%>" required>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Password</th>
                                            <td><input type='password' class="form-control" name="password" value="" required></td>
                                        </tr>
                                        <tr>
                                            <th>Confirm Password</th>
                                            <td><input type="password" class="form-control" name="pwd" value="" required></td>
                                        </tr>
                                    </table>
                                    <button type="btn" class="btn btn-primary">Create User</button>
                                    <input type="hidden" name="action" value="add">
                                </form>
                            </div>
                        </div>
                        <a href="user?role=<%= roleName%>"><button type="button" class="btn btn-secondary">Back</button></a>
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
                    todayHighlight: true
                });
                $('.alert').alert();
            });
        </script>
    </body>

</html>

