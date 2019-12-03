<%-- 
    Document   : dashboard
    Created on : 2019/11/11, 下午 10:08:39
    Author     : JerryKwok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                <a href="#" class="list-group-item active main-color-bg-nav">
                                    <i class="material-icons">dashboard</i> 
                                    <span>Dashboard</span>
                                </a>
                                <a href="attendance" class="list-group-item">
                                    <i class="material-icons">check_box</i
                                    ><span> Attendace</span>
                                </a>
                                <a href="report" class="list-group-item">
                                    <i class="material-icons">insert_drive_file</i>
                                    <span> Reports</span>
                                </a>
                                <a href="student" class="list-group-item">
                                    <i class="material-icons">school</i>
                                    <span> Student
                                        <span class="badge">${studentCount}</span>
                                </span>
                            </a>
                            <a href="../login?action=logout" class="list-group-item text-right">
                                <span>  Logout</span> 
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <!-- Website Overview -->
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title">Overview</h5>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-6 col-sm-12">
                                        <div class="card bg-light card-body mb-3 dash-box">
                                            <h2><i class="material-icons">library_books</i> ${courseCount}</h2>
                                            <h4>Class</h4>
                                        </div>
                                    </div>
                                    <div class="col-md-6  col-sm-12">
                                        <div class="card bg-light card-body mb-3 dash-box">
                                            <h2> <i class="material-icons">school</i> ${studentCount}</h2>
                                            <h4>Student</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Latest Users -->
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title">Schedule</h5>
                            </div>
                            <div class="card-body">
                                <table class="table table-striped table-hover">
                                    <tr>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Joined</th>
                                    </tr>
                                    <tr>
                                        <td>Jill Smith</td>
                                        <td>jillsmith@gmail.com</td>
                                        <td>Dec 12, 2016</td>
                                    </tr>
                                    <tr>
                                        <td>Eve Jackson</td>
                                        <td>ejackson@yahoo.com</td>
                                        <td>Dec 13, 2016</td>
                                    </tr>
                                    <tr>
                                        <td>John Doe</td>
                                        <td>jdoe@gmail.com</td>
                                        <td>Dec 13, 2016</td>
                                    </tr>
                                    <tr>
                                        <td>Stephanie Landon</td>
                                        <td>landon@yahoo.com</td>
                                        <td>Dec 14, 2016</td>
                                    </tr>
                                    <tr>
                                        <td>Mike Johnson</td>
                                        <td>mjohnson@gmail.com</td>
                                        <td>Dec 15, 2016</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <br><br>
        <footer></footer>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

</html>

