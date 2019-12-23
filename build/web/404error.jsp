<%-- 
    Document   : 404error
    Created on : 2019/12/14, 下午 02:05:51
    Author     : JerryKwok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Error page</title>
        <meta charset="utf-8">
        <link href="<%= request.getContextPath() %>/css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row ">
                <div class="col-12">
                    <div class="error-template" style="margin-top: 200px">
                        <h1> Oops!</h1>
                        <h2>404 Not Found</h2>
                        <div class="error-details">
                            Sorry, an error has occured, Requested page not found!
                        </div>
                        <div class="error-actions">
                            <br>
                            <button onclick="history.back()" class="btn btn-primary btn-lg">
                                <span class="glyphicon glyphicon-home"></span>
                                Back to Home
                                </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>







        
        
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>