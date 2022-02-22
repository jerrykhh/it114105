<%-- 
    Document   : index
    Created on : 2019/10/31, 下午 04:52:53
    Author     : jerrykwok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body> 
        <div class="container-login">
            <div class="wrap-login">
                <%
                    if (request.getAttribute("roleMes") != null) {
                        out.print("<div class='alert alert-danger alert-dismissible fade show' role='alert'>");
                        out.print("<strong>Error Role!</strong> You should select the role.");
                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                        out.print("<span aria-hidden='true'>&times;</span>");
                        out.print("</button></div><br>");
                    }
                %>
                <form class="login-form" action="/login" method="POST">
                    <span class="login-form-title">
                        Account Login
                    </span>
                    <p>Please select your role</p>
                    <div class="loginFormRole mx-auto">
                        <a href="login?role=Student" class="blockquote text-center">
                            <div class="bs-callout bs-callout-deflaut rounded">
                                <h5>Student</h5>
                            </div>
                        </a>
                        <a href="login?role=Teacher" class="blockquote text-center">
                            <div class="bs-callout bs-callout-primary rounded">
                                <h5>Teacher</h5>
                            </div>
                        </a>
                        <a href="login?role=Admin" class="blockquote text-center">
                            <div class="bs-callout bs-callout-info rounded">
                                <h5>Admin</h5>
                            </div>
                        </a>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $('.alert').alert();
            });
        </script>
    </body>
</html>
