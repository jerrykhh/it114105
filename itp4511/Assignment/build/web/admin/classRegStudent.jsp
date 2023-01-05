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
                        <%@taglib uri="/WEB-INF/tlds/nav-taglib.tld" prefix="nav" %>
                        <nav:showNav role="Admin" active="ClassReg" />
                        </div>

                        <div class="col-lg-9">
                            <!-- Website Overview -->
                            <div class="card">
                                <div class="card-header main-color-bg">
                                    <h5 class="card-title">Edit Student Class</h5>
                                </div>
                                <div class="card-body">
                                    <br>
                                <jsp:useBean id="studentDetials" scope="request" class="system.bean.StudentBean"/>
                                <jsp:useBean id="classList" scope="request" class="ArrayList<system.bean.ClassBean>"/>
                                <form action="classReg" method="POST">
                                    <table class="table">
                                        <tr>
                                            <th>Student Class ID</th>
                                            <td>${studentClassID}</td>
                                        </tr>
                                        <tr>
                                            <th>Student Name</th>
                                            <td><jsp:getProperty name="studenBean" property="name"/></td>
                                        </tr>
                                        <tr>
                                            <th>Student ID</th>
                                            <td><jsp:getProperty name="studenBean" property="id"/></td>
                                        </tr>
                                        <tr>
                                            <th>Gender</th>
                                            <td><jsp:getProperty name="studenBean" property="gender"/></td>
                                        </tr>
                                        <tr>
                                            <th>Birthday</th>
                                            <td><jsp:getProperty name="studenBean" property="birthday"/></td>
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
                                    <button type="submit" class="btn btn-primary">Saved</button>
                                    <input type="hidden" name="id" value="${studentClassID}">
                                    <input type="hidden" name="action" value="save">
                                </form>
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
        <script src="../js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $('.alert').alert();
            });
        </script>>
    </body>

</html>

