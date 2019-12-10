<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body> 
        <div class="container-login">
            <div class="wrap-login">
                <form class="login-form" action="login?role=<%= request.getParameter("role") %>" method="POST">
                    <span class="login-form-title">
                        Account Login
                    </span>
                    <p><%= request.getParameter("role")%></p>
                    <br><br>
                    <span>
                        Username
                    </span>
                    <div class="wrap-input-login">
                        <input class="input-login" type="text" name="username">
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
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
