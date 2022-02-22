<%@page contentType="text/html" pageEncoding="UTF-8" import="system.bean.AttendBean, system.bean.StudentBean"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Student Area | Attendance</title>
        <!-- Bootstrap core CSS -->
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
                        <nav:showNav role="Student" active="attendance" />
                        </div>
                        <div class="col-lg-9">
                            <!-- Website Overview -->
                            <!-- Latest Users -->
                            <div class="card">
                                <div class="card-header main-color-bg">
                                    <h5 class="card-title">Attendance</h5>
                                </div>
                                <div class="card-body">
                                    <button type="button" id="btnExportExcel" class="btn btn-outline-info">Export Attendance Data to Excel</button>
                                    <br><br>
                                <jsp:useBean id="studentDetials" scope="request" class="system.bean.StudentBean"/>
                                <table class="table table-borderless">
                                    <tbody>
                                        <tr>
                                            <th scope="row">Student ID:</th>
                                            <td><jsp:getProperty name="studentDetials" property="id"/></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Student Name:</th>
                                            <td><jsp:getProperty name="studentDetials" property="name"/></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Gender :</th>
                                            <td><jsp:getProperty name="studentDetials" property="gender"/></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Student Email:</th>
                                            <td><jsp:getProperty name="studentDetials" property="email"/></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Birthday:</th>
                                            <td><jsp:getProperty name="studentDetials" property="birthday"/></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div class="card">
                                    <div class="card-body">
                                        <canvas id="reportChart" width="50px"></canvas>
                                        <table> 
                                            <tr>
                                                <td>Total Present Day: </td>
                                                <td> ${totalAttendDay}</td>
                                            </tr>
                                            <tr>
                                                <td>Present Day: </td>
                                                <td> ${presentDay}</td>
                                            </tr>
                                            <tr>
                                                <td>Absent: </td>
                                                <td> ${absDay}</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <table class="table table-hover exportExcel">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>ID</th>
                                            <th>Date</th>
                                            <th>Attend</th>
                                        </tr>
                                    </thead>
                                    <%
                                        int id = 1;
                                        for (AttendBean attend : studentDetials.getAttendList()) {
                                            out.print("<tr>");
                                            out.print("<td>" + (id++) + "</td>");
                                            out.print("<td>" + attend.getDate() + "</td>");
                                            if (attend.isAttend()) {
                                                out.print("<td><a href='#' class='badge badge-success'>Present</a></td>");
                                            } else {
                                                out.print("<td><a href='#' class='badge badge-danger'>Absent</a></td>");
                                            }
                                            out.print("</tr>");
                                        }

                                    %>
                                </table>
                            </div>
                        </div>
                        <a href="report?class=<%= request.getParameter("class")%>&year=<%= request.getParameter("year")%>&term=<%= request.getParameter("term")%>">
                            <button type="button" class="btn btn-secondary">
                                Back
                            </button>
                        </a>
                    </div>

                </div>
            </div>
        </section>
        <br><br>
        <footer></footer>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery.tableToexcel.js"></script>
        <script>
            $(document).ready(function () {
                $("#btnExportExcel").click(function () {
                    $(".exportExcel").table2excel({
                        exclude: ".excludeThisClass",
                        name: "Worksheet Name",
                        filename: "Student_Report.xls", // do include extension
                        preserveColors: false // set to true if you want background colors and font colors preserved
                    });
                });
            });
            let reportChart = document.getElementById('reportChart').getContext('2d');
            Chart.defaults.global.defaultFontFamily = 'Lato';
            Chart.defaults.global.defaultFontSize = 18;
            Chart.defaults.global.defaultFontColor = 'black';
            let massPopChart = new Chart(reportChart, {
                type: 'pie', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
                data: {
                    labels: ['Present(%)', 'Absent(%)'],
                    datasets: [{
                            label: false,
                            data: [
                                Math.round(${presentDay/totalAttendDay*100}),
                                Math.round(${absDay/totalAttendDay*100})
                            ],
                            //backgroundColor:'green',
                            backgroundColor: [
                                'rgba(71, 179, 156, 0.9)',
                                'rgba(236, 107, 86, 0.6)'
                                
                            ],
                            borderWidth: 1,
                            borderColor: '#777',
                            hoverBorderWidth: 3,
                            hoverBorderColor: '#000'
                        }]
                },
                options: {
                    title: {
                        display: true,
                        text: 'Attendance',
                        fontSize: 20
                    },
                    legend: {
                        display: true,
                        position: 'right',
                        labels: {
                            fontColor: '#000'
                        }
                    },
                    layout: {
                        padding: {
                            left: 20,
                            right: 0,
                            bottom: 0,
                            top: 0
                        }
                    },
                    tooltips: {
                        enabled: true
                    }
                }
            });

        </script>
    </body>

</html>

