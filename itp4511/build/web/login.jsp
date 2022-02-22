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
                    if (request.getAttribute("loginMes") != null) {
                        out.print("<div class='alert alert-danger alert-dismissible fade show' role='alert'>");
                        out.print("Invalid login, please try again");
                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                        out.print("<span aria-hidden='true'>&times;</span>");
                        out.print("</button></div><br>");
                    }
                %>
                <form class="login-form" action="login?role=<%= request.getParameter("role")%>" method="POST">
                    <span class="login-form-title">
                        Account Login
                    </span>
                    <p><%= request.getParameter("role")%></p>
                    <br><br>
                    <span>
                        Username
                    </span>
                    <div class="wrap-input-login">
                        <input class="input-login" type="text" name="username" value="<%= request.getAttribute("loginMes") != null ? request.getAttribute("loginMes") : ""%>">
                    </div>
                    <br><br>
                    <span>
                        Password
                    </span>
                    <div class="wrap-input-login">
                        <input class="input-login" type="password" name="pwd" >
                    </div>
                    <br><br>
                    <div class="container-login-form-btn">
                        <input type="hidden" name="role" value="<%= request.getParameter("role")%>">
                        <input type="hidden" name="action" value="authenticate">
                        <button class="login-form-btn" type="submit">
                            Login
                        </button>
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
