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
                <form class="login-form" action="/login" method="POST">
                    <span class="login-form-title">
                        Account Login
                    </span>
                    <p>Please select your role</p>
                    <div class="loginFormRole mx-auto">
                        <a href="login.jsp?role=Student" class="blockquote text-center">
                            <div class="bs-callout bs-callout-deflaut rounded">
                                <h5>Student</h5>
                            </div>
                        </a>
                        <a href="login.jsp?role=Teacher" class="blockquote text-center">
                            <div class="bs-callout bs-callout-primary rounded">
                                <h5>Teacher</h5>
                            </div>
                        </a>
                        <a href="login.jsp?role=Admin" class="blockquote text-center">
                            <div class="bs-callout bs-callout-info rounded">
                                <h5>Admin</h5>
                            </div>
                        </a>
                    </div>
                </form>
            </div>
        </div>
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
