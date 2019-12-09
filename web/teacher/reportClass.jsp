<%-- 
    Document   : dashboard
    Created on : 2019/11/11, 下午 10:08:39
    Author     : JerryKwok
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="system.bean.ClassBean, system.bean.SemesterBean, system.bean.StudentBean"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin Area | Dashboard</title>
        <!-- Bootstrap core CSS -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
              rel="stylesheet">
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/style.css" rel="stylesheet">

    </head>
    <%
        String urlStudent = "report?";
        if (request.getParameter("class") != null) {
            urlStudent += "class=" + request.getParameter("class") + "&";
        }
        if (request.getParameter("year") != null) {
            urlStudent += "year=" + request.getParameter("year") + "&";
        } else {
            urlStudent += "year=" + Calendar.getInstance().get(Calendar.YEAR) + "&";
        }
        if (request.getParameter("term") != null) {
            urlStudent += "term=" + request.getParameter("term") + "&";
        } else {
            urlStudent += "term=1&";
        }
    %>
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
                                <a href="attendance" class="list-group-item">
                                    <i class="material-icons">check_box</i
                                    ><span> Attendace</span>
                                </a>
                                <a href="report" class="list-group-item active">
                                    <i class="material-icons">insert_drive_file</i>
                                    <span> Reports</span>
                                </a>
                                <a href="../login?action=logout" class="list-group-item text-right">
                                    <span>  Logout</span> 
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-9">
                            <div class="card">
                                <div class="card-header main-color-bg">
                                    <h5 class="card-title">Select Class to Generate Report</h5>
                                </div>
                                <form action="report" method="GET">
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label>Class</label>
                                            <select class="form-control" name="class">
                                            <jsp:useBean id="classList" scope="request" class="java.util.ArrayList<system.bean.ClassBean>"/>
                                            <%                                                for (ClassBean classVal : classList) {
                                                    if (request.getParameter("class").equals(classVal.getClassName())) {
                                                        out.print("<option value='" + classVal.getClassName() + "' selected>" + classVal.getClassName() + "</option>");
                                                    } else {
                                                        out.print("<option value='" + classVal.getClassName() + "'>" + classVal.getClassName() + "</option>");
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Year</label>
                                        <select class="form-control" name="year">
                                            <jsp:useBean id="yearList" scope="request" class="java.util.ArrayList<system.bean.SemesterBean>"/>
                                            <%
                                                int counter = 1;
                                                for (SemesterBean year : yearList) {
                                                    if (counter == 1) {
                                                        out.print("<option value='" + year.getYear() + "' selected>" + year.getYear() + "</option>");
                                                    } else {
                                                        out.print("<option value='" + year.getYear() + "'>" + year.getYear() + "</option>");
                                                    }
                                                    counter++;
                                                }
                                            %>
                                        </select>
                                    </div>   
                                    <div class="form-group">
                                        <label>Term</label>
                                        <select class="form-control" name="term">
                                            <jsp:useBean id="termList" scope="request" class="java.util.ArrayList<system.bean.SemesterBean>"/>
                                            <%
                                                int count = 1;
                                                for (SemesterBean term : termList) {
                                                    if (count == 1) {
                                                        out.print("<option value='" + term.getTerm() + "' selected>" + term.getTerm() + " term</option>");
                                                    } else {
                                                        out.print("<option value='" + term.getTerm() + "'>" + term.getTerm() + " term</option>");
                                                    }
                                                    count++;
                                                }
                                            %>
                                        </select>
                                    </div>     

                                    <br>    
                                    <input type="hidden" name="report" id="report" value="">
                                    <button type="button" id="btnGenReport" class="btn btn-primary right">Generate</button>
                                    <button type="button" id="btnGenLowReport" class="btn btn-danger">Generate Low Attendance</button>
                                </div>
                            </form>
                        </div>
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title"> Report </h5>
                            </div>
                            <div class="card-body">
                                <canvas id="reportChart" width="50px"></canvas>
                                Average Attendance (%): ${attendAVG}
                            </div>
                        </div>
                        <!-- Website Overview -->
                        <div class="card">
                            <div class="card-body">
                                <button type="button" id="btnExportExcel" class="btn btn-outline-info">Export Attendance Data to Excel</button>
                                <br><br>
                                <div class="form-group">
                                    <table class="table table-hover exportExcel">
                                        <thead>
                                            <tr>
                                                <th scope="col">Student ID</th>
                                                <th scope="col">Student Name</th>
                                                <th scope="col">Attend Rate</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <jsp:useBean id="studentAttendList" scope="request" class="java.util.ArrayList<system.bean.StudentBean>"/>
                                            <%
                                                for (StudentBean student : studentAttendList) {
                                                    out.print("<tr data-href='" + urlStudent + "studentId=" + student.getId() + "'>");
                                                    out.print("<td>" + student.getId() + "</td>");
                                                    out.print("<td>" + student.getName() + "</td>");
                                                    out.print("<td>" + student.getAttendRate() + " %</td>");
                                                    out.print("</tr>");
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <a href="report">
                            <button type="button" class="btn btn-secondary">
                                Back
                            </button>
                        </a>
                    </div>
                </div>
        </section>
        <footer></footer>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
        <script src="../js/jquery.tableToexcel.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $("tr[data-href]").click(function () {
                    window.location.href = $(this).attr("data-href");
                });
                
                $("#btnGenReport").click(function(){
                    $("form").submit();
                });
                $("#btnGenLowReport").click(function(){
                    $("#report").val("low");
                    $("form").submit();
                });
                $("#btnExportExcel").click(function () {
                    $(".exportExcel").table2excel({
                        exclude: ".excludeThisClass",
                        name: "Worksheet Name",
                        filename: "<%= request.getParameter("studentId") + "_Report"%>.xls", // do include extension
                        preserveColors: false // set to true if you want background colors and font colors preserved
                    });
                });
            });
        </script>
        <script>
            let reportChart = document.getElementById('reportChart').getContext('2d');
            Chart.defaults.global.defaultFontFamily = 'Lato';
            Chart.defaults.global.defaultFontSize = 18;
            Chart.defaults.global.defaultFontColor = 'black';
            let massPopChart = new Chart(reportChart, {
                type: 'pie', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
                data: {
                    <%
                        if(request.getParameter("report") != null && request.getParameter("report").equals("low"))
                            out.print("labels: ['Student < 39%','Student 40-49%', 'Student 50-60%']");
                        else
                            out.print("labels: ['Student < 60%', 'Student >=60%']");
                    %>,
                    datasets: [{
                            label: 'Population',
                            data: [
                                <%
                                if(request.getParameter("report") != null && request.getParameter("report").equals("low"))
                                    out.print(request.getAttribute("numberOfStudentLowAtt") + ", " + request.getAttribute("numberOfStudentDanger") + ", " + request.getAttribute("numberOfStudentWarning"));
                                else
                                    out.print(request.getAttribute("numberOfStudentNotMeetTar") + ", " + request.getAttribute("numberOfStudentMeetTar"));
                                    
                                %>
                            ],
                            //backgroundColor:'green',
                            backgroundColor: [
                                'rgba(236, 107, 86, 0.6)',
                                'rgba(71, 179, 156, 0.9)',
                                'rgba(88,80,141,0.8)'
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
                        text: 'Meet the Attendance Target',
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
                            left: 30,
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

